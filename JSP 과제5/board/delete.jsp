<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<jsp:setProperty property="idx" param="b_idx" name="board"/>
<jsp:setProperty property="userpassword" param="password" name="board"/>
<%
	if(dao.deleteBoard(board) != 0){
		%>
		<script>
		alert('삭제되었습니다');
		location.href='list.jsp';
		</script>
		<%
	}
	else{
		%>
		<script>
		alert('삭제실패');
		location.href='list.jsp';
		</script>
	<%
	}
%>