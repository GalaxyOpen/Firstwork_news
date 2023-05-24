<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Title</title>

  <link rel="stylesheet" href="/resources/css/bootstrap.css">
  <link rel="stylesheet" href="../resources/css/main.css">
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
    <title>Title</title>
</head>
<body>
<%@include file="../component/header.jsp" %>
<%@include file="../component/nav.jsp" %>
<div id="section">
  <div class="container" id="search-area">
    <form action="/article/paging">
     <select name="type">
       <option value="articleTitle">기사 제목</option>
       <option value="${articleDTO.articleWrtier}">작성 기자</option>
     </select>
     <input type="text" name="q" placeholder="검색어를 입력하세요">
     <input type="submit" value="검색">
    </form>
  </div>
  <div class="container" id="list">
    <table class="table table-striped table-hover text-center">
      <tr>
        <th>기사 번호</th>
        <th>기사 제목</th>
        <th>작성 기자</th>
        <th>작성 날짜</th>
        <th>기사 조회수</th>
      </tr>

      <c:forEach items="${articleList}" var="article">
        <tr>
          <td>${article.id}</td>
          <td>
            <a href="/article?id=${article.id}${paging.page}&q=${q}&type=${type}">${article.articleTitle}</a>
          </td>
          <td>
            <fmt:formatDate value="${article.articleUploadingTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
          </td>
          <td>${article.articleHits}</td>
        </tr>
      </c:forEach>

    </table>
  </div>
  <div class="container">
    <ul class="pagination justify-content-center">
      <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줍니다. --%>
        <c:when test="${paging.page<=1}">
          <li calss="page-item disabled">
            <a class="page-link">[이전]</a>
          </li>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 이전을 클릭할 때 현재 페이지보다 1 작은 페이지를 요청합니다. --%>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link" href="/article/paging?page=${paging.page-1}&q=${q}&type${type}">[이전]</a>
          </li>
        </c:otherwise>
      </c:choose>
      <%-- 중간 페이지 설명--%>
      <%-- for(int 1=startpage; i<=endPage; i++) <= 바로 아랫줄 c:forEach구문 설명한 것 --%>
      <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
          <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 하는 구문 --%>
          <c:when test="${i eq paging.page}">
            <li class="page-item active">
              <a class="page-link">${i}</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="page-item">
              <a class="page-link" href="/article/paging?page=${i}&q=${q}&type${type}">${i}</a>
            </li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
          <li class="page-item disabled">
            <a class="page-link">[다음]</a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link" href="/article/paging?page=${paging.page+1}&q=${q}&type${type}">[다음]</a>
          </li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
  <c:if test="${sessionScope.reporterLoginEmail !=null}">
  <a href="/article/save">글 작성하기</a>
  <a href="/reporter/mypage">MyHome</a>
  </c:if>
  <c:if test="${sessionScope.loginEmail != null}">
  <a href="/member/myPage">MyHome</a>
  </c:if>
  <c:if test="${sessionScope.reporterLoginEmail == admin && sessionScope.loginEmail == admin}">
    <a href="/reporter/list">기자 목록</a>
    <a href="/member/list">댓글러 목록</a>
  </c:if>
</div>
<%@include file="../component/footer.jsp" %>
</body>
</html>
