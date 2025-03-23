$(function () {

    // ============== 自訂函式↓ =================

    // 函式 - 格式化票券編號
    function formatSerialNumber(number, suffix) {
        const numStr = String(number);
        const padding = 6 - numStr.length;

        let SerialNumber = suffix;
        for (let i = 0; i < padding; i++) {
            SerialNumber += "0";
        }
        SerialNumber += numStr;
        return SerialNumber;
    }

    // 函式 - 將日期時間格式轉換成沒有秒數
    function convertTimeFormat(dateTime) {
        return dateTime.slice(0, 16);
    }

    // ============== 自訂函式↑ =================




    // 抓活動資訊 - 這邊應該要發fetch 去查訂單的活動資訊
    // 從URI取得orderId
    let urlParams = new URLSearchParams(window.location.search);
    console.log(urlParams.toString());
    let orderId = urlParams.get('orderId');

    fetch(`/chill-tribe/orders/order/${orderId}`)
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(orders => {
            console.log(orders);

            let orderStatus;
            if (orders.orderStatus === "no_payment_required" || orders.orderStatus === "paid"){
                orderStatus = "訂單已成立"
            }
            // 如果後續有多種付款方式的話建議換成js 的swith-case 比較合適
            let paymentMethod;
            if (orders.paymentMethod === "credit_card"){
                paymentMethod = "信用卡"
            } else {
                paymentMethod = "無需付款"
            }

            let orderSerial = formatSerialNumber(orders.orderId, "ORD");

            // 訂單資訊
            $('#order-serial').text(orderSerial);
            $('#contact-person').text(orders.orderContact);
            $('#contact-mail').text(orders.contactMail);
            $('#contact-phone').text(orders.contactPhone);
            $('#order-datetime').text(orders.orderCreateDatetime);
            $('#order-status').text(orderStatus);
            $('#payment_method').text(paymentMethod);
            $('#requirement').text(orders.requirement);
            $("#order-quantity").text(orders.quantity);
            $("#act-unit-price").text(orders.activity.unitPrice);
            const totalPrice = orders.quantity * orders.activity.unitPrice;
            $("#order-total").text(totalPrice);
            activityId = orders.activityId;

            return fetch(`/chill-tribe/supplier/applyAct/${activityId}`);
        })
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(activity => {
            // console.log(activity);
            let startDateTime = convertTimeFormat(activity.startDateTime);
            let endDateTime = convertTimeFormat(activity.endDateTime);
            let firstImg = activity.activityImages[0]?.imageBase64 || '../activity/asset/no-image.jpg';
            // 顯示活動資訊
            $("#activity-info-header img").attr("src", firstImg);
            $("#activity-info-header h4").text(activity.activityName);
            $("#activity-info-header span").text(activity.city.cityName);
            $("#supplier-name").text(activity.supplier.supplier_name);
            $("#address").text(activity.address);
            $("#start-time").text(startDateTime);
            $("#end-time").text(endDateTime);
        })

        // 跳轉瀏覽訂單還沒寫


});