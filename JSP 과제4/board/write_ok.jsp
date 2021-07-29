<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>

<%
	request.setCharacterEncoding("UTF-8");
	if(session.getAttribute("userid") == null){
%>
	<script>
		alert('로그인 후 이용하세요');
		location.href='../login.jsp';
	</script>
<%
	}else{
// D:\Ryuzy/JSP/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Day6/upload
//		String b_title = request.getParameter("b_title");
//		String b_content = request.getParameter("b_content");
		
		String uploadPath = request.getRealPath("upload");
		System.out.println(uploadPath);
		int size = 1024*1024*10;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
			String b_title = multi.getParameter("b_title");
			String b_content = multi.getParameter("b_content");
			String b_file = multi.getFilesystemName("b_file");
			
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "insert into tb_board(b_userid, b_title, b_content, b_file) values (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, (String)session.getAttribute("userid"));
				pstmt.setString(2, b_title);
				pstmt.setString(3, b_content);
				pstmt.setString(4, b_file);
				pstmt.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
%>
<script>
	alert('등록되었습니다');
	location.href='list.jsp';
</script>
<%
	}
%>