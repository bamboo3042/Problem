<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
<script>
function fileTypeCheck(obj) {

	let pathpoint = obj.value.lastIndexOf('.');
	let filepoint = obj.value.substring(pathpoint+1,obj.length);
	let filetype = filepoint.toLowerCase();

	if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {
		return true;
	} else {

		alert('이미지 파일만 업로드 할 수 있습니다!(jpg, gif, png, jpeg, bmp)');
		location.href = "write.jsp";

		return false;
	}

}
</script>
</head>
<body>
	<h2>글쓰기</h2>
	<form method="post" action="write_ok.jsp" enctype="multipart/form-data">
		<p><label>ID : <input type="text" name="userid"></label></p>
		<p><label>password : <input type="text" name="userpassword"></label></p>
		<p><label>작성자 : <input type="text" name="username"></label></p>
		<p><label>제목 : <input type="text" name="b_title"></label></p>
		<p>내용</p>
		<p><textarea rows="5" cols="40" id="editor" name="b_content"></textarea></p>
		<script>
	    ClassicEditor
        .create( document.querySelector( '#editor' ) )
        .catch( error => {
            console.error( error );
        } );
		</script>
		<p>파일 : <input type="file" name="b_file" onchange="fileTypeCheck(this)" accept="image/*"></p>
		<p><input type="submit" value="등록">
		<input type="reset" value="다시작성"> 
		<input type="button" value="리스트" onclick="location.href='list.jsp'"></p>
	</form>
</body>
</html>