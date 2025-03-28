$(function () {

    // ============== 假資料↓ =================
    // 先塞假會員資料到SessionStorage
    // let memberid = {
    //     memberid: 1
    // }
    // sessionStorage.setItem('memberid', JSON.stringify(memberid));
    // ============== 假資料↑ =================

    // ============== 自訂函式↓ =================

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
        // console.log(dateTime);
        // if (dateTime == null) {
        //     dateTime = "2025/02/15 17:04:19";
        // }
        return dateTime.slice(0, 10);
    }

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


    // ============== 自訂函式↑ =================

    // 將會員購物車內容撈回來
    // path 要再改成從session 取得
    fetch(`/chill-tribe/orders/order/list/${getMemberData()}`)
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(orderItems => {
            console.log(orderItems);

            // 清空item DOM
            $("#order-item-lists").empty();

            // 將itemDOM字串塞到DOM裡
            for (let i = 0; i < orderItems.length; i++) {

                let orderSerial = formatSerialNumber(orderItems[i].orderId, "ORD")
                let orderCreatedDate = convertTimeFormat(orderItems[i].orderCreateDatetime);
                let activityStartDate = convertTimeFormat(orderItems[i].activity.startDateTime);
                let orderTotalPrice = orderItems[i].activity.unitPrice * orderItems[i].quantity;
                let orderStatus;

                // 訂單狀態之後再細改
                if (orderItems[i].orderStatus === "paid" || orderItems[i].orderStatus === "no_payment_required") {
                    orderStatus = "訂單已成效";
                } else {
                    orderStatus = "訂單已無效";
                }

                let itemDOM = `
                <table class="property-table-wrap responsive-table bkmark">
                    <tbody id="order-list">
                        <!-- Item #1 -->
                        <tr class="order-item">
                            <td class="property-container" style="padding-left: 0;">
                                <img src="${orderItems[i].activity.activityImages[0]?.imageBase64 || '../activity/asset/no-image.jpg'}" alt="">
                                <ul class="title" style="padding-left: 0; width: 1rem;">
                                    <li>
                                        <h4><a href="http://localhost:8080/chill-tribe/order/order-info.html?orderId=${orderItems[i].orderId}" class="order-act-name">${orderItems[i].activity.activityName}</a></h4>
                                    </li>
                                    <li><span>訂單編號</span></li>
                                    <li><span>訂購日期</span></li>
                                    <li><span>開始日期</span></li>
                                </ul>
                            </td>
                            <td style="padding-left: 0;">
                                <div class="title">
                                    <div style="height: 1.5rem;"></div>
                                    <div style="height: 1.7rem;">${orderSerial}</div>
                                    <div style="height: 1.7rem;">${orderCreatedDate}</div>
                                    <div style="height: 1.7rem;">${activityStartDate}</div>
                                </div>
                            </td>
                            <td>
                                <div style="height: 1.5rem;"></div>
                                <div><span class="ticket-amount" style="padding-top: 0.7rem;"
                                        data-price="900">票券共計： </span><b>${orderItems[i].quantity}</b><b> 張</b></div>
                                <div style="height: 1.5rem;"></div>
                                <div><span class="table-property-price" style="padding-top: 0.7rem;"
                                        data-price=" 900">總額：</span><b> TWD$
                                    </b><b>${orderTotalPrice}</b></div>
                            </td>
                            <td class="status">
                                <span class="label bg-light-purple text-purple">${orderStatus}</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
                `;

                $("#order-item-lists").append(itemDOM);
            }


        })
        .catch(error => {
            console.error(error);
        });


});