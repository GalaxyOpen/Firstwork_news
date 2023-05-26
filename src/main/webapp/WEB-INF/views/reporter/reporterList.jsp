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
<h2>기자 회원 목록</h2>
  <table>
    <tr>
      <th>가입번호</th>
      <th>레벨</th>
      <th>기자성명</th>
      <th>기자이메일</th>
      <th>소속언론사</th>
    </tr>
    <c:forEach items="${reporterList}" var="reporter">
      <tr>
        <td onclick="reporter_detail_ajax('${reporter.id}')">${reporter.id}</td>
        <td>${reporter.reporterLevel}</td>
        <td>${reporter.reporterName}</td>
        <td>${reporter.reporterEmail}</td>
        <td>${reporter.reporterMedia}</td>

          <td><button onclick="reporter_detail('${reporter.id}')">기자 조회</button></td>
          <td><button onclick="reporter_delete('${reporter.id}')">기자 삭제</button></td>

      </tr>
    </c:forEach>
  </table>
  <div id="detail-area"></div>
  <a href="/">처음으로 돌아가기</a>
</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
  const reporter_detail=(id)=>{
    location.href="/reporter?id="+id;
  }
  const reporter_delete=(id)=>{
    location.href="/reporter/delete?id="+id;
  }
  const reporter_detail_ajax=(id)=>{
    const resultArea=document.getElementById("detail-area")
    $.ajax({
      type:"get",
      url: "/reporter/list",
      data: {
        "id": id
      },
      success: function(res){
        let result="<table>"
        result+="<tr>"
        result+="<td>"+res.reporterLevel+"</td>"
        result+="<td>"+res.reporterName+"</td>"
        result+="<td>"+res.reporterEmail+"</td>"
        result+="<td>"+res.reporterMedia+"</td>"
        result+="<td>"+res.reporterPassword+"</td>"
        result+="<td>"+res.reporterMobile+"</td>"
        result+="</tr>"
        result+="</table>"
        resultArea.innerHTML=result;
      },
      error: function(){
        alert("일치하는 정보가 없습니다")
      }
    })
  }
</script>
</html>
