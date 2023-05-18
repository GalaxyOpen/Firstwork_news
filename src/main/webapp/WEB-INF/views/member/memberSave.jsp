<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
</head>
<body>
<%@include file="../component/header.jsp"%>

<div id="section">
    <div style="display: grid; place-items: center">
    <form action="/member/save" method="post">
        <div class="mb-3">
        <label for="memberEmail" class="form-label">아이디(이메일):</label>
        <input type="text" class="form-control" id="memberEmail" name="memberEmail" onblur="email_duplicate_check()" placeholder="아이디를 입력해주세요">
        <p id="email-area"></p>
        </div>
        <div class="mb-3">
        <label for="memberPassword" class="form-label">비밀번호:</label>
        <input type="text" id="memberPassword" class="form-control" name="memberPassword" onblur="password_check()" placeholder="비밀번호를 입력해주세요">
        <p id="password-area"></p>
        </div>
        <div class="mb-3">
        <label for="memberName" class="form-label">이름:</label>
        <input type="text" id="memberName" class="form-control" name="memberName" placeholder="이름을 입력해주세요">
        <p id="name-area"></p>
        </div>
        <div class="mb-3">
        <label for="memberMobile" class="form-label">Phone number :</label>
        <input type="text" id="memberMobile" class="form-control" name="memberMobile" onblur="mobile_check()" placeholder="010-xxxx-xxxx">
        <p id="mobile-area"></p>
        </div>
        <input type="submit" class="btn btn-dark" value="회원가입">
    </form>
    </div>
</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
    const email_duplicate_check =()=>{
        const typingEmail = document.getElementById("memberEmail").value;
        const email_check = document.getElementById("email-area");
        $.ajax({
            type: "post",
            url: "/memberIdCheck",
            data: {
                "memberEmail":typingEmail
            },
            success: function(){
                email_check.innerHTML="사용가능한 이메일 주소입니다.";
                email_check.style.color="green";
            },
            error :function(){
                email_check.innerHTML="중복되었거나 올바르지 않은 이메일 주소입니다.";
                email_check.style.color="red";
            }
        })
    }
    const password_check =()=>{
        const password = document.getElementById("memberPassword").value;
        const passwordCheck = document.getElementById("password-area");
        const exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[-_!#])[A-Za-z\d-_!#]{8,16}$/;

        if(password.length==0){
            passwordCheck.innerHTML="비밀번호를 입력해주세요";
            passwordCheck.style.color="red";
        }else if(!password.match(exp)){
            passwordCheck.innerHTML="8~16자리 특수문자,대소문자,숫자를 입력해주세요."
            passwordCheck.style.color="red";
        }else{
            passwordCheck.innerHTML="비밀번호 사용이 가능합니다.";
            passwordCheck.style.color= "green";
        }
    }
    const mobile_check=()=>{
        const mobile=document.getElementById("memberMobile").value;
        const mobileCheck =document.getElementById("mobile-area")
        const exp = /^\d{3}-\d{4}-\d{4}$/
        if(mobile.match(exp)){
            mobileCheck.innerHTML="전화번호가 입력되었습니다.";
            mobileCheck.style.color="green";
        }else{
            mobileCheck.innerHTML="형식에 맞게 다시 입력해주세요.";
            mobileCheck.style.color="red";
        }
    }
</script>
</html>
