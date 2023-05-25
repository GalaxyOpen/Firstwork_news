<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="../resources/css/main.css">
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  <h2>기사 수정</h2>
  <form action="/article/update" method="post" name="updateForm" enctype="multipart/form-data">
    기사 번호 : <input type="text" name="id" value="${article.id}" readonly><br>
    기사 제목 : <input type="text" name="articleTitle" placeholder="수정할 글 제목"><br>
    작성 기자 : <input type="text" name="articleWriter" value="${article.articleWriter}" readonly><br>
    기사 내용 : <textarea name="articleContents"  cols="100" rows="10">${article.articleContents}</textarea>
    파일 첨부 : <input type="file" name="articlePicture" multiple><br>
    <input type="submit" value="수정">
    <a href="/">처음으로 돌아가기</a>
  </form>
</div>
<%@include file="../component/footer.jsp"%>
</body>
</html>
