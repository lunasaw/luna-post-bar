var everyPageDataCount = 7;
var postPageIndex = 1;
var postAllPage = 0;
$(document).ready(function () {
    KindEditor.options.cssData = 'body {font-family:微软雅黑;}',
        editor = KindEditor.create('textarea[id="POST_ADD_DES"]', {
            allowUpload: true,
            uploadJson: '/postbar/postController/kindEditorImgInput',
            allowFileManager: false,
            width: '900px',
            height: '300px',
            items: ['fullscreen', '|', 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                'table', 'hr', 'emoticons',]
        });


    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, 0, everyPageDataCount, true, "/post/post/api/pageListByEntity");

});


function returnPostList() {
    $("#POST_ADD_TITLE").val("");
    editor.html("");
    $("#tishi").html("");
    $("#POST_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#POST_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
}

function ADD_POST() {
    $("#POST_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#POST_ADD_DIV_ID").attr("style", "display:block;");//隐藏div
}


function addPostCheck() {
    var title = $("#POST_ADD_TITLE").val().trim();
    var text = editor.html().trim();

    if (title == "") {
        $("#tishi").html("文章标题不能为空");
        return;
    }
    if (title.length > 16) {
        $("#tishi").html("文章标题最多16个字符");
        return;
    }
    if (text == "") {
        $("#tishi").html("文章内容不能为空");
        return;
    }

    let tempPost = {
        postTitle: title,
        postText: text
    }
    insertPost(tempPost);
    returnPostList();
    let searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, postPageIndex, everyPageDataCount, true, "/post/post/api/pageListByEntity");


}

function insertPost(post) {
    $.ajax({
        type: "POST",
        url: "/post/post/api/insert",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(post),
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("新增失败", result.message);
            }

            if (data) {
                $.MsgBox.Alert("消息", "新增成功！");
                window.location.replace("post.html");
            } else {
                $.MsgBox.Alert("消息", "新增失败，请重试！");
            }
        }
    });
}

function getPostList(postTitle, pageStart, pageSize, SynOrAsyn, url) {
    let post = {
        postTitle: postTitle
    }
    // console.log(post)
    $.ajax({
        url: url + "/" + pageStart + "/" + pageSize, // url where to submit the request
        type: "GET", // type of action POST || GET
        data: post,
        sync: SynOrAsyn,
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
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
                $('.post_data').empty();
                for (let i in list) {
                    content = content + '<tr bgcolor="#FFFFFF"><td align="center" width="20">' +
                        ' <input name="DELETE_CHECK_NAME" type="checkbox" value="' + list[i].id + '"> </td>' +
                        '<td valign="center" align="center" width="30">' + list[i].postPageViews + ' </td>' +
                        '<td valign="center" align="center" width="30">' + list[i].postCommentSize + ' </td>' +
                        '<td valign="center" align="center" width="110"> ' +
                        '<a href="" onclick="post_detailed(\'' + list[i].id + '\');' +
                        ' return false;">' + list[i].postTitle + '</a></td>' +
                        '<td valign="center" align="center" width="110">' + list[i].username + ' </td>' +
                        '<td valign="center" align="center" width="100">' + list[i].createTime + '</td>' +
                        '<td valign="center" align="center" width="100">' + list[i].lastComment + '</td>' +
                        '</tr>';
                }
                $('.post_data').append(content);
            } else {
                $('.post_data').empty();
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


function showPostlist(admin, postList, postAllNum, allPage, pageIndex) {

}

function GOTO_POST_NEXT_PAGE() {

    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    postPageIndex = postPageIndex + 1
    getPostList(searchNameVal, postPageIndex, everyPageDataCount, true, "/post/post/api/pageListByEntity");
}

function GOTO_POST_TAIL_PAGE() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, postAllPage, everyPageDataCount, true, "/post/post/api/pageListByEntity");
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
    getPostList(searchNameVal, jumpVal, everyPageDataCount, true, "/post/post/api/pageListByEntity");
}


function GOTO_POST_HOME_PAGE() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, 0, everyPageDataCount, true, "/post/post/api/pageListByEntity");
}

function GOTO_POST_PREVIOUS_PAGE() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    postPageIndex = postPageIndex - 1;
    getPostList(searchNameVal, postPageIndex, everyPageDataCount, true, "/post/post/api/pageListByEntity");

}

function searchByPostName() {
    var searchNameVal = $("#SEARCH_POST_NAME").val().trim();
    getPostList(searchNameVal, 0, everyPageDataCount, true, "/post/post/api/pageListByEntity");
}

// 详情触发阅读函数
function post_detailed(postUUID) {
    readPost(postUUID);
    window.location.replace("comment.html?page=post&postid=" + postUUID);
}

function readPost(postId) {
    // 发送请求
    $.ajax({
        url: "/post/post/api/read/" + postId, // url where to submit the request
        type: "POST", // type of action POST || GET
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("消息", "系统错误，请联系管理员");
            }
        }
    });
}

function DELETE_POST() {
    var chk_value = [];
    $('input[name="DELETE_CHECK_NAME"]:checked').each(function () {
        chk_value.push($(this).val());
    });
    if (chk_value.length == 0) {
        $.MsgBox.Alert("消息", "请先选择需要删除的文章");
        return;
    }

    deletePost(chk_value);

}

function deletePost(ids) {
    // 发送请求
    $.ajax({
        url: "/post/post/api/delete", // url where to submit the request
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
            getPostList(searchNameVal, postPageIndex, everyPageDataCount, true, "/post/post/api/pageListByEntity");
        }
    });
    // window.location = "${ctx }/user/removeUser?ids=" + ids.get();
}

