$(function () {

    // Claude 產的 要解決動態綁定的問題 - 待詢問
    // $(document).on('load orientationchange resize scroll', '.slick-initialized', function (e) {
    //      console.log('Event triggered:', e.type);
    //      console.log('Target element:', this);

    //     // 如果有特定的初始化函數，在這裡調用
    //     // 例如 slick slider 的初始化
    //     if (!$(this).data('initialized')) {
    //         $(this).find('.clior').slick({
    //             slidesToShow: 1,
    //             slidesToScroll: 1,
    //             arrows: true,
    //             dots: true,
    //             fade: false
    //         });
    //         $(this).data('initialized', true);
    //          console.log('Initialized:', $(this).data('initialized'));
    //     }
    // });

    $(document).on('orientationchange resize scroll', '.slick-initialized', function (e) {
        // 事件處理邏輯
    });
    
    // 確保 `load` 事件針對特定元素
    $(window).on('load', function () {
        $('.slick-initialized').trigger('customLoad');
    });
    
    $(document).on('customLoad', '.slick-initialized', function (e) {
        // 針對 load 事件的處理邏輯
    });


    // ============== 測試 - 頁面載入後直接網查詢活動的Servlet 發出Get 請求 ================
    fetch('activity/SearchAlActivities')
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        // 整理fetch 回傳回來的資料塞進DOM 中
        // .then(text => console.log(text))
        .then(activities => {
            $(".property-slide").empty();
            const [firstActivity, ...restActivities] = activities;
            activities.forEach(activity => {
                //    console.log(activity.activityName);

                let activity_item_html = `
        <!-- Single Property 以一個活動為單位-->
        <div class="single-items">
            <div class="property-listing card border rounded-3">
    
                <div class="listing-img-wrapper p-3">
                    <div class="list-img-slide position-relative">
                        <!-- tag -->
                        <div class="position-absolute top-0 left-0 ms-3 mt-3 z-1">
                            <div
                                class="label bg-success text-light d-inline-flex align-items-center justify-content-center">
                                <span class="svg-icon text-light svg-icon-2hx me-1">
                                    <svg width="14" height="14" viewBox="0 0 24 24" fill="none"
                                        xmlns="http://www.w3.org/2000/svg">
                                        <path opacity="0.3"
                                            d="M20.5543 4.37824L12.1798 2.02473C12.0626 1.99176 11.9376 1.99176 11.8203 2.02473L3.44572 4.37824C3.18118 4.45258 3 4.6807 3 4.93945V13.569C3 14.6914 3.48509 15.8404 4.4417 16.984C5.17231 17.8575 6.18314 18.7345 7.446 19.5909C9.56752 21.0295 11.6566 21.912 11.7445 21.9488C11.8258 21.9829 11.9129 22 12.0001 22C12.0872 22 12.1744 21.983 12.2557 21.9488C12.3435 21.912 14.4326 21.0295 16.5541 19.5909C17.8169 18.7345 18.8277 17.8575 19.5584 16.984C20.515 15.8404 21 14.6914 21 13.569V4.93945C21 4.6807 20.8189 4.45258 20.5543 4.37824Z"
                                            fill="currentColor"></path>
                                        <path
                                            d="M14.854 11.321C14.7568 11.2282 14.6388 11.1818 14.4998 11.1818H14.3333V10.2272C14.3333 9.61741 14.1041 9.09378 13.6458 8.65628C13.1875 8.21876 12.639 8 12 8C11.361 8 10.8124 8.21876 10.3541 8.65626C9.89574 9.09378 9.66663 9.61739 9.66663 10.2272V11.1818H9.49999C9.36115 11.1818 9.24306 11.2282 9.14583 11.321C9.0486 11.4138 9 11.5265 9 11.6591V14.5227C9 14.6553 9.04862 14.768 9.14583 14.8609C9.24306 14.9536 9.36115 15 9.49999 15H14.5C14.6389 15 14.7569 14.9536 14.8542 14.8609C14.9513 14.768 15 14.6553 15 14.5227V11.6591C15.0001 11.5265 14.9513 11.4138 14.854 11.321ZM13.3333 11.1818H10.6666V10.2272C10.6666 9.87594 10.7969 9.57597 11.0573 9.32743C11.3177 9.07886 11.6319 8.9546 12 8.9546C12.3681 8.9546 12.6823 9.07884 12.9427 9.32743C13.2031 9.57595 13.3333 9.87594 13.3333 10.2272V11.1818Z"
                                            fill="currentColor"></path>
                                    </svg>
                                </span>${activity.approved}
                            </div>
                        </div>
                        <!-- 活動圖片 -->
                        <div class="click rounded-3 overflow-hidden mb-0">
                            <div><a href="single-property-1.html"><img
                                        src="https://placehold.co/1200x800" class="img-fluid"
                                        alt="" /></a></div>
                            <div><a href="single-property-1.html"><img
                                        src="https://placehold.co/1200x800" class="img-fluid"
                                        alt="" /></a></div>
                            <div><a href="single-property-1.html"><img
                                        src="https://placehold.co/1200x800" class="img-fluid"
                                        alt="" /></a></div>
                        </div>
                    </div>
                </div>
    
                <div class="listing-caption-wrapper px-3">
                    <div class="listing-detail-wrapper">
                        <div class="listing-short-detail-wrap">
                            <div class="listing-short-detail">
                                <!-- 次標籤 -->
                                <div class="d-flex align-items-center">
                                    <span
                                        class="label bg-light-success text-success prt-type me-2">${activity.category}</span><span
                                        class="label bg-light-purple text-purple property-cats">Apartment</span>
                                </div>
                                <!-- 活動標題 -->
                                <h4 class="listing-name fw-semibold fs-5 mb-1"><a
                                        href="single-property-1.html">${activity.activityName}</a>
                                </h4>
                                <!-- 活動地點 -->
                                <div class="prt-location text-muted-2">
                                    <span class="svg-icon svg-icon-2hx">
                                        <svg width="18" height="18" viewBox="0 0 24 24" fill="none"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path opacity="0.3"
                                                d="M18.0624 15.3453L13.1624 20.7453C12.5624 21.4453 11.5624 21.4453 10.9624 20.7453L6.06242 15.3453C4.56242 13.6453 3.76242 11.4453 4.06242 8.94534C4.56242 5.34534 7.46242 2.44534 11.0624 2.04534C15.8624 1.54534 19.9624 5.24534 19.9624 9.94534C20.0624 12.0453 19.2624 13.9453 18.0624 15.3453Z"
                                                fill="currentColor" />
                                            <path
                                                d="M12.0624 13.0453C13.7193 13.0453 15.0624 11.7022 15.0624 10.0453C15.0624 8.38849 13.7193 7.04535 12.0624 7.04535C10.4056 7.04535 9.06241 8.38849 9.06241 10.0453C9.06241 11.7022 10.4056 13.0453 12.0624 13.0453Z"
                                                fill="currentColor" />
                                        </svg>
                                    </span>
                                    ${activity.address}
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 額外顯示的資訊 -->
                    <div class="price-features-wrapper">
                        <div
                            class="list-fx-features d-flex align-items-center justify-content-between">
                            <div class="listing-card d-flex align-items-center">
                                <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2">
                                    <i class="fa-solid fa-building-shield fs-sm"></i>
                                </div><span class="text-muted-2">剩餘${activity.inventoryCount}</span>
                            </div>
                            <div class="listing-card d-flex align-items-center">
                                <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2">
                                    <i class="fa-solid fa-bed fs-sm"></i>
                                </div><span class="text-muted-2">3 Beds</span>
                            </div>
                            <div class="listing-card d-flex align-items-center">
                                <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2">
                                    <i class="fa-solid fa-clone fs-sm"></i>
                                </div><span class="text-muted-2">1800 SQFT</span>
                            </div>
                        </div>
                    </div>
                    <!-- 費用 及 跳轉Button -->
                    <div
                        class="listing-detail-footer d-flex align-items-center justify-content-between py-4">
                        <div class="listing-short-detail-flex">
                            <h6 class="listing-card-info-price m-0">$${activity.unitPrice}</h6>
                        </div>
                        <div class="footer-flex">
                            <a href="property-detail.html" class="prt-view">
                                <span class="svg-icon text-primary svg-icon-2hx">
                                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none"
                                        xmlns="http://www.w3.org/2000/svg">
                                        <path
                                            d="M15.43 8.56949L10.744 15.1395C10.6422 15.282 10.5804 15.4492 10.5651 15.6236C10.5498 15.7981 10.5815 15.9734 10.657 16.1315L13.194 21.4425C13.2737 21.6097 13.3991 21.751 13.5557 21.8499C13.7123 21.9488 13.8938 22.0014 14.079 22.0015H14.117C14.3087 21.9941 14.4941 21.9307 14.6502 21.8191C14.8062 21.7075 14.9261 21.5526 14.995 21.3735L21.933 3.33649C22.0011 3.15918 22.0164 2.96594 21.977 2.78013C21.9376 2.59432 21.8452 2.4239 21.711 2.28949L15.43 8.56949Z"
                                            fill="currentColor" />
                                        <path opacity="0.3"
                                            d="M20.664 2.06648L2.62602 9.00148C2.44768 9.07085 2.29348 9.19082 2.1824 9.34663C2.07131 9.50244 2.00818 9.68731 2.00074 9.87853C1.99331 10.0697 2.04189 10.259 2.14054 10.4229C2.23919 10.5869 2.38359 10.7185 2.55601 10.8015L7.86601 13.3365C8.02383 13.4126 8.19925 13.4448 8.37382 13.4297C8.54839 13.4145 8.71565 13.3526 8.85801 13.2505L15.43 8.56548L21.711 2.28448C21.5762 2.15096 21.4055 2.05932 21.2198 2.02064C21.034 1.98196 20.8409 1.99788 20.664 2.06648Z"
                                            fill="currentColor" />
                                    </svg>
                                </span>
                            </a>
                        </div>
                    </div>
    
                </div>
    
            </div>
        </div>
        `;

                $(".property-slide").append(activity_item_html);
            });
        })
        .catch(({ message }) => console.log(message));


    // ============== 查詢活動卡片資料到即將開始活動 ==============

    fetch('activity/SearchActivityComingSoon')
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                const { status, statusText } = resp;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(activityCards => {
            $("#coming-soon-activities").empty();
            const [firstActivityCard, ...restActivityCards] = activityCards;
            activityCards.forEach(activityCard => {
                //    console.log(activityCard.activityName);

                let activity_card_html = `
                    <!-- Single Property 以一個活動為單位-->
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <div class="property-listing property-1 bg-white p-2 rounded">

                            <div class="listing-img-wrapper">
                                <a href="single-property-2.html">
                                    <img src="${activityCard.imageBase64[0]}" class="img-fluid mx-auto rounded" alt="" />
                                </a>
                            </div>

                            <div class="listing-content">

                                <div class="listing-detail-wrapper-box">
                                    <div
                                        class="listing-detail-wrapper d-flex align-items-center justify-content-between">
                                        <div class="listing-short-detail">
                                            <span class="label bg-light-danger text-danger d-inline-flex mb-1">${activityCard.category}</span>
                                            <h4 class="listing-name mb-0"><a href="single-property-2.html">${activityCard.activityName}</a></h4>
                                            <div class="fr-can-rating">
                                                <i class="fas fa-star fs-xs filled"></i>
                                                <i class="fas fa-star fs-xs filled"></i>
                                                <i class="fas fa-star fs-xs filled"></i>
                                                <i class="fas fa-star fs-xs filled"></i>
                                                <i class="fas fa-star fs-xs"></i>
                                                <span class="reviews_text fs-sm text-muted ms-2">(42 Reviews)</span>
                                            </div>

                                        </div>
                                        <div class="list-price">
                                            <h6 class="listing-card-info-price text-primary">$${activityCard.unitPrice}</h6>
                                        </div>
                                    </div>
                                </div>

                                <div class="price-features-wrapper">
                                    <div
                                        class="list-fx-features d-flex align-items-center justify-content-between mt-3 mb-1">
                                        <div class="listing-card d-flex align-items-center">
                                            <div class="square--25 text-muted-2 fs-sm circle gray-simple me-1"><i
                                                    class="fa-solid fa-building-shield fs-xs"></i></div><span
                                                class="text-muted-2 fs-sm">${activityCard.cityName}</span>
                                        </div>
                                        <div class="listing-card d-flex align-items-center">
                                            <div class="square--25 text-muted-2 fs-sm circle gray-simple me-1"><i
                                                    class="fa-solid fa-clone fs-xs"></i></div><span
                                                class="text-muted-2 fs-sm">${activityCard.supplierName}</span>
                                        </div>
                                    </div>
                                    
                                </div>

                                <div class="listing-footer-wrapper">
                                    <div class="listing-locate">
                                        <span class="listing-location text-muted-2"><i
                                                class="fa-solid fa-location-pin me-1"></i>${activityCard.address}</span>
                                    </div>
                                    <div class="listing-detail-btn">
                                        <a href="single-property-2.html"
                                            class="btn btn-sm px-4 fw-medium btn-primary">查看活動</a>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                `;

                $("#coming-soon-activities").append(activity_card_html);
            });
        })
        .catch(({ message }) => console.log(message));

    // ============== 使用者在搜尋欄選取類別 ================

    $("#index-search-button").on("click", function(e){
        e.preventDefault();

        let searchActivityName = $("input#search-activity-name").val();
        let category = $("select#category").val();
        let region = $("select#region").val();
        
        console.log("活動名稱:" + searchActivityName + category + "," + region );

        let params = new URLSearchParams();
        params.append("search-activity-name", searchActivityName);
        params.append("category", category);
        params.append("region", region);
        
        let url = `activity/chill-list-layout.html`;

        window.location.href = `${url}?${params.toString()}`;
    })

});