let tool = new Tool();

ready(() => {
    let resultDisplay = tool.getElementByXpath("//textarea[@id='result-display']");
    resultDisplay.value = "Wait for output.";

    let buttonGroup = tool.getElementByXpath("//div[@id='button-group']");

    let buttonCreateAllTable = tool.getElementByXpath("//button[@id='button-createAllTable']");
    buttonCreateAllTable.onclick = () => {
        const request = new XMLHttpRequest();
        request.open("GET", "/a617/createAllTable", true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            resultDisplay.value = request.responseText;
            console.log('xhr.responseText: ', request.responseText);
        }

        request.send();
    }

    let button1 = tool.getElementByXpath("//button[@id='button1']");
    button1.onclick = () => {
        const request = new XMLHttpRequest();
        request.open("GET", "/a617/clearAllTable", true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            resultDisplay.value = request.responseText;
            console.log('xhr.responseText: ', request.responseText);
        }

        request.send();
    }

    let button2 = tool.getElementByXpath("//button[@id='button2']");
    button2.onclick = () => {
        const request = new XMLHttpRequest();
        request.open("POST", "/a617/fullPull", true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            resultDisplay.value = request.responseText;
            console.log('xhr.responseText: ', request.responseText);
        }

        const data = {requestCode: 5, type: 15, deviceID: 0};
        request.send(JSON.stringify(data));
    }

    let button3 = tool.getElementByXpath("//button[@id='button3']");
    button3.onclick = () => {
        // increase

        const request = new XMLHttpRequest();
        request.open("POST", "/a617/increase", true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            resultDisplay.value = request.responseText;
            console.log('xhr.responseText: ', request.responseText);
        }

        const data = {
            requestCode: 5,
            requestTime: 0,
            deviceID: 0,
            record: [{
                typeCode: 1,
                ruleCode: 1,
                data: [
                    {
                        id: 1, name: "a", dateRegistered: 1649924045000, dateVisited: 1649924045000
                    }, {
                        iD: 2,
                        name: "b",
                        dateRegistered: 1649606400000,
                        dateVisited: 1649606400000,
                    }, {
                        iD: 3,
                        name: "c",
                        dateRegistered: 1649606400000,
                        dateVisited: 1649606400000,
                    }
                ]
            }, {
                typeCode: 1,
                ruleCode: 2,
                data: [
                    {id: 2, name: "bb", dateRegistered: 1659924045000, dateVisited: 1659924045000}
                ]
            }, {
                typeCode: 1,
                ruleCode: 3,
                data: [3]
            }, {
                typeCode: 2,
                ruleCode: 1,
                data: [
                    {
                        "countUpdated": 0,
                        "dateCreated": 1649674864000,
                        "dateUpdated": 1649674864000,
                        "description": "a",
                        "iD": 5,
                        "iDCover": null,
                        "iDCreatedDevice": 0,
                        "iDParent": 0,
                        "nameDisplay": "a",
                        "nameStorage": "a"
                    },
                    {
                        "countUpdated": 0,
                        "dateCreated": 1649674864000,
                        "dateUpdated": 1649674864000,
                        "description": "b",
                        "iD": 6,
                        "iDCover": null,
                        "iDCreatedDevice": 0,
                        "iDParent": 0,
                        "nameDisplay": "b",
                        "nameStorage": "b"
                    },
                    {
                        "countUpdated": 0,
                        "dateCreated": 1649674864000,
                        "dateUpdated": 1649674864000,
                        "description": "c",
                        "iD": 7,
                        "iDCover": null,
                        "iDCreatedDevice": 0,
                        "iDParent": 0,
                        "nameDisplay": "c",
                        "nameStorage": "c"
                    }
                ]
            }, {
                typeCode: 2,
                ruleCode: 2,
                data: [
                    {
                        "countUpdated": 0,
                        "dateCreated": 1649674864000,
                        "dateUpdated": 1649674864000,
                        "description": "bb",
                        "iD": 6,
                        "iDCover": null,
                        "iDCreatedDevice": 1,
                        "iDParent": 5,
                        "nameDisplay": "bb",
                        "nameStorage": "bb"
                    }
                ]
            }, {
                typeCode: 2,
                ruleCode: 3,
                data: [7]
            }, {
                typeCode: 4,
                ruleCode: 1,
                data: [
                    {
                        "countUpdated": 1,
                        "dateRegistered": 345600000,
                        "dateTaken": 345600000,
                        "dateUpdated": 345600000,
                        "description": "a",
                        "fileSize": 1,
                        "height": 1,
                        "iD": 5,
                        "iDFolder": 5,
                        "iDRegisterDevice": 1,
                        "iDsStorageDevice": [
                            1
                        ],
                        "iDsTag": [
                            1
                        ],
                        "latitude": 1,
                        "longitude": 1,
                        "mD5": "a",
                        "mimeType": "a",
                        "nameDisplay": "a",
                        "nameFolder": "a",
                        "nameRegister": "a",
                        "nameStorage": "a",
                        "namesStorageDevice": [
                            "a"
                        ],
                        "namesTag": [
                            "a"
                        ],
                        "orientation": 1,
                        "pathLocalFull": "a",
                        "pathLocalThumb": "1",
                        "presenceCloudThumb": true,
                        "presenceLocalFull": true,
                        "presenceLocalThumb": true,
                        "width": 1
                    }, {
                        "countUpdated": 1,
                        "dateRegistered": 345600000,
                        "dateTaken": 345600000,
                        "dateUpdated": 345600000,
                        "description": "b",
                        "fileSize": 1,
                        "height": 1,
                        "iD": 6,
                        "iDFolder": 6,
                        "iDRegisterDevice": 1,
                        "iDsStorageDevice": [
                            1
                        ],
                        "iDsTag": [
                            1
                        ],
                        "latitude": 1,
                        "longitude": 1,
                        "mD5": "b",
                        "mimeType": "b",
                        "nameDisplay": "b",
                        "nameFolder": "b",
                        "nameRegister": "b",
                        "nameStorage": "b",
                        "namesStorageDevice": [
                            "b"
                        ],
                        "namesTag": [
                            "b"
                        ],
                        "orientation": 1,
                        "pathLocalFull": "b",
                        "pathLocalThumb": "b",
                        "presenceCloudThumb": true,
                        "presenceLocalFull": true,
                        "presenceLocalThumb": true,
                        "width": 1
                    }, {
                        "countUpdated": 1,
                        "dateRegistered": 345600000,
                        "dateTaken": 345600000,
                        "dateUpdated": 345600000,
                        "description": "c",
                        "fileSize": 1,
                        "height": 1,
                        "iD": 7,
                        "iDFolder": 6,
                        "iDRegisterDevice": 1,
                        "iDsStorageDevice": [
                            1
                        ],
                        "iDsTag": [
                            1
                        ],
                        "latitude": 1,
                        "longitude": 1,
                        "mD5": "c",
                        "mimeType": "c",
                        "nameDisplay": "c",
                        "nameFolder": "c",
                        "nameRegister": "c",
                        "nameStorage": "c",
                        "namesStorageDevice": [
                            "c"
                        ],
                        "namesTag": [
                            "c"
                        ],
                        "orientation": 1,
                        "pathLocalFull": "c",
                        "pathLocalThumb": "c",
                        "presenceCloudThumb": true,
                        "presenceLocalFull": true,
                        "presenceLocalThumb": true,
                        "width": 1
                    }
                ]
            }, {
                typeCode: 4,
                ruleCode: 2,
                data: [{
                    "countUpdated": 1,
                    "dateRegistered": 345600000,
                    "dateTaken": 345600000,
                    "dateUpdated": 345600000,
                    "description": "bb",
                    "fileSize": 1,
                    "height": 1,
                    "iD": 6,
                    "iDFolder": 5,
                    "iDRegisterDevice": 1,
                    "iDsStorageDevice": [
                        1
                    ],
                    "iDsTag": [
                        1
                    ],
                    "latitude": 1,
                    "longitude": 1,
                    "mD5": "bb",
                    "mimeType": "bb",
                    "nameDisplay": "bb",
                    "nameFolder": "bb",
                    "nameRegister": "bb",
                    "nameStorage": "bb",
                    "namesStorageDevice": [
                        "bb"
                    ],
                    "namesTag": [
                        "bb"
                    ],
                    "orientation": 1,
                    "pathLocalFull": "bb",
                    "pathLocalThumb": "bb",
                    "presenceCloudThumb": true,
                    "presenceLocalFull": true,
                    "presenceLocalThumb": true,
                    "width": 1
                }]
            }, {
                typeCode: 4,
                ruleCode: 3,
                data: [7]
            }, {
                typeCode: 8,
                ruleCode: 1,
                data: [
                    {
                        "countUpdated": 1,
                        "dateCreated": 1649936453000,
                        "dateUpdated": 1649936453000,
                        "iD": 5,
                        "name": "a"
                    }, {
                        "countUpdated": 1,
                        "dateCreated": 1649936453000,
                        "dateUpdated": 1649936453000,
                        "iD": 6,
                        "name": "b"
                    }, {
                        "countUpdated": 1,
                        "dateCreated": 1649936453000,
                        "dateUpdated": 1649936453000,
                        "iD": 7,
                        "name": "c"
                    }
                ]
            }, {
                typeCode: 8,
                ruleCode: 2,
                data: [
                    {
                        "countUpdated": 1,
                        "dateCreated": 1649936453000,
                        "dateUpdated": 1649936453000,
                        "iD": 6,
                        "name": "bb"
                    }
                ]
            }, {
                typeCode: 8,
                ruleCode: 3,
                data: [7]
            }
            ]
        };
        request.send(JSON.stringify(data));
    };
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
}