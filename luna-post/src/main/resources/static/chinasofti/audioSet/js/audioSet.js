$(function () {

    $("#auSetVoiPer option[value='" + 0 + "']").attr("selected", true);
    $("#auSetSpd option[value='" + 5 + "']").attr("selected", true);
    $("#auSetPit option[value='" + 5 + "']").attr("selected", true);
    $("#auSetVol option[value='" + 5 + "']").attr("selected", true);

    sysAudio();
});

function sysAudio() {
    $.ajax({
        type: "get",
        url: "/post/audio/api/get",
        contentType: 'application/json;charset=UTF-8',
        dataType: "json",
        success: function (result) {
            console.log(result);
            let data;
            try {
                data = checkResultAndGetData(result);
            } catch (error) {
                $.MsgBox.Alert("查询失败", result.message);
            }

            if (data !== null) {
                $("#auSetVoiPer option[value='" + data.audioVoiPer + "']").attr("selected", true);
                $("#auSetSpd option[value='" + data.audioSpd + "']").attr("selected", true);
                $("#auSetPit option[value='" + data.audioPit + "']").attr("selected", true);
                $("#auSetVol option[value='" + data.audioVol + "']").attr("selected", true);
            }
        }
    });
}

function updateAudioSetUp() {
    var auSetVoiPer = $("#auSetVoiPer").val();
    var auSetSpd = $("#auSetSpd").val();
    var auSetPit = $("#auSetPit").val();
    var auSetVol = $("#auSetVol").val();

    let audio = {
        audioVoiPer: parseInt(auSetVoiPer),
        audioSpd: parseInt(auSetSpd),
        audioPit: parseInt(auSetPit),
        audioVol: parseInt(auSetVol),
    }

    updateAudio(audio);
}

function updateAudio(audio) {
    $.ajax({
        type: "PUT",
        url: "/post/audio/api/update",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(audio),
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
                $.MsgBox.Alert("消息", "修改成功");
            } else {
                $.MsgBox.Alert("消息", "修改失败");
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
