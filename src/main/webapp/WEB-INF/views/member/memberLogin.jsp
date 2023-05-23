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
<%@include file="../component/member_nav.jsp"%>
<div id="section">
  <form action="/member/login" method="post">
    <h2>일반 회원 로그인</h2>
    <input type="text" name="memberEmail" placeholder="Email"><br>
    <input type="text" name="memberPassword" placeholder="Password"><br>
    <input type="submit" value="로그인">
  </form>
</div>
<a href="/">처음으로 돌아가기</a>
<%@include file="../component/footer.jsp"%>
</body>
</html>
