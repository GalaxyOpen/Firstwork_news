<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="../resources/css/main.css">
  <link rel="stylesheet" href="../resources/css/bootstrap.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  <form action="/article/save" method="post" enctype="multipart/form-data">
          제목 : <input type="text" name="articleTitle" placeholder="제목"><br>
          작성자 : <input type="text" name="articleWriter" value="${sessionScope.loginEmail}"><br>
          글 내용 : <textarea name="articleContents" cols="100" rows="10" placeholder="내용을 입력하세요"></textarea></textarea><br>
          파일 첨부 : <input type="file" name="articlePicture" multiple><br>
          <input type="submit" value="작성">
          <a href="/">처음으로 돌아가기</a>
  </form>
</div>
<%@include file="../component/footer.jsp"%>
</body>
</html>
