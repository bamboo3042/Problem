<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<jsp:useBean id="reply" class="com.koreait.reply.ReplyDTO"/>
<jsp:useBean id="dao" class="com.koreait.reply.ReplyDAO"/>
<jsp:setProperty property="boardidx" name="reply" param="b_idx"/>
<jsp:setProperty property="userid" name="reply" param="re_id"/>
<jsp:setProperty property="userpassword" name="reply" param="re_password"/>
<jsp:setProperty property="content" name="reply" param="re_content"/>

<%
	request.setCharacterEncoding("UTF-8");
		
	if(dao.addReply(reply) == 1){
		%>
		<script>
		alert('등록되었습니다');
		location.href='view.jsp?b_idx=<%=reply.getBoardidx()%>';
		</script>
		<%
	}
	else{
		%>
		<script>
		alert('등록실패');
		location.href='view.jsp?b_idx=<%=reply.getBoardidx()%>';
		</script>
		<%			
	}
%>
