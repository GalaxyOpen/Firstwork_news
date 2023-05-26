<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="nav">
    <ul>
        <li>
            <a href="">
                <i class="bi bi-house"></i>
            </a>

        <c:if test="${sessionScope.reporterLoginEmail == null && sessionScope.loginEmail == null}">
        <li>
            <a href="/article/paging">기사목록</a>
        </li>
        <li>
            <a href="/member/save">댓글회원 가입</a>
        </li>
        <li>
            <a href="/reporter/save">기자회원 가입</a>
        </li>
        <li>
            <a href="/member/login">댓글 회원 로그인</a>
        </li>
        <li>
            <a href="/reporter/login">기자 회원 로그인</a>
        </li>
        </c:if>

        <c:if test="${sessionScope.reporterLoginEmail !=null}">
        <li>
            <a href="/reporter/myPage">마이페이지</a>
        </li>
        </c:if>

        <c:if test="${sessionScope.loginEmail !=null}">
            <a href="/member/myPage">마이페이지</a>
        </c:if>


        <c:if test="${sessionScope.reporterLoginEmail == 'admin' || sessionScope.loginEmail == 'admin'}">
        <li>
            <a href="/member/list">회원목록</a>
            <a href="/reporter/list">기자회원 목록</a>
        </li>
        </c:if>
</div>
<%--        <li class="login-name" id="login-area">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${sessionScope.loginEmail != null}">--%>
<%--                    <a href="/mypage" style="color: black;">${sessionScope.loginEmail} 님 환영해요!</a>--%>
<%--                    <a href="/logout">logout</a>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <a href="/login">login</a>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </li>--%>

<li class="login-name" id="login-area">

</li>
</ul>
</div>
<script>
    const loginArea = document.getElementById("login-area");
    const loginEmail= '${sessionScope.reporterLoginEmail}'
    const loginEmail2= '${sessionScope.loginEmail}'
    console.log(loginEmail.length);

    if(loginEmail.length !=0){
        loginArea.innerHTML = "<a href='/reporter/myPage' style='color: black;'>"+loginEmail +"님 어서오세요!</a>"+
            "<a href='/reporter/logout'>logout</a>";
    }else if(loginEmail2.length !=0){
        loginArea.innerHTML= "<a href='/member/myPage' style='color: black;'>"+loginEmail2+"님 어서오세요!</a>"+
            "<a href='/member/logout'>logout</a>"
    }else{
        loginArea.innerHTML = "<a href=/>처음으로</a>";
    }
</script>