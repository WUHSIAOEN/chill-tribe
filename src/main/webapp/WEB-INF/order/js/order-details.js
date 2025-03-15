$(function () {

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




    // 抓活動資訊 - 這邊應該要發fetch 去查訂單的活動資訊
    const activityId = getOrderData().activityId;
    fetch(`/chill-tribe/supplier/applyAct/${activityId}`)
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(activity => {
            console.log(activity);
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





    // 抓訂單資訊



});