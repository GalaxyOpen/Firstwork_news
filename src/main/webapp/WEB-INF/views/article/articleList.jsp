<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="/resources/css/main.css">
  <style>
    #section a{
      display : block;
    }
  </style>
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  <form action="/article/list"></form>
  <h2>기사 리스트</h2>
  <table>
    <tr>
      <th>기사번호</th>
      <th>기사제목</th>
      <th>작성기자</th>
      <th>작성시간</th>
      <th>기사내용</th>
    </tr>
    <c:forEach items="${articleList}" var="article">
      <tr>
        <td>${article.id}</td>
        <td><a href="/article?id=${article.id}">${article.articleTitle}</a></td>
        <%-- 여기서 /article의 경우 findById 메소드 --%>
        <td>${article.articleWriter}</td>
        <td>${article.articleUploadingTime}</td>
        <td>${article.articleContents}</td>
      </tr>
    </c:forEach>
  </table>

</div>
<%@include file="../component/footer.jsp"%>
</body>

</html>
