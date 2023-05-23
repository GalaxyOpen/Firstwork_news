<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">

</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/reporter_nav.jsp"%>
${sessionScope.loginEmail} 님 환영해요!
<button onclick="update('${reporter.id}')">개인정보 수정하기</button><br>
<a href="/reporter/save">글 작성하기</a><br>
<a href="/article/list">글 목록보기</a><br>
<a href="/reporter/logout">로그아웃</a><br>
<a href="/">처음으로 돌아가기</a><br>
<%@include file="../component/footer.jsp"%>
</body>
<script>
    const update=(id)=>{
        location.href="/reporter/update?id="+id;
    }
</script>
</html>
