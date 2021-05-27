var everyPageDataCount = 7;
var postPageIndex = 0;
var postAllPage = 0;
var postUUID = "";
var returnpage = ""

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
    getPostList(postUUID, true, "/postbar/commentController/getInit");
});


function getPostList(postUUID, SynOrAsyn, url) {


}

function showPostlist(post, user, register, postPraise, myUserUUID, myAdmin) {


}

function allCommentlist(allCommentlist, admin) {

}

function hotsPraiseClick(postUUID, cmUUID) {


}

function addComCheck() {
    var cmText = editor.html().trim();

    if (cmText == "") {
        $("#tishi").html("评论内容不能为空");
        return;
    }


    window.location.replace("comment.html?page=" + returnpage + "&postid=" + postUUID);


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


    window.location.replace("comment.html?page=" + returnpage + "&postid=" + postUUID);


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