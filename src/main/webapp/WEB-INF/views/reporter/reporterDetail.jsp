<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../resources/css/main.css">
  <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  <table>
    <tr>
      <th>가입 기자번호</th>
      <td>${reporter.id}</td>
    </tr>
    <tr>
      <th>레벨</th>
      <td>${reporter.reporterLevel}</td>
    </tr>
    <tr>
      <th>대표사진</th>
      <td>
        <c:forEach items="${reporterPictureList}" var="reporterPicture">
          <img src="${pageContext.request.contextPath}/upload/${reporterPicture.storedFileName}"
               alt="" width="250" height="250">
        </c:forEach>
      </td>
    </tr>
    <tr>
      <th>이름</th>
      <td>${reporter.reporterName}</td>
    </tr>
    <tr>
      <th>이메일</th>
      <td>${reporter.reporterEmail}</td>
    </tr>
    <tr>
      <th>소속 언론사</th>
      <td>${reporter.reporterMedia}</td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td>${reporter.reporterPassword}</td>
    </tr>
    <tr>
      <th>전화번호</th>
    <td>${reporter.reporterMobile}</td>
    </tr>
  </table>
</div>
<%@include file="../component/footer.jsp"%>
</body>
</html>
