<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  <h2>기자 정보 수정</h2>
  <form action="/reporter/update" method="post" name="updateForm" enctype="multipart/form-data">
    회원번호 : <input type="text" name="id" value="${reporter.id}" readonly>
    ID :<input type="text" name="reporterEmail" value="${reporter.reporterEmail}" readonly placeholder="이메일"><br>
    Password : <input type="text" id="reporterPassword" name="reporterPassword" placeholder="비밀번호"><br>
    Name : <input type="text" name="reporterName" value="${reporter.reporterName}" placeholder="이름"><br>
    Media : <input type="text" name="reporterMedia" value="${reporter.reporterMedia}" placeholder="언론사">
    Mobile : <input type="text" name="reporterMobile" value="${reporter.reporterMobile}" placeholder="전화번호">
    파일 첨부 : <input type="file" name="reporterPicture" multiple><br>
    <input type="button" onclick="update_check()" value="수정">
  </form>
</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
  const update_check = () => {
    const inputPass = document.getElementById("reporterPassword").value;
    const DBPass = '${reporter.reporterPassword}';
    if (inputPass == DBPass) {
      document.updateForm.submit();

    } else {
      alert("비밀번호가 일치하지 않습니다!!");
    }
  }
</script>
</html>