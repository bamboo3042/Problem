<%@page import="com.koreait.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<jsp:useBean id="replydao" class="com.koreait.reply.ReplyDAO"/>
<%
	int totalCount = dao.countBoard();
	int pagePerCount = 10;	// 페이지당 글개수
	int start = 0;	// 시작 글번호
			
	// http://localhost:9090/Day5/board/list.jsp?pageNum=3
	String pageNum = request.getParameter("pageNum");
	if(pageNum != null && !pageNum.equals("")){
		start = (Integer.parseInt(pageNum)-1) * pagePerCount;	// 2 * 10
	}else{
		pageNum = "1";
		start=0;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>
	<h2>리스트</h2>
	<p>게시글 : <%=totalCount%>개</p>
	
	<table border="1" width="800">
		<tr>
			<th width="50">번호</th>
			<th width="300">제목</th>
			<th width="100">ID</th>
			<th width="100">작성자</th>
			<th width="75">조회수</th>
		</tr>
<%
	for(BoardDTO board : dao.boardList(start, pagePerCount)){		
		String fileStr = "";
		if(board.getB_file() != null && !board.getB_file().equals("")){
			fileStr = "<img src='./file.png' alt='파일'>";
		}
%>
		<tr>
			<td><%=board.getIdx()%></td>
			<td><a href="./view.jsp?b_idx=<%=board.getIdx()%>"><%=board.getB_title()%></a> <%=replydao.countReply(board)%> <%=fileStr%></td>
			<td><%=board.getUserid()%></td>
			<td><%=board.getUsername()%></td>
			<td><%=board.getHit()%></td>
		</tr>
<% 	}

	int pageNums = 0;
	if(totalCount % pagePerCount == 0){
		pageNums = (totalCount / pagePerCount);	// 20 / 10
	}else{
		pageNums = (totalCount / pagePerCount) + 1;	
	}
%>
	<tr>
		<td colspan="6" align="center">
			<%
				for(int i=1; i<=pageNums; i++){
					out.print("<a href='list.jsp?pageNum="+i+"'>["+i+"]</a> ");
				}
			%>
		</td>
	</tr>
	</table>
	
	<p><input type="button" value="글쓰기" onclick="location.href='write.jsp'"></p>
</body>
</html>