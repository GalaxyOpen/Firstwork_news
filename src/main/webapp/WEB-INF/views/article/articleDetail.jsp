<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
</head>
<style>

</style>

<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
   <table>
       <tr>
           <td><${article.articleWriter}></td>
       </tr>
       <c:if test="${article.fileAttached == 1}">
           <tr>
               <td>
                   <c:forEach items ="${articlePictureList}" var="articlePicture">
                       <div id="center-image">
                       <img src="${pageContext.request.contextPath}/upload/${articlePicture.storedFileName}"
                            alt="" width="400" height="400">
                       </div>
                   </c:forEach>
               </td>
           </tr>
       </c:if>
       <tr>
           <td>
               <fmt:formatDate value="${article.articleUploadingTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
           </td>
       </tr>
       <tr>
           <th>조회수</th>
           <td>${article.articleHits}</td>
       </tr>
       <tr>
           <th>기사 내용</th>
           <td>${article.articleContents}</td>
       </tr>

   </table>
    <button onclick="article_list()">기사 목록</button>
        <c:if test="${sessionScope.reporterLoginEmail != null}">
            <button onclick="article_update()">기사 수정</button>
            <button onclick="article_delete()">기사 삭제</button>
        </c:if>
    <div id="commentArea">
        <c:if test="${sessionScope.loginEmail !=null}">
            <input type="text" id="commentWriter" value="${sessionScope.loginEmail}">
            <input type="text" id="commentContents" placeholder="댓글">
            <button onclick="comment_write()">댓글 작성</button>
        </c:if>

    </div>
    <div id="commentList">
        <c:choose>
            <c:when test="${commentList == null}">
                <h2>댓글이 없습니다.</h2>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>댓글번호</th>
                        <th>작성회원</th>
                        <th>작성내용</th>
                        <th>작성시간</th>
                    </tr>
                    <c:forEach items="${commentList}" var="comment">
                        <tr>
                            <td>${comment.id}</td>
                            <td>${comment.commentWriter}</td>
                            <td>${comment.commentContents}</td>
                            <td>
                                <fmt:formatDate value="${comment.commentCreatedDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
    <div id="heartArea">
        <div id="myHeart">
            <button onclick="goodUp()" <c:if test="${sessionScope.loginEmail==null}">disabled</c:if>><img id="img" src="/resources/images/full_heart.png" alt=""/></button>
        </div>
    </div>
    <c:if test="${sessionScope.loginEmail}">
        <a href="/member/myPage">홈으로 돌아가기</a>
    </c:if>
    <c:if test="${sessionScope.reporterLoginEmail}">
        <a href="/article/update">글 수정하기</a>
        <a href="/reporter/myPage">홈으로 돌아가기</a>
    </c:if>
</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
    const article_list =()=>{
        const type = '${type}'
        const q = '${q}'
        const page = '${page}'
        location.href="/article/paging?page="+page+"&type"+type+"&q"+q;
    }
    const article_update=()=>{
        const id='${article.id}'; // 기사를 수정할 때는 id 변수 값을 가지고 와야 하고 이를 가지고 변경 요청한다.
        location.href="/article/update?id="+id;
    }
    const article_delete=()=>{
        const id='${article.id}'; // 기사를 마찬가지로 삭제할 때에도 id 변수 값을 가지고 와야 하고 이를 가지고 삭제 요청한다.
        location.href="/article/delete?id="+id;
    }
    const comment_write =()=>{
        const commentWriter=document.getElementById("commentWriter").value;
        const commentContents=document.getElementById("commentContents").value;
        const articleId='${article.id}'
        const result=document.getElementById("commentList");
        $.ajax({
            type:"post",
            url: "/comment/save",
            data: {
                "commentContents" : commentContents,
                "commentWriter" : commentWriter,
                "articleId" : articleId
            },
            success: function (comment){
                console.log(comment);
                let output ="<table>";
                output+="<tr>"
                output+="<th>댓글번호</th>"
                output+="<th>작성회원</th>"
                output+="<th>작성내용</th>"
                output+="<th>작성시간</th>"
                output+="</tr>"
                for(let i in comment){
                    output+="<tr>"
                    output+="<td>"+comment[i].id+"</td>";
                    output+="<td>"+comment[i].commentWriter+"</td>";
                    output+="<td>"+comment[i].commentContents+"</td>";
                    output+="<td>"+moment(com[i].commentCreatedDate).format("YYYY-MM-DD HH:mm:ss")+"</td>";
                    output+="</tr>"
                }
                output+="</table>"
                result.innerHTML=output;
                document.getElementById("commentWriter").value="";
                document.getElementById("commentContents").value="";
            },
            error:function(){
                console.log("실패");
            }
        })
    }
    const goodUp=()=>{
        document.getElementById("img").src="/resources/images/heart.png";
        $.ajax({
            type: "post",
            url: "/goodUp",
            data: {
                "articleId" : '${article.id}',
                "memberId" : '${sessionScope.loginEmail}'
            },
<%-- 참고로 위에 이미지 파일도 같이 고쳐야 함 --%>
            success:function(){
                console.log("좋아요 성공");
            },
            error:function(){
                console.log("좋아요 실패");

            }
        })
    }

</script>
</html>
