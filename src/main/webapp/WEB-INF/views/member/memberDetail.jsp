<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<html>
<head>
    <title>Title</title>
  <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
  <link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  <table>
    <tr>
      <th>가입 번호</th>
      <td>${member.id}</td>
    </tr>
    <tr>
      <th>레벨</th>
      <td>${member.memberLevel}</td>
    </tr>
    <tr>
      <th>이름</th>
      <td>${member.memberName}</td>
    </tr>
    <tr>
      <th>이메일</th>
      <td>${member.memberEmail}</td>
    </tr>
    <tr>
      <th>휴대전화 번호</th>
      <td>${member.memberMobile}</td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td>${member.memberPassword}</td>
    </tr>
  </table>
  <%-- 멤버 회원은 사진이 없으므로 제외함. --%>
</div>
<%@include file="../component/footer.jsp"%>
</body>
</html>
