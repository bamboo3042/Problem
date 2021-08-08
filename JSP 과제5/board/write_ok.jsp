<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%
	String uploadPath = request.getSession().getServletContext().getRealPath("upload");
	int size = 1024*1024*10;
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());

	if(dao.createBoard(multi) == 1){
		%>
		<script>
			alert('등록되었습니다');
			location.href='list.jsp';
		</script>			
		<%
	}else{
		%>
		<script>
			alert('등록 실패!');
			location.href='list.jsp';
		</script>			
		<%
	}
%>