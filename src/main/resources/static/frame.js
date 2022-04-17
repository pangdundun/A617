let tool = new Tool();

let resultDisplay = tool.getElementByXpath("//textarea[@id='result-display']");

ready(() => {
    resultDisplay.value = "Wait for output.";

    let buttonGroup = tool.getElementByXpath("//div[@id='button-group']");

    let buttonIncrease = tool.getElementByXpath("//button[@id='buttonIncrease']");
    buttonIncrease.onclick = () => {
        // increase

        const request = new XMLHttpRequest();
        request.open("POST", "/a617/increase", true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            resultDisplay.value = request.responseText;
            console.log('xhr.responseText: ', request.responseText);
        }

        const data = {
            requestCode: 2,
            requestTime: 1650196958192,
            deviceID: 0,
            typeCode: 2,
            operateCode: 2,
            ruleCode: 1,
            data: '{"data1":{"IDCreatedDevice":0,"IDParent":0,"nameDisplay":"i","nameStorage":"i"},"int1":0}',
        };
        request.send(JSON.stringify(data));
    };

    let buttonFullPullDevice = tool.getElementByXpath("//button[@id='buttonFullPullDevice']");
    buttonFullPullDevice.onclick = () => {
        fullPull(1);
    }

    let buttonFullPullFolder = tool.getElementByXpath("//button[@id='buttonFullPullFolder']");
    buttonFullPullFolder.onclick = () => {
        fullPull(2);
    }

    let buttonFullPullPhoto = tool.getElementByXpath("//button[@id='buttonFullPullPhoto']");
    buttonFullPullPhoto.onclick = () => {
        fullPull(3);
    }

    let buttonFullPullLabel = tool.getElementByXpath("//button[@id='buttonFullPullLabel']");
    buttonFullPullLabel.onclick = () => {
        fullPull(4);
    }

    let buttonRecreateAllTable = tool.getElementByXpath("//button[@id='buttonRecreateAllTable']");
    buttonRecreateAllTable.onclick = () => {
        const request = new XMLHttpRequest();
        request.open("POST", "/a617/recreateAllTable", true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            resultDisplay.value = request.responseText;
            console.log('xhr.responseText: ', request.responseText);
        }

        const data = {requestCode: 6, requestTime: 0, deviceID: 0};
        request.send(JSON.stringify(data));
    }

    let buttonReinitializeAllTable = tool.getElementByXpath("//button[@id='buttonReinitializeAllTable']");
    buttonReinitializeAllTable.onclick = () => {
        const request = new XMLHttpRequest();
        request.open("POST", "/a617/reinitializeAllTable", true);
        request.setRequestHeader('content-type', 'application/json');

        request.onreadystatechange = () => {
            resultDisplay.value = request.responseText;
            console.log('xhr.responseText: ', request.responseText);
        }

        const data = {requestCode: 7, requestTime: 0, deviceID: 0};
        request.send(JSON.stringify(data));
    }
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

function fullPull(typeCode) {
    const request = new XMLHttpRequest();
    request.open("POST", "/a617/fullPull", true);
    request.setRequestHeader('content-type', 'application/json');

    request.onreadystatechange = () => {
        resultDisplay.value = request.responseText;
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
}