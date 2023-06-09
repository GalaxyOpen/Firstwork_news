<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./resources/css/main.css">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
<%@include file="./component/header.jsp"%>
<%@include file="./component/nav.jsp"%>
<h2 style="dsiplay: flex; text-align: center;">Reporter vs Keyboard Warrior</h2>

<div style="display: flex; justify-content: center; align-items: center;">
    <img src="/resources/images/img.png" weight="250" height="250">
    <img src="./resources/images/이의있소.png" weight="250" height="250">
</div>

<c:if test="${sessionScope.reporterLoginEmail == 'admin' && sessionScope.loginEmail =='admin'}">
    <a href="/member/list">회원목록</a>
    <a href="/reporter/list">기자목록</a>
    <a href="/artile/list">전체 기사 목록</a>
</c:if>
<%@include file="./component/footer.jsp"%>
</body>
</html>