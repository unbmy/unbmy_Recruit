function usernameCheck(){
    let username = document.getElementById("user_register_username").value;
    // let identity = $('input[name="identity"]:checked').val();
    $.ajax({
        url: "/usernameCheck",
        type: "get",
        data: {"username": username},
        dataType: "text",
        error: function (status){
            alert(status);
        },
        success: function (data){
            if (data === "fail"){
                document.getElementById("register_login_textfield").classList.add("mdui-textfield-invalid");
                document.getElementById("register_username_err_msg").innerText = "该用户名已被注册！";
            } else if (data === "success"){
                document.getElementById("register_login_textfield").classList.remove("mdui-textfield-invalid");
                document.getElementById("register_username_err_msg").innerText = "用户名不能为空";
            }
        }
    })
}

function passwordCheck(){
    let password = document.getElementById("user_register_password").value;
    if (password.length < 8 || password.length > 16){
        document.getElementById("register_password_textfield").classList.add("mdui-textfield-invalid");
        document.getElementById("register_password_err_msg").innerText = "请输入8至16位长度密码";
    } else {
        document.getElementById("register_password_textfield").classList.remove("mdui-textfield-invalid");
        document.getElementById("register_password_err_msg").innerText = "密码不能为空";
    }
}

function repeatPasswordCheck(){
    let password = document.getElementById("user_register_password").value;
    let repeatPassword = document.getElementById("user_register_repeatPassword").value;
    if (password !== repeatPassword){
        document.getElementById("register_repeatPassword_textfield").classList.add("mdui-textfield-invalid");
        document.getElementById("register_repeatPassword_err_msg").innerText = "两次密码不一致";
    } else {
        document.getElementById("register_repeatPassword_textfield").classList.remove("mdui-textfield-invalid");
        document.getElementById("register_repeatPassword_err_msg").innerText = "密码不能为空";
    }
}

function getVerifyCode(){
    $.ajax({
        url: "/getVerifyCode",
        type: "get",
        data: "",
        dataType: "text",
        error: function (status){
            alert(status);
        },
        success: function (data){
            alert("您的验证码为：" + data);
        }
    })
}

function verifyCodeCheck(){
    let verifyCode = document.getElementById("user_register_verifyCode").value;
    $.ajax({
        url: "/verifyCodeCheck",
        type: "get",
        data: {"verifyCode": verifyCode},
        dataType: "text",
        error: function (status){
            alert(status);
        },
        success: function (data){
            if (data === "fail"){
                document.getElementById("register_verifyCode_textfield").classList.add("mdui-textfield-invalid");
                document.getElementById("register_verifyCode_err_msg").innerText = "两次验证码不一致！";
            } else if (data === "success"){
                document.getElementById("register_verifyCode_textfield").classList.remove("mdui-textfield-invalid");
                document.getElementById("register_verifyCode_err_msg").innerText = "请输入6位验证码";
            }
        }
    });
}