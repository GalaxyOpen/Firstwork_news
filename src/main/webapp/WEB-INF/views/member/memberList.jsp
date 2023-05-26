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
    <h2>댓글 회원 리스트</h2>
    <table>
        <tr>
            <th>번호</th>
            <th>레벨</th>
            <th>이름</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>비밀번호</th>
        </tr>
        <c:forEach items="${memberList}" var="member">
            <tr>
                <td>${member.id}</td>
                <td>${member.memberLevel}</td>
                <td>${member.memberName}</td>
                <td>${member.memberEmail}</td>
                <td>${member.memberMobile}</td>
                <td>${member.memberPassword}</td>

                <td><button onclick="member_detail('${member.id}')">조회</button></td>
                <td><button onclick="member_delete('${member.id}')">삭제</button></td>
            </tr>
        </c:forEach>
    </table>
    <div id="detail-area"></div>
    <a href="/">처음으로 돌아가기</a>
</div>

<%@include file="../component/footer.jsp"%>
</body>
<script>
    const member_detail=(id)=>{
        location.href="/member?id="+id;
        <%-- findById 메소드를 따라가야 함.  --%>
    }
    const member_delete=(id)=>{
        location.href="/member/delete?id="+id;
    }
    const member_detail_ajax=(id)=>{
        const resultarea = document.getElementById("detail-area")
        <%-- findAll 메소드를 따라가야 함. --%>
        $.ajax({
            type: "get",
            url:"/member/list",
            data:{
                "id":id
            },
            success: function(res){
                let result = "<table>"
                result += "<tr>"
                result += "<td>"+res.memberId+"</td>"
                result += "<td>"+res.memberLevel+"</td>"
                result += "<td>"+res.memberName+"</td>"
                result += "<td>"+res.memberEmail+"</td>"
                result += "<td>"+res.memberMobile+"</td>"
                result += "<td>"+res.memberPassword+"</td>"
                result += "</tr>"
                result += "</table>"
                resultarea.innerHTML = result;
            },
            error: function(){
                alert("일치하는 정보가 없습니다.")
            }
        })
    }
</script>
</html>
