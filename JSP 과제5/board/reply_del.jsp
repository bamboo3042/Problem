<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<jsp:useBean id="reply" class="com.koreait.board.ReplyDTO" />
<jsp:useBean id="dao" class="com.koreait.board.ReplyDAO"/>
<jsp:useBean id="boardDTO" class="com.koreait.board.BoardDTO"/>
<%
	
	if(session.getAttribute("userid") == null){
%>
	<script>
		alert('로그인 후 이용하세요');
		location.href='../login.jsp';
	</script>
<% 	
	}else{
		request.setCharacterEncoding("UTF-8");	
%>


		
<%
		reply.setReidx(Integer.parseInt(request.getParameter("re_idx")));
	if(dao.reply_del(reply) == 1){	
%>
	boardDTO.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
	
		<script>
		alert('삭제되었습니다');
		
		//location.href='view.jsp?b_idx';
		location.href='./view.jsp?b_idx=<%=boardDTO.getB_idx()%>';
		</script>
<%
	}
}
%>
