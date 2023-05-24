<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  ${sessionScope.loginEmail} 님 환영해요!

  <button onclick="update('${member.id}')">개인정보 수정하기</button><br>
        <a href="/article/save">글 작성하기</a><br>
        <a href="/article/list">글 목록보기</a><br>
        <a href="/member/logout">로그아웃</a><br>
        <a href="/member/myPage">처음으로 돌아가기</a><br>

    <c:if test="${sessionScope.loginEmail == 'admin'}">
        <a href="/member/admin">회원목록</a><br>
    </c:if>

</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
  const update=(id)=>{
    location.href="/member/update?id="+id;
  }

</script>
</html>
