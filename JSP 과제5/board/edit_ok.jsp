<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%
	String uploadPath = request.getSession().getServletContext().getRealPath("upload");
	int size = 1024*1024*10;
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());

	if(dao.editBoard(multi) == 1){
		%>
		<script>
			alert('수정되었습니다');
			location.href='list.jsp';
		</script>			
		<%
	}else{
		%>
		<script>
			alert('수정  실패!');
			location.href='list.jsp';
		</script>			
		<%
	}
%>