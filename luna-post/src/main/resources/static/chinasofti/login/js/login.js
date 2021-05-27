var uk = "_uk_", pk = "_pk_", rk = "_rk_";
$(function () {
    $("#username").focus();
    $("#username").val($.localStorage.get(uk));
    $("#password").val($.localStorage.get(pk));
    if ($.localStorage.get(rk)) {
        $("#rememberPwd").attr("checked", true);
    }

});

function subLogin() {
    let $msg = $("#message"), $usrname = $("#username"), $pwd = $("#password"), $rememberPwd = $("#rememberPwd");
    let usrname = $usrname.val() || "";

    if (usrname.trim().length === 0) {
        $("#tishi").html("用户名不能为空");
        return;
    }
    let pwd = $pwd.val() || "";
    if (pwd.trim().length === 0) {
        $("#tishi").html("密码不能为空!");
        return;
    }
    let rememberPwd;
    if ($rememberPwd.is(':checked')) {
        $.localStorage.set(uk, usrname);
        $.localStorage.set(pk, pwd);
        $.localStorage.set(rk, true);
        rememberPwd = "no";
    } else {
        $.localStorage.remove(uk);
        $.localStorage.remove(pk);
        $.localStorage.remove(rk);
        rememberPwd = "off";
    }

    let user = {
        username: usrname,
        password: pwd,
        rememberPwd: rememberPwd
    }

    send(user);
    console.log(JSON.stringify(user))
    $(".btn-login").unbind('click');
    console.log(pwd);

    /** 提交表单 */
    //$("#loginForm").submit();
    // window.location.replace("menu.html?menuUserName=sjm");

    //alert(JSON.stringify(json));


}

function send(user) {
    $.ajax({
        type: "POST",
        url: "/post/api/login",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(user),
        // data: serializeFormData($('.form-sign')),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("登陆失败", result.message);
                return;
            }

            // console.log(data);
            // if (data == null) {
            // 	alert(JSON.stringify(result));
            // 	return;
            // }
            window.location.replace("menu.html");
        }
    });
}

function register(user) {
    $.ajax({
        type: "POST",
        url: "/post/api/register",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(user),
        // data: serializeFormData($('.form-sign')),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("注册失败", result.message);
            }

            if (data) {
                $.MsgBox.Alert("消息", "注册成功，请重新登录！");
                gotoLogin();
            }else {
                $.MsgBox.Alert("消息", "注册失败，请重新注册！");
            }
        }
    });
}

//serialize form data
function serializeFormData($form) {
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return JSON.stringify(indexed_array);
}

function checkResultAndGetData($result) {
    if ($result.success == false) {
        throw $result;
    }
    return $result.data;
}

if (window != top) {
    top.location.href = location.href;
}

function subReg() {

    let userName = $("#regusername").val();
    let password = $("#regpassword").val();
    let passwordCon = $("#regpasswordCon").val()
    let regSex = $("#regsex").val();
    let regAge = $("#regAge").val();
    let regEmial = $("#regEmial").val();

    let registerUser = {
        username: userName,
        password: password,
        ensurePassword: passwordCon,
        age: regAge,
        sex: regSex,
        email: regEmial
    }

    if (typeof (userName) == 'undefined' || userName.trim() === "") {
        $("#zucetishi").html("用户名不能为空");
        return;
    }
    if (userName.trim().length > 20) {
        $("#zucetishi").html("用户名不能大于20个字符");
        return;
    }
    if (typeof (password) == 'undefined' || password.trim() === "") {
        $("#zucetishi").html("密码不能为空");
        return;
    }
    if (password.trim().length !== 6) {
        $("#zucetishi").html("密码必须为6位");
        return;
    }
    if (typeof (passwordCon) == 'undefined' || passwordCon.trim() === "") {
        $("#zucetishi").html("确认密码不能为空");
        return;
    }
    if (password.trim() !== passwordCon.trim()) {
        $("#zucetishi").html("密码与确认密码必须保持一致");
        return;
    }
    if (typeof (regEmial) == 'undefined' || regEmial.trim() === "") {
        $("#zucetishi").html("邮箱地址不能为空");
        return;
    }

    if (!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(regEmial.trim()))) {
        $("#zucetishi").html("邮箱地址格式不正确");
        return;
    }

    register(registerUser);
}

function gotoregister() {
    $("#loginbox").hide();
    $("#registerbox").show();

    $("#regusername").val("");
    $("#regpassword").val("");
    $("#regpasswordCon").val("")
    $("#regsex").val("0");
    $("#zucetishi").html("");
    let regage = $("#regAge");
    let selectPotion = $("#regAge option");

    if (selectPotion.length == "0") {
        for (let i = 14; i < 100; i++) {
            regage.append("<option value='" + i + "'>" + i + "</option>");
        }
    } else {
        regage.val("14");
    }

    $("#regEmial").val("");
}

function gotoLogin() {
    $("#loginbox").show();
    $("#registerbox").hide();

    layui.use("layer");
    $("#username").focus();
    $("#username").val($.localStorage.get(uk));
    $("#password").val($.localStorage.get(pk));
    $("#tishi").html("");
    if ($.localStorage.get(rk)) {
        $("#rememberPwd").attr("checked", true);
    }
}