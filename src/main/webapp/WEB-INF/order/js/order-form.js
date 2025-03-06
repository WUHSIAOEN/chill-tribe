$(function () {

    // ============== 假資料↓ =================
    // 先塞假訂單資料到localStorage
    let orderData = {
        activityId: 34,
        quantity: 2,
        unitPrice: 1000,
        total: 2000
    }
    localStorage.setItem('orderData', JSON.stringify(orderData));

    // 先塞假會員資料到SessionStorage
    let MemberData = {
        memberId: 1
    }
    sessionStorage.setItem('MemberData', JSON.stringify(MemberData));
    // ============== 假資料↑ =================

    // ============== 自訂函式↓ =================

    // 函式 - 取得訂單資料
    function getOrderData() {
        const orderData = localStorage.getItem('orderData');
        if (orderData) {
            return JSON.parse(orderData);
        }
        return null;
    }

    // 函式 - 取得會員資料
    function getMemberData() {
        const memberData = sessionStorage.getItem('MemberData');
        if (memberData) {
            return JSON.parse(memberData);
        }
        return null;
    }

    // 函式 - 將日期時間格式轉換成只有日期的格式
    function convertTimeFormat(dateTime) {
        return dateTime.slice(0, 16);
    }

    // ============== 自訂函式↑ =================

    // 取得會員基本資料
    fetch(`/chill-tribe/member/find/${getMemberData().memberId}`)
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


    // 從query string 取得活動ID
    // let activityId = new URLSearchParams(window.location.search);
    // console.log(urlParams.toString());

    // 要先抓到活動資訊放到右邊活動資訊卡
    // URI 要改`/chill-tribe/supplier/applyAct/${activityId}`
    fetch(`/chill-tribe/supplier/applyAct/34`)
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
            let firstImg = activity.activityImages[0].imageBase64;
            // 顯示活動資訊
            $("#activity-info-header img").attr("src", firstImg);
            $("#activity-info-header h4").text(activity.activityName);
            $("#activity-info-header span").text(activity.city.cityName);
            $("#supplier-name").text(activity.supplier.supplier_name);
            $("#start-time").text(startDateTime);
            $("#end-time").text(endDateTime);
        })

    // 這邊要新增一個判斷，前處理訂單狀態，是否為需要付款的訂單
    

    // ============== 從LocalStorage 取得訂單資訊↓ =================
    // console.log(getOrderData());
    $("#order-quantity").text(getOrderData().quantity);
    $("#act-unit-price").text(getOrderData().unitPrice);
    $("#order-total").text(getOrderData().total);

    // ============== 從LocalStorage 取得訂單資訊↑ =================


    // ============== 訂單聯絡資料蒐集↓ =================
    // 當按下送出訂單按鈕時
    $("#order-form").submit(function (e) {
        e.preventDefault();
        // console.log("送出訂單");

        // 取得表單資料
        let orderContact = $("#order-contact").val();
        let contactMail = $("#contact-mail").val();
        let contactPhone = $("#contact-phone").val();
        let requirement = $("#requirement").val();

        // console.log(orderContact, contactMail, contactPhone, requirement);

        // 送出訂單
        fetch("/chill-tribe/orders/order", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                activityId: getOrderData().activityId,
                memberId: getMemberData().memberId,
                quantity: getOrderData().quantity,
                orderStatus: "no_payment_required",
                paymentMethod: "none",
                orderContact: orderContact,
                contactMail: contactMail,
                contactPhone: contactPhone,
                requirement: requirement
            }),
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
                // location.href = "/chill-tribe/order/order-complete.html";
            })
    })





    // 故意先多一個s
    // fetch("/chill-tribe/orders/orders", {
    //     method: 'POST',
    //     headers: { 'Content-Type': 'application/json' },
    //     body: JSON.stringify({
    //         activityId: 34,
    //         memberId: 1,
    //         quantity: 2,
    //         orderStatus: "no_payment_required",
    //         paymentMethod: "none",
    //         orderContact: "小名",
    //         contactMail: "aaabbbcc@gmail.com",
    //         contactPhone: "456196582",
    //         requirement: "無"
    //     }),
    // })
    // .then(resp => {
    //     if (resp.ok) {
    //         return resp.json();
    //     } else {
    //         const { status, statusText } = resp;
    //         throw Error(`${status}: ${statusText}`);
    //     }
    // })
    // .then(order => {
    //     console.log(order);
    // })

})
