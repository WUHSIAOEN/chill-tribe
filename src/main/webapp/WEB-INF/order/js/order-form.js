$(function () {

    // ============== 假資料↓ =================
    // 先塞假訂單資料到localStorage
    // let orderData = {
    //     activityId: 34,
    //     quantity: 2,
    //     unitPrice: 1000,
    //     total: 2000
    // }
    // localStorage.setItem('checkoutActivity', JSON.stringify(checkoutActivity));

    // 先塞假會員資料到SessionStorage
    // let memberid = {
    //     memberid: 1
    // }
    // sessionStorage.setItem('memberid', JSON.stringify(memberid));
    // ============== 假資料↑ =================

    // ============== 自訂函式↓ =================

    // 函式 - 取得訂單資料
    function getOrderData() {
        const checkoutActivity = localStorage.getItem('checkoutActivity');
        if (checkoutActivity) {
            return JSON.parse(checkoutActivity);
        }
        return null;
    }

    // 函式 - 取得會員資料
    function getMemberData() {
        const memberData = localStorage.getItem('memberid');
        // if (memberData) {
        //     return JSON.parse(memberData);
        // }
        return memberData;
    }

    // 函式 - 將日期時間格式轉換成只有日期的格式
    function convertTimeFormat(dateTime) {
        return dateTime.slice(0, 16);
    }

    // ============== 自訂函式↑ =================


    // console.log(getMemberData().memberid);

    // 取得會員基本資料
    fetch(`${APP_CONFIG.BASE_URL}member/find/${getMemberData()}`)
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(member => {
            // console.log(member);
            $("#default-name").val(member.membername);
            $("#default-email").val(member.email);
            $("#default-phone").val(member.phone);
        })


    // 要先抓到活動資訊放到右邊活動資訊卡
    // URI 要改`/chill-tribe/supplier/applyAct/${activityId}`
    const activityId = getOrderData().activityId;
    fetch(`${APP_CONFIG.BASE_URL}supplier/applyAct/${activityId}`)
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

    // 這邊要新增一個判斷，前處理訂單狀態，是否為需要付款的訂單


    // ============== 從LocalStorage 取得訂單資訊↓ =================
    // console.log(getOrderData());
    $("#order-quantity").text(getOrderData().quantity);
    $("#act-unit-price").text(getOrderData().unitPrice);
    const totalPrice = getOrderData().quantity * getOrderData().unitPrice;
    $("#order-total").text(totalPrice);

    // ============== 從LocalStorage 取得訂單資訊↑ =================


    // ============== 訂單聯絡資料蒐集↓ =================
    // 當按下送出訂單按鈕時
    $("#order-form").submit(function (e) {
        e.preventDefault();

        // 取得表單資料
        let orderContact = $("#default-name").val();
        let contactMail = $("#default-email").val();
        let contactPhone = $("#default-phone").val();
        let requirement = $("#requirement").val();

        let orderObj = {
            activityId: getOrderData().activityId,
            memberId: getMemberData(),
            quantity: getOrderData().quantity,
            orderStatus: getOrderData().orderStatus,
            paymentMethod: getOrderData().paymentMethod,
            orderContact,
            contactMail,
            contactPhone,
            requirement
        }
		
		console.log(orderObj);


        // =====================要判斷需不需要付款=====================

        if (getOrderData().orderStatus === "no_payment_required") {
            // 送出訂單
            fetch(`${APP_CONFIG.BASE_URL}orders/order/orderWithoutPayment`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(orderObj),
            })
                .then(resp => {
                    if (resp.ok) {
                        return resp.json();
                    } else {
                        const { status, statusText } = resp;
                        throw Error(`${status}: ${statusText}`);
                    }
                })
                .then(order => {
                    console.log(order);

                    // 導向訂單完成頁
                    location.href = `/chill-tribe/order/order-details.html?orderId=${order.orderId}`;

                    // 清空localStorage
                    // localStorage.removeItem('checkoutActivity');
                })
        } else {
            // 送出訂單
            fetch(`${APP_CONFIG.BASE_URL}orders/order/orderWithPayment`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(orderObj),
            })
                .then(resp => {
                    if (resp.ok) {
                        return resp.json();
                    } else {
                        const { status, statusText } = resp;
                        throw Error(`${status}: ${statusText}`);
                    }
                })
                .then(result => {
                    console.log(result);
                    // $("#ecpay").html(result.successful ? "結帳成功" : result.errMsg);
                    $("#ecpay").html(result);

                    // 清空localStorage
                    // localStorage.removeItem('checkoutActivity');

                })
        }

    })







})
