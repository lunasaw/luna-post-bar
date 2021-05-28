var everyPageDataCount = 7;
var postPageIndex = 1;
var postAllPage = 0;
$(document).ready(function () {

    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, 1, everyPageDataCount, true, "/post/user/api/showUserPageList");

    var regage = $("#regAge");
    var selectPotion = $("#regAge option");

    if (selectPotion.length == "0") {
        for (var i = 14; i < 100; i++) {
            regage.append("<option value='" + i + "'>" + i + "</option>");
        }
    }

});

function getPostList(postTitle, pageStart, pageSize, SynOrAsyn, url) {
    let userInfo = {
        name: postTitle
    }
    console.log(userInfo)
    $.ajax({
        url: url + "/" + pageStart + "/" + pageSize, // url where to submit the request
        type: "GET", // type of action POST || GET
        data: userInfo,
        sync: SynOrAsyn,
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                // alert(JSON.stringify(error));
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
                return;
            }

            console.log(data);
            if (data == null) {
                return;
            }

            // 当前页面开始记录数目
            $("#data_count_start").text(data.startRow);
            // 当前页面结束记录数
            $("#data_count_end").text(data.endRow);
            // 总页数
            $("#page_count").text(data.pages);
            postAllPage = data.pages;
            // 总计
            $("#data_count").text(data.total);
            // 当前页数
            $("#page_num").text(data.pageNum);
            if (data.isFirstPage == true) {
                $("#page_pre_btn").attr("disabled", "disabled")
            } else {
                $("#page_pre_btn").removeAttr("disabled");
            }

            if (data.isLastPage == true) {
                $("#page_next_btn").attr("disabled", "disabled")
            } else {
                $("#page_next_btn").removeAttr("disabled");
            }


            // 渲染页面
            let list = data.list;
            if (list.length > 0) {
                let content = '';
                $('.user_data').empty();
                for (let i in list) {
                    content = content + '<tr bgcolor="#FFFFFF">' +
                        '<td align="center" width="20"> ' +
                        '<input name="DELETE_CHECK_NAME" type="checkbox" value="' + list[i].id + '"> </td><td valign="center" align="center" width="30">' +
                        ' <a href="" onclick="EDIT_USER(\'' + list[i].id + '\',\'' + list[i].name + '\',\'' + (list[i].sex == "男" ? 0 : 1) + '\',\'' + list[i].age + ' \',\'' + list[i].email + '\',\'' + (list[i].admin == "管理员" ? 0 : 1) + '\'); return false;">' +
                        '' + list[i].name + '</a></td>' +
                        '<td valign="center" align="center" width="110">' + list[i].email + '</td>' +
                        '<td valign="center" align="center" width="30">' + list[i].age + ' </td>' +
                        '<td valign="center" align="center" width="110">' + list[i].sex + '</td>' +
                        '<td valign="center" align="center" width="110">' + list[i].createTime + '</td>' +
                        '<td valign="center" align="center" width="100">' + list[i].loginTime + ' </td>' +
                        '<td valign="center" align="center" width="100">' + list[i].admin + ' </td>' +
                        '</tr>'
                }
                $('.user_data').append(content);
            } else {
                $('.user_data').empty();
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

function showPostlist(registerList, postAllNum, allPage, pageIndex) {

}

function GOTO_POST_NEXT_PAGE() {

    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    postPageIndex = postPageIndex + 1;
    getPostList(searchNameVal, postPageIndex, everyPageDataCount, true, "/post/user/api/showUserPageList");
}

function GOTO_POST_TAIL_PAGE() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, postAllPage, everyPageDataCount, true, "/post/user/api/showUserPageList");
}

function GOTO_POST_PAGE() {
    var jumpVal = $("#JUMP_INPUT_ID").val().trim();
    if (jumpVal == "") {
        $.MsgBox.Alert("消息", "跳转页不能为空");
        return;
    }
    if (!(/^[0-9]+$/.test(jumpVal))) {
        $.MsgBox.Alert("消息", "页码必须为数字");
        return;
    }
    if (jumpVal <= 0) {
        $.MsgBox.Alert("消息", "页码必须大于等于1");
        return;
    }
    if (jumpVal > postAllPage) {
        $.MsgBox.Alert("消息", "页码超出上限");
        return;
    }
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, jumpVal, everyPageDataCount, true, "/post/user/api/showUserPageList");
}


function GOTO_POST_HOME_PAGE() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, 0, everyPageDataCount, true, "/post/user/api/showUserPageList");
}

function GOTO_POST_PREVIOUS_PAGE() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    postPageIndex = postPageIndex - 1;
    getPostList(searchNameVal, postPageIndex, everyPageDataCount, true, "/post/user/api/showUserPageList");

}

function searchByPostName() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, 0, everyPageDataCount, true, "/post/user/api/showUserPageList");
}

function DELETE_POST() {
    var chk_value = [];
    $('input[name="DELETE_CHECK_NAME"]:checked').each(function () {
        chk_value.push($(this).val());
    });
    if (chk_value.length == 0) {
        $.MsgBox.Alert("消息", "请先选择需要删除的用户");
        return;
    }

    deleteUser(chk_value);

}

function deleteUser(ids) {
    // 发送请求
    $.ajax({
        url: "/post/user/api/delete", // url where to submit the request
        type: "DELETE", // type of action POST || GET
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        data: JSON.stringify(ids),
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                alert(JSON.stringify(error));
                return;
            }
            console.log(data);

            $.MsgBox.Alert("消息", "删除成功");
            let searchNameVal = $("#SEARCH_POST_NAME").val().trim();
            getPostList(searchNameVal, postPageIndex, everyPageDataCount, true, "/post/user/api/showUserPageList");
        }
    });
    // window.location = "${ctx }/user/removeUser?ids=" + ids.get();
}

function editUserCheck() {
    var userUUID = $("#userUUID").val();
    var userName = $("#userName").val();
    var regsex = $("#regsex").val();
    var regAge = $("#regAge").val();
    var regEmial = $("#regEmial").val();
    var admin = $("#admin").val();
    var password = $("#password").val();
    var oldName = $("#oldUserName").val();

    let userInfo = {
        id : userUUID,
        name: userName,
        sex: regsex,
        age: regAge,
        admin: admin,
        email: regEmial,
        password: password
    }

    console.log(JSON.stringify(userInfo))


    if (typeof (userName) == 'undefined' || userName.trim() == "") {
        $("#tishi").html("用户名不能为空");
        return;
    }
    if (userName.trim().length > 20) {
        $("#tishi").html("用户名不能大于20个字符");
        return;
    }

    if (password.trim() != "" && password.trim().length != 6) {
        $("#tishi").html("密码必须为6位");
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

function update(userInfo){
    $.ajax({
        type: "PUT",
        url: "/post/user/api/userManage/update",
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
                $("#userUUID").val("");
                $("#userName").val("");
                $("#regsex").val("");
                $("#regAge").val("");
                $("#regEmial").val("");
                $("#admin").val("");
                $("#password").val("");
                $("#oldUserName").val("");
                $("#tishi").html("");
                $("#POST_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
                $("#POST_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
                var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
                getPostList(searchNameVal, 0, everyPageDataCount, true, "/post/user/api/showUserPageList");

            } else {
                $.MsgBox.Alert("消息", "修改失败");
            }
        }
    });
}

function returnPostList() {
    $("#userUUID").val("");
    $("#userName").val("");
    $("#regsex").val("");
    $("#regAge").val("");
    $("#regEmial").val("");
    $("#admin").val("");
    $("#password").val("");
    $("#oldUserName").val("");
    $("#tishi").html("");
    $("#POST_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#POST_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
}

function EDIT_USER(userUUID, userName, regsex, regAge, regEmial, admin) {

    $("#POST_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#POST_ADD_DIV_ID").attr("style", "display:block;");//隐藏div

    $("#userUUID").val(userUUID);
    $("#userName").val(userName);
    $("#regsex").val(regsex);
    $("#regAge").val(regAge);
    $("#regEmial").val(regEmial);
    $("#admin").val(admin);
    $("#oldUserName").val(userName);
}