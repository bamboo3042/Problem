<%@page import="com.koreait.reply.ReplyDTO"%>
<%@page import="com.koreait.reply.ReplyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<jsp:setProperty property="idx" param="b_idx" name="board"/>
<jsp:useBean id="replydao" class="com.koreait.reply.ReplyDAO"/>
<%
	board = dao.viewBoard(board);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
<script>
	function getPassword(loc){
		console.log(loc);
		let password = prompt("게시글 비밀번호를 입력하세요");
		location.href = loc + "&password=" + password;
	}
	
//	function getrePassword(re_loc){
//		console.log(re_loc);
//		let re_password = prompt("댓글 비밀번호를 입력하세요");
//		location.href = loc + "&password=" + re_password; 
//	}
</script>
</head>
<body>
	<h2>글보기</h2>
	<table border="1" width="800">
		<tr>
			<td>제목</td><td><%=board.getB_title()%></td>
		</tr>
		<tr>
			<td>ID</td><td><%=board.getUserid()%></td>
		</tr>
		<tr>
			<td>작성자</td><td><%=board.getUsername()%></td>
		</tr>
		<tr>
			<td>조회수</td><td><%=board.getHit()%></td>
		</tr>
		<tr>
			<td>내용</td><td><%=board.getB_content()%></td>
		</tr>
		<tr>
			<td>파일</td>
			<td>
				<%
					if(board.getB_file() != null && !board.getB_file().equals("")){
						out.println("첨부파일");
						out.println("<img src='../upload/"+board.getB_file()+"' alt='첨부파일' width='150'>");
					}
				%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick=getPassword('./edit.jsp?b_idx=<%=board.getIdx()%>')> 
				<input type="button" value="삭제" onclick=getPassword('./delete.jsp?b_idx=<%=board.getIdx()%>')>
				<input type="button" value="리스트" onclick="location.href='./list.jsp'">
			</td>
		</tr>
	</table>
	<hr/>
	<form method="post" action="reply_ok.jsp">
		<input type="hidden" name="b_idx" value="<%=board.getIdx()%>">
		<p><label>ID : <input type="text" size="20" name="re_id"></label></p>
		<p><label>PASSWORD : <input type="text" size="20" name="re_password"></label>
		<p><input type="text" size="40" name="re_content"> <input type="submit" value="확인"></p>
	</form>
	<hr/>
<%
	for(ReplyDTO reply: replydao.viewReply(board)){
%>
		<p><%=reply.getUserid()%> : <%=reply.getContent()%></p>
<%
	}
%>
</body>
</html>