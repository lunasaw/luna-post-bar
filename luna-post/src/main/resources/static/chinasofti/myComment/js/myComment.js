var everyPageDataCount = 10;
var postPageIndex = 1;
var postAllPage = 0;

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

$(function () {

    KindEditor.options.cssData = 'body {font-family:微软雅黑;}',
        editor = KindEditor.create('textarea[id="COM_ADD_DES"]', {
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

    getPostList(true, "/post/comment/api/myPageListByEntity/", 1, everyPageDataCount);
});


function getPostList(SynOrAsyn, url, pageStart, pageSize) {
    let comment = {}
    console.log(comment)
    $.ajax({
        url: url + "/" + pageStart + "/" + pageSize, // url where to submit the request
        type: "GET", // type of action POST || GET
        data: comment,
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
                $('#myComment').empty();
                for (let i in list) {
                    content = content + '<div class="row">' +
                        '<div class="form-inline col-sm-12">' +
                        '<span>' + list[i].content + '</span>' +
                        '</div><div class="col-sm-12">' +
                        '<audio src="' + list[i].audio + '" controls="controls" style="height:20px">' +
                        '</audio></div><div class="col-sm-12"><div><table>' +
                        '<tbody><tr><td>评论时间：' + list[i].modifiedTime + '&nbsp;&nbsp;|&nbsp;&nbsp;</td>' +
                        '<td>评论文章：<a href="" onclick="post_detailed(\'' + list[i].postId + '\'); ' +
                        'return false;">' + list[i].postTitle + '</a>&nbsp;&nbsp;|&nbsp;&nbsp;</td><td><a href="" ' +
                        'onclick="EDIT_COM(\'' + list[i].id + '\'); return false;">评论编辑</a>：&nbsp;&nbsp;|&nbsp;&nbsp;</td>' +
                        '<td>删除评论：<input name="DELETE_CHECK_NAME" type="checkbox" value="' + list[i].id + '"></td></tr></tbody>' +
                        '</table></div>' +
                        '</div></div><hr><hr>';
                }
                $('#myComment').append(content);
            } else {
                $('#myComment').empty();
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

function allCommentlist(allCommentlist, postAllNum, allPage, pageIndex) {

}

function post_detailed(postUUID) {

    window.location.replace("comment.html?page=myCom&postid=" + postUUID);

}

function DELETE_COM() {
    var chk_value = [];
    $('input[name="DELETE_CHECK_NAME"]:checked').each(function () {
        chk_value.push($(this).val());
    });
    if (chk_value.length == 0) {
        $.MsgBox.Alert("消息", "请先选择需要删除的评论！");
        return;
    }

    deletePost(chk_value);
}

function deletePost(ids) {
    // 发送请求
    $.ajax({
        url: "/post/comment/api/delete", // url where to submit the request
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
            getPostList(true, "/post/comment/api/myPageListByEntity/", postPageIndex, everyPageDataCount);
        }
    });
    // window.location = "${ctx }/user/removeUser?ids=" + ids.get();
}

function returnComList() {
    editor.html("");
    $("#tishi").html("");
    $("#COM_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#COM_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
}

function EDIT_COM(cmUUID) {

    editor.html("测试评论1");
    var html = "";
    html += '<button type="button" class="btn btn-info" onclick="editComCheck(\'' + cmUUID + '\')">编辑</button>';
    html += '<button type="button" class="btn btn-default" onclick="returnComList()">返回</button>';
    $("#editButtion").html(html);


    $("#COM_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#COM_ADD_DIV_ID").attr("style", "display:block;");//隐藏div

}

function GOTO_POST_NEXT_PAGE() {

    postPageIndex = postPageIndex + 1;
    getPostList(true, "/post/comment/api/myPageListByEntity/", postPageIndex, everyPageDataCount);

}

function GOTO_POST_TAIL_PAGE() {

    getPostList(true, "/post/comment/api/myPageListByEntity/", postAllPage, everyPageDataCount);

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

    getPostList(true, "/post/comment/api/myPageListByEntity/", jumpVal, everyPageDataCount);
}


function GOTO_POST_HOME_PAGE() {
    getPostList(true, "/post/comment/api/myPageListByEntity/", 1, everyPageDataCount);
}

function GOTO_POST_PREVIOUS_PAGE() {
    console.log(postPageIndex)
    postPageIndex = postPageIndex - 1;
    getPostList(true, "/post/comment/api/myPageListByEntity/", postPageIndex, everyPageDataCount);

}

function editComCheck(cmUUID) {
    var cmText = editor.html().trim();

    if (cmText == "") {
        $("#tishi").html("评论内容不能为空");
        return;
    }


    window.location.replace("myComment.html?radm=" + Math.random());


}
