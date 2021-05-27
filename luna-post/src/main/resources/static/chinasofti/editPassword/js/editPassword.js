function subPassword() {
    let newPassword = $("#newPassword").val();
    let newPasswordCon = $("#newPasswordCon").val()
    let oldPassword = $("#oldPassword").val()

    let password = {
        newPassword: newPassword,
        oldPassword: oldPassword
    }

    if (typeof (newPassword) == 'undefined' || newPassword.trim() === "") {
        $("#tishi").html("新密码不能为空");
        return;
    }
    if (newPassword.trim().length !== 6) {
        $("#tishi").html("新密码必须为6位");
        return;
    }
    if (typeof (newPasswordCon) == 'undefined' || newPasswordCon.trim() === "") {
        $("#tishi").html("确认密码不能为空");
        return;
    }
    if (newPassword.trim() !== newPasswordCon.trim()) {
        $("#tishi").html("新密码与确认密码必须保持一致");
        return;
    }

    if (typeof (oldPassword) == 'undefined' || oldPassword.trim() === "") {
        $("#tishi").html("当前密码不能为空");
        return;
    }

    editPassword(password);


}

function editPassword(editPassword) {
    $.ajax({
        type: "POST",
        url: "/post/api/editPassword",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(editPassword),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
            }
            if (data) {
                $.MsgBox.Alert("消息", "密码修改成功，请重新登录！");
                window.parent.location.replace("login.html");
            } else {
                $.MsgBox.Alert("消息", "密码修改失败，请重新注册！");
            }
        }
    });
}

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}