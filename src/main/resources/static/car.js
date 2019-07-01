/**
 * 小车前进
 */
function up() {
    $.ajax({
        url: "/car/up",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 小车后退
 */
function down() {
    $.ajax({
        url: "/car/down",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 小车左转
 * @param sleep
 */
function left(sleep) {
    $.ajax({
        url: "/car/left",
        type: "get",
        async: true,
        data: "sleep=" + sleep,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 小车右转
 * @param sleep
 */
function right(sleep) {
    $.ajax({
        url: "/car/right",
        type: "get",
        async: true,
        data: "sleep=" + sleep,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 小车停止
 */
function stop() {
    $.ajax({
        url: "/car/stop",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 加速
 */
function speedAcc() {
    $.ajax({
        url: "/car/speedAcc",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 小车减速
 */
function speedDec() {
    $.ajax({
        url: "/car/speedDec",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 旋转小车
 */
function rotate() {
    $.ajax({
        url: "/car/rotate",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 自动寻路 1min
 */
function navigateAvoid() {
    $.ajax({
        url: "/navigate/goAvoid",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

function navigateLine() {
    $.ajax({
        url: "/navigate/goLine",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * LED开启
 */
function lightOn() {
    $.ajax({
        url: "/light/on",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * LED关闭
 */
function lightOff() {
    $.ajax({
        url: "/light/off",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 超声波测距
 */
function getDistance() {
    $.ajax({
        url: "/wave/distance",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
            $("#distance").html(result + "cm");
        }
    });
}

/**
 * 激光打开
 */
function laserOpen() {
    $.ajax({
        url: "/laser/on",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 激光关闭
 */
function laserClose() {
    $.ajax({
        url: "/laser/off",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 摄像头舵机左右
 * @param angle
 */
function cameraLeftAndRight(angle) {
    $.ajax({
        url: "/servo/cameraLeftAndRight",
        type: "get",
        async: true,
        data: "angle=" + angle,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 摄像头上下舵机
 * @param angle
 */
function cameraUpAndDown(angle) {
    $.ajax({
        url: "/servo/cameraUpAndDown",
        type: "get",
        async: true,
        data: "angle=" + angle,
        success: function (result) {
            console.log(result);
        }
    });
}

/**
 * 超声波左右舵机
 * @param angle
 */
function ultrasonicWaveLeftAndRight(angle) {
    $.ajax({
        url: "/servo/ultrasonicWaveLeftAndRight",
        type: "get",
        async: true,
        data: "angle=" + angle,
        success: function (result) {
            console.log(result);
        }
    });
}


function cameraOn() {
    $.ajax({
        url: "/camera/on",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

function cameraOff() {
    $.ajax({
        url: "/camera/Off",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

function fanC() {
    $.ajax({
        url: "/car/fanC",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

function fanA() {
    $.ajax({
        url: "/car/fanA",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}

function fanS() {
    $.ajax({
        url: "/car/fanS",
        type: "get",
        async: true,
        success: function (result) {
            console.log(result);
        }
    });
}