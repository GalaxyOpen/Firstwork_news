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
<%@include file="../component/reporter_nav.jsp"%>
<div id="section">
  <form action="/reporter/login" method="post">
    <h2>기자 회원 로그인</h2>
    <input type="text" name="reporterEmail" placeholder="기자용 Email">
    <input type="text" name="reporterPassword" placeholder="해당 비밀번호">
    <input type="submit" value="기자로그인">
  </form>
</div>
<a href="/">처음으로 돌아가기</a>
<%@include file="../component/footer.jsp"%>
</body>
</html>
