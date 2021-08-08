<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<jsp:setProperty property="idx" name="board" param="b_idx"/>
<jsp:setProperty property="userpassword" param="password" name="board"/>
<%
	if(dao.editviewBoard(board) == null){
		%>
		<script>
		alert('비밀번호가 틀렸습니다');
		location.href='view.jsp?b_idx=<%=board.getIdx()%>';
		</script>
		<%
	}
	else{
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
</head>
<script>
function fileTypeCheck(obj) {

	let pathpoint = obj.value.lastIndexOf('.');
	let filepoint = obj.value.substring(pathpoint+1,obj.length);
	let filetype = filepoint.toLowerCase();

	if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {
		return true;
	} else {

		alert('이미지 파일만 업로드 할 수 있습니다!(jpg, gif, png, jpeg, bmp)');
		location.href = "edit.jsp?b_idx=<%=board.getIdx()%>&password="+<%=request.getParameter("password")%>;

		return false;
	}

}
</script>
<body>
	<h2>글수정</h2>
	<form method="post" action="edit_ok.jsp" enctype="multipart/form-data">
		<input type="hidden" name="b_idx" value="<%=board.getIdx()%>">
		<p>ID : <%=board.getUserid()%></p>
		<p>작성자 : <%=board.getUsername()%></p>
		<p><label>제목 : <input type="text" name="b_title" value="<%=board.getB_title()%>"></label></p>
		<p>내용</p>
		<p><textarea rows="5" cols="40" name="b_content" id="editor"><%=board.getB_content()%></textarea></p>
		<script>
	    ClassicEditor
        .create( document.querySelector( '#editor' ) )
        .catch( error => {
            console.error( error );
        } );
		</script>
		<%
			if(board.getB_file() != null && !board.getB_file().equals("")){
				out.println("첨부파일");
				out.println("<img src='../upload/"+board.getB_file()+"' alt='첨부파일' width='150'>");
			}
		%>
		<p><input type="file" name="b_file" onchange="fileTypeCheck(this)" accept="image/*"></p>
		<p><input type="submit" value="수정"> 
		<input type="reset" value="다시작성"> 
		<input type="button" value="돌아가기" onclick="history.back()"></p>
	</form>
</body>
</html>
<%
	}
%>