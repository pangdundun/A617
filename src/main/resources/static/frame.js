let tool = new Tool();

let $selectInterface = $('#selectInterface');
let $selectType = $('#selectType');
let $inputData = $('#inputData');

let $selectExample = $('#selectExample');
let $getExampleData = $('#getExampleData');

let $submit = $('#submit');

let $resultDisplay = $('#resultDisplay');

ready(() => {
    $resultDisplay.value = "Wait for output.";

    $getExampleData.click(() => {
        let data = '';
        let now = new Date().getTime();
        let value = parseInt($selectExample.find('option:selected').val());
        if (value === 1) {
            data = {"requestTime": now, "requestCode": 5, "deviceID": 0, "typeCode": 1};
        } else if (value === 2) {
            data = {"requestTime": now, "requestCode": 5, "deviceID": 0, "typeCode": 2};
        } else if (value === 3) {
            data = {"requestTime": now, "requestCode": 5, "deviceID": 0, "typeCode": 3};
        } else if (value === 4) {
            data = {"requestTime": now, "requestCode": 5, "deviceID": 0, "typeCode": 4};
        } else if (value === 5) {
            data = {
                "requestTime": now,
                "ruleCode": 1,
                "data": {
                    "int1": 0,
                    "object1": {
                        "ID": 0,
                        "IDCreatedDevice": 0,
                        "IDParent": 0,
                        "countUpdated": 0,
                        "dateCreated": now,
                        "dateUpdated": now,
                        "nameDisplay": "a",
                        "nameStorage": "a"
                    }
                },
                "requestCode": 2,
                "deviceID": 0,
                "typeCode": 2,
                "operateCode": 2
            };
        } else if (value === 6) {
            data = {"requestTime": now, "requestCode": 6, "deviceID": 0};
        } else if (value === 7) {
            data = {"requestTime": now, "requestCode": 7, "deviceID": 0};
        }
        $inputData.val(JSON.stringify(data));
    });

    $submit.click(() => {
        let type = $selectType.find('option:selected').text();
        let interface_ = $selectInterface.find('option:selected').text();
        let data = $inputData.val();

        console.log(type, interface_, data);

        const request = new XMLHttpRequest();
        request.open(type, '/a617/' + interface_, true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            $resultDisplay.val(request.responseText);
            console.log('xhr.responseText: ', request.responseText);
        }

        request.send(data);
    });
});

function ready(fn) {
    if (document.addEventListener) {
        //标准浏览器
        document.addEventListener('DOMContentLoaded', function () {
            //注销事件，避免反复触发
            document.removeEventListener('DOMContentLoaded', arguments.callee, false);
            //执行函数
            fn();
        }, false);
    } else if (document.attachEvent) {
        //IE浏览器
        document.attachEvent('onreadystatechange', function () {
            if (document.readyState === 'complete') {
                document.detachEvent('onreadystatechange', arguments.callee);
                //执行函数
                fn();
            }
        });
    }
}

// 20220425-110002 close
// function selectSolutionOnChange() {
//     let value = selectSolution.val();
//     switch (value) {
//         case 1:
//         case 2:
//         case 3:
//         case 4:
//             selectInterface.val(2);
//             break;
//         case 5:
//             selectInterface.val(1);
//             break;
//         case 6:
//             selectInterface.val(3);
//             break;
//         default:
//     }
// }

function fullPull(typeCode) {
    const request = new XMLHttpRequest();
    request.open("POST", "/a617/fullPull", true);
    request.setRequestHeader('content-type', 'application/json');

    request.onreadystatechange = () => {
        $resultDisplay.value = request.responseText;
        console.log('xhr.responseText: ', request.responseText);
    }

    const data = {requestCode: 5, requestTime: 0, deviceID: 0, typeCode: typeCode};
    request.send(JSON.stringify(data));
}

function Tool() {
    this.getTimeStamp = () => {
        let str = date.getFullYear();
        str += this.formatNumber(date.getMonth() + 1);
        str += this.formatNumber(date.getDate());
        str += '-';
        str += this.formatNumber(date.getHours());
        str += this.formatNumber(date.getMinutes());
        str += this.formatNumber(date.getSeconds());
        return str;
    }

    this.formatNumber = (number) => {
        if (/^\d$/.test(number)) {
            return '0' + number;
        }
        if (/^[1-9]\d$/.test(number)) {
            return number;
        }
        return '';
    }

    this.getElementByXpath = xpath => {
        return document.evaluate(xpath, document).iterateNext();
    }

    // function old() {
    //     let buttonIncrease = tool.getElementByXpath("//button[@id='buttonIncrease']");
    //     buttonIncrease.onclick = () => {
    //         // increase
    //
    //         const request = new XMLHttpRequest();
    //         request.open("POST", "/a617/increase", true);
    //         request.setRequestHeader('content-type', 'application/json');
    //
    //         request.onreadystatechange = () => {
    //             $resultDisplay.value = request.responseText;
    //             console.log('xhr.responseText: ', request.responseText);
    //         }
    //
    //         const data = {
    //             "requestTime": 1650235366703, "ruleCode": 2, "data": {
    //                 "int1": 0,
    //                 "objectArr": [{
    //                     "IDFolder": 2,
    //                     "IDRegisterDevice": 0,
    //                     "MD5": "c50ad75e1a98e9c879b558e96a4674c8",
    //                     "dateTaken": 1649061091000,
    //                     "fileSize": 54594,
    //                     "height": 1000,
    //                     "mimeType": "image/jpeg",
    //                     "nameDisplay": "[Thumb]640340242107-59e87b91-dca6-31e4-8f53-451724575103.jpg",
    //                     "nameFolder": "b",
    //                     "nameRegister": "[Thumb]640340242107-59e87b91-dca6-31e4-8f53-451724575103.jpg",
    //                     "nameStorage": "[Thumb]640340242107-59e87b91-dca6-31e4-8f53-451724575103.jpg",
    //                     "orientation": 0,
    //                     "pathLocalFull": "/storage/emulated/0/Pictures/QQ/[Thumb]640340242107-59e87b91-dca6-31e4-8f53-451724575103.jpg",
    //                     "presenceLocalFull": true,
    //                     "width": 750
    //                 }, {
    //                     "IDFolder": 2,
    //                     "IDRegisterDevice": 0,
    //                     "MD5": "0f3fe89f0befd511497797f49a95729f",
    //                     "dateTaken": 1649061116000,
    //                     "fileSize": 19537741,
    //                     "height": 4608,
    //                     "mimeType": "image/png",
    //                     "nameDisplay": "0GV1J_Z`U$OK378SN8`1M@Y.png",
    //                     "nameFolder": "b",
    //                     "nameRegister": "0GV1J_Z`U$OK378SN8`1M@Y.png",
    //                     "nameStorage": "0GV1J_Z`U$OK378SN8`1M@Y.png",
    //                     "orientation": 0,
    //                     "pathLocalFull": "/storage/emulated/0/Pictures/QQ/0GV1J_Z`U$OK378SN8`1M@Y.png",
    //                     "presenceLocalFull": true,
    //                     "width": 3456
    //                 }, {
    //                     "IDFolder": 2,
    //                     "IDRegisterDevice": 0,
    //                     "MD5": "92527537f9e2afc3cdac10aa5a91b366",
    //                     "dateTaken": 1649950076265,
    //                     "fileSize": 925082,
    //                     "height": 0,
    //                     "mimeType": "image/png",
    //                     "nameDisplay": "QFY5ZM`S1@%11IC42755%ER.png",
    //                     "nameFolder": "b",
    //                     "nameRegister": "QFY5ZM`S1@%11IC42755%ER.png",
    //                     "nameStorage": "QFY5ZM`S1@%11IC42755%ER.png",
    //                     "orientation": 0,
    //                     "pathLocalFull": "/storage/emulated/0/Pictures/QQ/QFY5ZM`S1@%11IC42755%ER.png",
    //                     "presenceLocalFull": true,
    //                     "width": 0
    //                 }, {
    //                     "IDFolder": 2,
    //                     "IDRegisterDevice": 0,
    //                     "MD5": "92527537f9e2afc3cdac10aa5a91b366",
    //                     "dateTaken": 1649950077209,
    //                     "fileSize": 925082,
    //                     "height": 0,
    //                     "mimeType": "image/png",
    //                     "nameDisplay": "NZXU2G3C0UV~[BQ[6T0{RZ9.png",
    //                     "nameFolder": "b",
    //                     "nameRegister": "NZXU2G3C0UV~[BQ[6T0{RZ9.png",
    //                     "nameStorage": "NZXU2G3C0UV~[BQ[6T0{RZ9.png",
    //                     "orientation": 0,
    //                     "pathLocalFull": "/storage/emulated/0/Pictures/QQ/NZXU2G3C0UV~[BQ[6T0{RZ9.png",
    //                     "presenceLocalFull": true,
    //                     "width": 0
    //                 }, {
    //                     "IDFolder": 2,
    //                     "IDRegisterDevice": 0,
    //                     "MD5": "92527537f9e2afc3cdac10aa5a91b366",
    //                     "dateTaken": 1649950086000,
    //                     "fileSize": 925082,
    //                     "height": 6972,
    //                     "mimeType": "image/png",
    //                     "nameDisplay": "QFY5ZM`S1@%11IC42755%ER.png",
    //                     "nameFolder": "b",
    //                     "nameRegister": "QFY5ZM`S1@%11IC42755%ER.png",
    //                     "nameStorage": "QFY5ZM`S1@%11IC42755%ER.png",
    //                     "orientation": 0,
    //                     "pathLocalFull": "/storage/emulated/0/Pictures/QQ/QFY5ZM`S1@%11IC42755%ER(1).png",
    //                     "presenceLocalFull": true,
    //                     "width": 3526
    //                 }, {
    //                     "IDFolder": 2,
    //                     "IDRegisterDevice": 0,
    //                     "MD5": "09a78b6e1ee41b14d293c5adbb0cfb95",
    //                     "dateTaken": 1650134613000,
    //                     "fileSize": 2532124,
    //                     "height": 8236,
    //                     "mimeType": "image/png",
    //                     "nameDisplay": "B%OG@(5$Q]}L3XHI9IB[5{D.png",
    //                     "nameFolder": "b",
    //                     "nameRegister": "B%OG@(5$Q]}L3XHI9IB[5{D.png",
    //                     "nameStorage": "B%OG@(5$Q]}L3XHI9IB[5{D.png",
    //                     "orientation": 0,
    //                     "pathLocalFull": "/storage/emulated/0/Pictures/QQ/B%OG@(5$Q]}L3XHI9IB[5{D.png",
    //                     "presenceLocalFull": true,
    //                     "width": 3196
    //                 }]
    //             }, "requestCode": 2, "deviceID": 0, "typeCode": 3, "operateCode": 2
    //         };
    //         request.send(JSON.stringify(data));
    //     };
    //
    //     let buttonFullPullDevice = tool.getElementByXpath("//button[@id='buttonFullPullDevice']");
    //     buttonFullPullDevice.onclick = () => {
    //         fullPull(1);
    //     }
    //
    //     let buttonFullPullFolder = tool.getElementByXpath("//button[@id='buttonFullPullFolder']");
    //     buttonFullPullFolder.onclick = () => {
    //         fullPull(2);
    //     }
    //
    //     let buttonFullPullPhoto = tool.getElementByXpath("//button[@id='buttonFullPullPhoto']");
    //     buttonFullPullPhoto.onclick = () => {
    //         fullPull(3);
    //     }
    //
    //     let buttonFullPullLabel = tool.getElementByXpath("//button[@id='buttonFullPullLabel']");
    //     buttonFullPullLabel.onclick = () => {
    //         fullPull(4);
    //     }
    //
    //     let buttonRecreateAllTable = tool.getElementByXpath("//button[@id='buttonRecreateAllTable']");
    //     buttonRecreateAllTable.onclick = () => {
    //         const request = new XMLHttpRequest();
    //         request.open("POST", "/a617/recreateAllTable", true);
    //         request.setRequestHeader('content-type', 'application/json');
    //
    //         request.onreadystatechange = () => {
    //             $resultDisplay.value = request.responseText;
    //             console.log('xhr.responseText: ', request.responseText);
    //         }
    //
    //         const data = {requestCode: 6, requestTime: 0, deviceID: 0};
    //         request.send(JSON.stringify(data));
    //     }
    //
    //     let buttonReinitializeAllTable = tool.getElementByXpath("//button[@id='buttonReinitializeAllTable']");
    //     buttonReinitializeAllTable.onclick = () => {
    //         const request = new XMLHttpRequest();
    //         request.open("POST", "/a617/reinitializeAllTable", true);
    //         request.setRequestHeader('content-type', 'application/json');
    //
    //         request.onreadystatechange = () => {
    //             $resultDisplay.value = request.responseText;
    //             console.log('xhr.responseText: ', request.responseText);
    //         }
    //
    //         const data = {requestCode: 7, requestTime: 0, deviceID: 0};
    //         request.send(JSON.stringify(data));
    //     }
    // }
}