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
<%@include file="../component/nav.jsp"%>
<div id="section">
    <div style="display: grid; place-items: center">
        <form action="/reporter/save" method="post" enctype="multipart/form-data">
            <div class="mb-3">
            <label for="reporter_Email" class="form-label"> ID: </label>
            <input type="text" id="reporter_Email" name="reporterEmail" onblur="reporter_duplicate_check()" placeholder="아이디를 입력해주세요"><br>
            <p id="email-area"></p>
            </div>
            <div class="mb-3">
            <label for="reporterPassword" class="form-label"> 비밀번호: </label>
            <input type="text" id="reporterPassword" name="reporterPassword" onblur="password_check()" placeholder="비밀번호를 입력해주세요"><br>
            <p id="password-area"></p>
            </div>
            <div class="mb-3">
            <label for="reporterName" class="form-label"> 이름: </label>
            <input type="text" id="reporterName" name="reporterName" placeholder="이름을 입력해주세요"><br>
            <p id="name-area"></p>
            </div>
            <div class="mb-3">
            <label for="reporterMedia" class="form-label">언론사: </label>
            <input type="text" id="reporterMedia" name="reporterMedia" placeholder="언론사 기재"><br>
            <p id="media-area"></p>
            </div>
            <div class="mb-3">
            <label for="reporterMobile" class="form-label">휴대전화 :</label>
            <input type="text" id="reporterMobile" name="reporterMobile" onblur="mobile_check()" placeholder="010-xxxx-xxxx"><br>
            <p id="mobile-area"></p>
            </div>
            사진 : <input type="file" name="reporterPicture" multiple><br>
            <input type="submit" class="btn btn-dark" value="회원가입">
        </form>
    </div>
</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
    const reporter_duplicate_check =()=>{
        const typing = document.getElementById("reporter_Email").value;
        const email_check = document.getElementById("email-area");
        $.ajax({
            type: "post",
            url: "/reporterIdCheck",
            data: {
                "reporterEmail":typing
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
        const password = document.getElementById("reporterPassword").value;
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
        const mobile=document.getElementById("reporterMobile").value;
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
