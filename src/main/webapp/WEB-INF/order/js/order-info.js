$(function () {


    // ============== 自訂函式↓ =================
    // 函式 - 將日期時間格式轉換成只有日期的格式
    function convertTimeFormat(dateTime) {
        return dateTime.slice(0, 16);
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







    // let queryString = "orderId=67";
    let urlParams = new URLSearchParams(window.location.search);
    let orderId = urlParams.get('orderId');

    fetch(`${APP_CONFIG.BASE_URL}orders/order/tickets/${orderId}`)
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(tickets => {
            console.log(tickets);

            $('#activity-name').attr('href', `${APP_CONFIG.BASE_URL}activity/single-activity.html?id=${tickets[0]?.activityId}`);
            $('#activity-img').attr('src', tickets[0]?.activity.activityImages[0]?.imageBase64 || '../activity/asset/no-image.jpg');
            $('#activity-name').text(tickets[0]?.activity?.activityName);
            let orderSerial = formatSerialNumber(tickets[0]?.order?.orderId, "ORD");
            $('#order-serial').text(orderSerial);
            $('#activity-desc').text(tickets[0]?.activity?.description);
            $('#activity-date').text(tickets[0]?.activity?.startDateTime);
            // 後端把活動狀態修好之後，再把第二個判斷條件值換掉
            if (tickets[0]?.order?.orderStatus === "no_payment_required" || tickets[0]?.order?.orderStatus === "paid") {
                let orderStatus = "訂單已成立"
                $('#order-status').text(orderStatus);
            } else {
                let orderStatus = "訂單已過期"
                $('#order-status').text(orderStatus);
            }
            $('#order-contact').text(tickets[0]?.order?.orderContact || "無");
            $('#contact-email').text(tickets[0]?.order?.contactMail || "無");
            $('#contact-phone').text(tickets[0]?.order?.contactPhone  || "無");
            let totalPrice = tickets[0]?.order?.quantity * tickets[0]?.activity?.unitPrice;
            $('#order-total-fee').text(totalPrice);
            $('#registration-count').text(tickets[0]?.order?.quantity);
            let cityDistrict = tickets[0]?.activity?.city.cityName + tickets[0]?.activity?.district.districName;
            $('#city-district').text(cityDistrict);
            $('#act-location').text(tickets[0]?.activity?.address);

            // 用報名人數跟最小成團人數比較，判斷是否成團
            let activityRegistrated = tickets[0]?.activity.maxParticipants - tickets[0]?.activity.inventory;
            if (activityRegistrated >= tickets[0]?.activity.minParticipants) {
                let groupStatus = "已成團"
                $('#group-status').text(groupStatus);
            } else {
                let groupStatus = "未成團"
                $('#group-status').text(groupStatus);
            }

            let actStartDateTime = convertTimeFormat(tickets[0]?.activity.startDateTime);
            let actEndDateTime = convertTimeFormat(tickets[0]?.activity.endDateTime);
            let orderCreatedTime = convertTimeFormat(tickets[0]?.order.orderCreateDatetime)

            $('#actStartDateTime').text(actStartDateTime);
            $('#actEndDateTime').text(actEndDateTime);
            $('#orderCreatedTime').text(orderCreatedTime);

            // 整理票券變數

            let ticketStartTime = convertTimeFormat(tickets[0]?.activity.ticketsActivateTime || tickets[0]?.activity.startDateTime);
            let ticketEndTime = convertTimeFormat(tickets[0]?.activity.ticketsExpiredTime || tickets[0]?.activity.endDateTime);

            // 右邊供應商資訊
            $('#supplier-img').attr('src', tickets[0]?.activity.supplier.image?.imageBase64 || '../activity/asset/no-image.jpg');
            $('#supplier-name').attr('href', `${APP_CONFIG.BASE_URL}supplier/suppliers-intro.html?id=${tickets[0]?.activity.supplier.supplier_id}`);
            $('#supplier-name').text(tickets[0]?.activity.supplier.supplier_name);
            $('#supplier-phone').text(tickets[0]?.activity.supplier.phone);
            $('#msg-contact-email').val(tickets[0]?.order.contactMail);
            $('#msg-contact-phone').val(tickets[0]?.order.contactPhone);


            $('#ticket-card').empty();

            for (let i = 0; i < tickets.length; i++) {

                let ticketSerialNumber = formatSerialNumber(tickets[i]?.ticketId, "TKT");
                // 個別票券
                let ticketEl = `
                    <!-- 票券QRCode 下拉 -->
                    <div class="card mb-2">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <button class="btn btn-link collapsed" type="button"
                                    data-bs-toggle="collapse" data-bs-target="#collapseOne"
                                    aria-expanded="false" aria-controls="collapseOne">
                                    單人票 # <b class="ticket-index">${i + 1}</b>
                                </button>
                            </h2>
                        </div>
                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                            data-bs-parent="#generalac">
                            <div class="card-body">
                                <!-- Single Agent -->
                                <div class="col-lg-6 col-md-6 col-sm-12">
                                    <div class="agents-grid card rounded-3 border">

                                        <div class="agents-grid-wrap">
                                            <div class="fr-grid-thumb mx-auto text-center mt-5 mb-3">
                                                <a href="#" class="d-inline-flex p-1 border">
                                                    <div class="qrcode"></div>
                                                </a>
                                            </div>
                                            <div class="fr-grid-deatil text-center">
                                                <div class="fr-grid-deatil-flex">
                                                    <h5 class="fr-can-name mb-0"><a href="#">票券編號： </a><span>${ticketSerialNumber}</span>
                                                    </h5>
                                                    <span
                                                        class="agent-property text-muted-2">票券有效期限：</span>
                                                        <p><span class="ticket-start-time">${ticketStartTime}</span>
                                                            <span> - </span>
                                                            <span class="ticket-end-time">${ticketEndTime}</span></p>
                                                        
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
                $('#ticket-card').append(ticketEl);
            }

            const image = new Image();
            image.src = "https://i.pinimg.com/736x/8e/c7/eb/8ec7eb0304fab49ff8f7dacd113d1628.jpg";

            image.addEventListener("load", () => {

                const qrcodes = document.querySelectorAll(".qrcode");

                qrcodes.forEach(element => {
                    const canvas = qrcanvas.qrcanvas({
                        cellSize: 8,
                        correctLevel: "H",
                        data: "https://www.google.com",
                        logo: {
                            image
                        }
                    });
                    element.appendChild(canvas);
                });
            });





        })



















});