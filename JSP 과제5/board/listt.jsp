<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.koreait.board.BoardDTO" %>
<%@ page import="com.koreait.board.BoardDAO" %>
<jsp:useBean id="boardDTO" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="boardDAO" class="com.koreait.board.BoardDAO"  />
<jsp:useBean id="replyDTO" class="com.koreait.board.ReplyDTO"/>
<jsp:useBean id="replyDAO" class="com.koreait.board.ReplyDAO"/>
<%	
	request.setCharacterEncoding("UTF-8");
%>	

	
	
<%
	int totalCount = 0;
	int pagePerCount = 10; 	//페이지당 글개수
	int start = 0; // 시작 글번호
	
	
	//===date=======
	Date from = new Date();
	SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	//날짜 입력받는 객체
	String to = fm.format(from);	// 2021-07-27
	//System.out.println(to);
	
	

	String pageNum = request.getParameter("pageNum"); 
	if(pageNum != null && !pageNum.equals("")){
		start = (Integer.parseInt(pageNum)-1) * pagePerCount;
	}else{
		pageNum = "1";
		start=0;
	}

	
			
	
	totalCount=boardDAO.totalcnt();
	
			
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
			<th width="60">번호</th>
			<th width="300">제목</th>
			<th width="100">글쓴이</th>
			<th width="70">조회수</th>
		</tr>
<%
boardDTO.setDtolist(boardDAO.page(start,pagePerCount));
//자바빈즈로 선언한 boardDTO객체에 boardDAO.page 메소드 리턴값을 셋함
//이때, page 리턴값, Dtolist 둘다 Arraylist 형식
for(BoardDTO list : boardDTO.getDtolist()){
//BoardDTO import를 통한 클래스 타입 사용,  set한 page메소드 리턴값 Dtolist를 get으로 받아옴(Dtolist는 arraylist형식)
//BoardDTO 형식을 가지는 Dtolist(arraylist 타입)의 각 객체들을 하나씩 가져와 각 객체별로 아래 기능 수행
	
			
		
		String imgCheck="";
		
		if(list.getFile() !=null && !list.getFile().equals("")){
			imgCheck="<img src='./img.png' height='20' width='20'>";
		}
		
		int idx = list.getIdx(); //향상된 포문에서의 list변수(BoardDTO에서 getIdx를 겟함)
			
		String replycnt_str="";
			
			if(replyDAO.replycnt(idx) > 0){
				//위에서 가져옴 BoardDTO의 idx를 매개변수로 넣어 replycnt메소드(게시물마다의 댓글 갯수 계산)를 수행
			
		replycnt_str ="["+replyDAO.replycnt(idx)+"]";
			}
		
	// 저의 게시판은 익명게시판입니다. 참고해주세요 익명성을 보장해주는 게시판이라 그렇습니다.	
%>
		<tr>
			<td><%=list.getIdx()%></td>
			<td><a href="./view.jsp?b_idx=<%=list.getIdx()%>"><%=list.getTitle()%></a> <%=replycnt_str%> 
			<%=imgCheck%>
			</td>
			<td><%=list.getUserid()%></td>
			<td><%=list.getHit()%></td>
			</tr>
		
<%
				} // 향상된 포문 종료
		
		int pageNums = 0;
		if(totalCount % pagePerCount == 0){
			pageNums = (totalCount / pagePerCount); // 23 / 10 
		}else{
			pageNums = (totalCount / pagePerCount) + 1;
		}
%>
	<tr>
		<td colspan="6" align="center">
		<%
		
			for(int i=1; i<=pageNums; i++){
				out.print("<a href='list.jsp?pageNum="+i +"'>["+i+"]</a> ");
				
			}
		
		%>

		</td> 
	</tr>
	</table>
	
	<p><input type="button" value="글쓰기" onclick="location.href='write.jsp?'"> <input type="button" value="메인" onclick="location.href='../login.jsp'"></p>
</body>
</html>