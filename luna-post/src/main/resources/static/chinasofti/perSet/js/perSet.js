$(document).ready(function () {


    var regage = $("#regAge");

    for (var i = 14; i < 100; i++) {
        regage.append("<option value='" + i + "'>" + i + "</option>");
    }
    sysUser();
});


function subReg() {
    var regUUID = $("#regUUID").val();
    var userName = $("#userName").val();
    var regSex = $("#regSex").val();
    var regAge = $("#regAge").val();
    var regEmial = $("#regEmial").val();
    var oldName = $("#oldUserName").val();

    let userInfo = {
        id: regUUID,
        name: userName,
        sex: regSex,
        age: regAge,
        email: regEmial,
    }

    if (typeof (userName) == 'undefined' || userName.trim() == "") {
        $("#tishi").html("用户名不能为空");
        return;
    }
    if (userName.trim().length > 20) {
        $("#tishi").html("用户名不能大于20个字符");
        return;
    }


    if (typeof (regEmial) == 'undefined' || regEmial.trim() == "") {
        $("#tishi").html("邮箱地址不能为空");
        return;
    }

    if (!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(regEmial.trim()))) {
        $("#tishi").html("邮箱地址格式不正确");
        return false;
    }
    update(userInfo);
}

function sysUser() {
    $.ajax({
        type: "GET",
        url: "/post/user/api/sysUserInfo",
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
            }

            $("#regUUID").val(data.id);
            $("#userName").val(data.name);
            $("#regSex option[value='" + data.sex + "']").attr("selected", true);
            $("#regAge option[value='" + data.age + "']").attr("selected", true);
            $("#regEmial").val(data.email);
            $("#oldUserName").val(data.name);
        }
    });
}

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

function update(userInfo) {
    $.ajax({
        type: "PUT",
        url: "/post/user/api/userManage/updateOwner",
        async: true,
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(userInfo),
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, text) {
            $.MsgBox.Alert("消息", "出错了，请于管理员联系");
        },
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                // alert(JSON.stringify(error));
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
                return;
            }
            if (data) {
                $.MsgBox.Alert("消息", "修改成功");
            } else {
                $.MsgBox.Alert("消息", "修改失败");
            }
        }
    });
}