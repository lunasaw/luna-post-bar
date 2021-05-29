var everyPageDataCount = 7;
var postPageIndex = 0;
var postAllPage = 0;
var postUUID = "";
var returnpage = ""
var hostComment

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

    postUUID = GetQueryString("postid");
    returnpage = GetQueryString("page");

    getCommentList(postUUID, true, "/post/comment/api/list");


    getHost(postUUID, true, "/post/comment/api/getHot");

    getPostDetail(postUUID, true, "/post/post/api/getDetail");
});

function getPostDetail(postUUID, SynOrAsyn, url) {
    $.ajax({
        url: url + "/" + postUUID, // url where to submit the request
        type: "GET", // type of action POST || GET
        sync: SynOrAsyn,
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                // alert(JSON.stringify(data));
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
                return;
            }

            console.log(data);
            if (data == null) {
                return;
            }
            // 渲染页面
            if (data !== null) {
                let user = data.showUserDTO;
                let post = data.postDTO
                let content = '';
                $('#post_data').empty();

                content = content + '<div class="card-body"><div class="row"><div class="col-md-11">' +
                    '<div class="form-inline col-md-11" style="width:100%"><div class="form-group " style="width:100%">' +
                    '<div id="postPhoto"><img src="' + user.photo + '" style="whith:80px;height:80px"></div>&nbsp;&nbsp;' +
                    '<div id="postInfo"><table>' +
                    '<tbody><tr>' +
                    '<td>主题：' + post.postTitle + '</td></tr>' +
                    '<tr><td>发帖人：' + user.name + '</td></tr>' +
                    '<tr><td>发帖人注册时间: ' + user.createTime + '</td></tr>' +
                    '<tr><td>发帖时间：' + post.createTime + '</td></tr>' +
                    '</tbody></table></div></div></div></div></div></div>' +
                    '<div id="postText"><span>' + post.postText + '</span></div>' +
                    '<div id="postAtt"><audio src="' + post.postAudio + '" controls="controls" ' +
                    'style="height:20px"></audio>&nbsp;|&nbsp;<a id="praisecNum"  href="javascript:void(0);" ' +
                    'onclick="hotsPraiseClick(\'' + post.id + '\',\'' + null + '\')">赞：' +
                    '</a> <span id="postPraise">' + post.praise + '</span></div>';

                $('#post_data').append(content);
            } else {
                $('#post_data').empty();
            }
        }
    });
}

function getHost(postUUID, SynOrAsyn, url) {
    let comment = {
        postId: postUUID
    }
    // console.log(comment)
    $.ajax({
        url: url, // url where to submit the request
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

            // console.log(data);
            if (data == null) {
                return;
            }
            // 渲染页面
            if (data !== null) {
                let content = '';
                $('#comment_hot_data').empty();

                hostComment = data.id;
                content = content + '<div class="form-inline col-sm-12">' +
                    '<div><img src="' + data.photo + '" style="whith:80px;height:80px"></div>&nbsp;&nbsp;' +
                    '<div>' +
                    '<table>' +
                    '<tbody>' +
                    '<tr><td>评论人：' + data.username + '</td></tr>' +
                    '<tr><td>评论人注册时间:' + data.userTime + '</td></tr>' +
                    '<tr><td>评论时间：' + data.createTime + '</td></tr>' +
                    '<tr><td>删除选中：<input name="DELETE_CHECK_NAME" type="checkbox" value="' + data.id + '"></td></tr>' +
                    '</tbody></table></div></div><div class="col-sm-12">' +
                    '<span>' + data.content + '</span></div><div class="col-sm-12">' +
                    '<audio src="' + data.audio + '" controls="controls" style="height:20px"></audio>' +
                    '&nbsp;|&nbsp;<a id="praisecNum" href="javascript:void(0);" ' +
                    'onclick="hotPraise(\'' + data.postId + '\',\'' + data.id + '\')">' +
                    '赞：</a> <span id="hotComment' + data.id + '">' + data.postPraise + '</span> </div>';
                $('#comment_hot_data').append(content);
            } else {
                $('#comment_hot_data').empty();
            }
        }
    });
}

function getCommentList(postUUID, SynOrAsyn, url) {
    let comment = {
        postId: postUUID
    }
    // console.log(comment)
    $.ajax({
        url: url, // url where to submit the request
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
            // 渲染页面
            let list = data;
            if (list.length > 0) {
                let content = '';
                $('#comment_data').empty();
                for (let i in list) {

                    content = content + '<div class="form-inline col-sm-12">' +
                        '<div><img src="' + list[i].photo + '" style="whith:80px;height:80px"></div>&nbsp;&nbsp;' +
                        '<div>' +
                        '<table>' +
                        '<tbody>' +
                        '<tr><td>评论人：' + list[i].username + '</td></tr>' +
                        '<tr><td>评论人注册时间:' + list[i].userTime + '</td></tr>' +
                        '<tr><td>评论时间：' + list[i].createTime + '</td></tr>' +
                        '<tr><td>删除选中：<input name="DELETE_CHECK_NAME" type="checkbox" value="' + list[i].id + '"></td></tr>' +
                        '</tbody></table></div></div><div class="col-sm-12">' +
                        '<span>' + list[i].content + '</span></div><div class="col-sm-12">' +
                        '<audio src="' + list[i].audio + '" controls="controls" style="height:20px"></audio>' +
                        '&nbsp;|&nbsp;<a id="praisecNum" href="javascript:void(0);" ' +
                        'onclick="commentPraise(\'' + list[i].postId + '\',\'' + list[i].id + '\')">' +
                        '赞：</a> <span id="comment' + list[i].id + '">' + list[i].postPraise + '</span></div>';
                }
                $('#comment_data').append(content);
            } else {
                $('#comment_data').empty();
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

function getPostList(postUUID, SynOrAsyn, url) {


}

function showPostlist(post, user, register, postPraise, myUserUUID, myAdmin) {


}

function allCommentlist(allCommentlist, admin) {

}

function hotPraise(postUUID, cmUUID) {
    $.ajax({
        type: "POST",
        url: "/post/comment/api/praise/" + postUUID + "/" + cmUUID,
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("新增失败", result.message);
            }

            if (data !== null) {
                let id = '#' + "hotComment" + data.commentId;
                $(id).text(data.praise);
                let comment = '#' + "comment" + data.commentId;
                $(comment).text(data.praise);
            } else {
                $.MsgBox.Alert("消息", "点赞过快了哟");
            }
        }
    });
}

function hotsPraiseClick(postUUID, cmUUID) {
    $.ajax({
        type: "POST",
        url: "/post/post/api/praise/" + postUUID,
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("消息", "出错了，请于管理员联系");
            }

            if (data !== null) {
                $('#postPraise').text(data);
            } else {
                $.MsgBox.Alert("消息", "点赞过快了哟");
            }
        }
    });

}

function commentPraise(postUUID, cmUUID) {
    $.ajax({
        type: "POST",
        url: "/post/comment/api/praise/" + postUUID + "/" + cmUUID,
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("新增失败", result.message);
            }

            if (data !== null) {
                let id = '#' + "comment" + data.commentId;
                $(id).text(data.praise);
                if (data.commentId === hostComment) {
                    let id = '#' + "hotComment" + hostComment;
                    $(id).text(data.praise);
                }
            } else {
                $.MsgBox.Alert("消息", "点赞过快了哟");
            }
        }
    });
}

function addComCheck() {
    var cmText = editor.html().trim();

    if (cmText == "") {
        $("#tishi").html("评论内容不能为空");
        return;
    }

    let comment = {
        postId: postUUID,
        content: cmText
    }
    insertComment(comment);
}

function insertComment(comment) {
    $.ajax({
        type: "POST",
        url: "/post/comment/api/insert",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(comment),
        dataType: "json",
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("新增失败", result.message);
            }

            if (data) {
                $.MsgBox.Alert("消息", "新增成功！");
                window.location.replace("comment.html?page=" + returnpage + "&postid=" + postUUID);
            } else {
                $.MsgBox.Alert("消息", "新增失败，请重试！");
            }
        }
    });
}

function returnComList() {
    editor.html("");
    $("#tishi").html("");
    $("#COM_LIST_DIV_ID").attr("style", "display:block;");//隐藏div
    $("#COM_ADD_DIV_ID").attr("style", "display:none;");//隐藏div
}

function ADD_COM() {
    $("#COM_LIST_DIV_ID").attr("style", "display:none;");//隐藏div
    $("#COM_ADD_DIV_ID").attr("style", "display:block;");//隐藏div
}

function praiseclick(postUUID, pr, postAudio) {

    $.MsgBox.Alert("消息", "您已点过了赞");

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


    deleteComment(chk_value)
    // window.location.replace("comment.html?page=" + returnpage + "&postid=" + postUUID);
}

function deleteComment(ids) {
    // 发送请求
    $.ajax({
        url: "/post/comment/api/delete", // url where to submit the request
        type: "DELETE", // type of action POST || GET
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        data: JSON.stringify(ids),
        success: function (result) {
            // console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                console.log(error)
                alert(JSON.stringify(error));
                return;
            }
            // console.log(data);

            $.MsgBox.Alert("消息", "删除成功");
            getCommentList(postUUID, true, "/post/comment/api/list");
        }
    });
    // window.location = "${ctx }/user/removeUser?ids=" + ids.get();
}


function returnPostList() {
    if (returnpage.trim() == "post") {
        window.location.replace("post.html");
    } else if (returnpage.trim() == "myPost") {
        window.location.replace("myPost.html");
    } else if (returnpage.trim() == "myCom") {
        window.location.replace("myComment.html");
    }
}