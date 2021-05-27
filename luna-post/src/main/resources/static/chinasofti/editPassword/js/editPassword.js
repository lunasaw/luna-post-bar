function subPassword() {
    var newPassword = $("#newPassword").val();
    var newPasswordCon = $("#newPasswordCon").val()
    var oldPassword = $("#oldPassword").val()


    if (typeof (newPassword) == 'undefined' || newPassword.trim() == "") {
        $("#tishi").html("新密码不能为空");
        return;
    }
    if (newPassword.trim().length != 6) {
        $("#tishi").html("新密码必须为6位");
        return;
    }
    if (typeof (newPasswordCon) == 'undefined' || newPasswordCon.trim() == "") {
        $("#tishi").html("确认密码不能为空");
        return;
    }
    if (newPassword.trim() != newPasswordCon.trim()) {
        $("#tishi").html("新密码与确认密码必须保持一致");
        return;
    }

    if (typeof (oldPassword) == 'undefined' || oldPassword.trim() == "") {
        $("#tishi").html("当前密码不能为空");
        return;
    }

    window.parent.location.replace("login.html");

}