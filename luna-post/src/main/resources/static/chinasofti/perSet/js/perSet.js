$(document).ready(function () {


    var regage = $("#regAge");

    for (var i = 14; i < 100; i++) {
        regage.append("<option value='" + i + "'>" + i + "</option>");
    }
    $("#regUUID").val("0000000000");
    $("#userName").val("sjm");
    $("#regSex option[value='" + 0 + "']").attr("selected", true);
    $("#regAge option[value='" + 40 + "']").attr("selected", true);
    $("#regEmial").val("11@163.com");
    $("#oldUserName").val("sjm");


});


function subReg() {
    var regUUID = $("#regUUID").val();
    var userName = $("#userName").val();
    var regSex = $("#regSex").val();
    var regAge = $("#regAge").val();
    var regEmial = $("#regEmial").val();
    var oldName = $("#oldUserName").val();
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


    window.parent.location.replace("login.html");

}