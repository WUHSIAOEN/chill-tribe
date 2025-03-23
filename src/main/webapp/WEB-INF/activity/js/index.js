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

    // $(document).on('orientationchange resize scroll', '.slick-initialized', function (e) {
    //     // 事件處理邏輯
    // });
    
    // // 確保 `load` 事件針對特定元素
    // $(window).on('load', function () {
    //     $('.slick-initialized').trigger('customLoad');
    // });
    
    // $(document).on('customLoad', '.slick-initialized', function (e) {
    //     // 針對 load 事件的處理邏輯
    // });


    


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
            // const [firstActivityCard, ...restActivityCards] = activityCards;
            activityCards.forEach(activityCard => {
                   console.log(activityCard);

                let activity_card_html = `
                    <!-- Single Property 以一個活動為單位-->
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <div class="property-listing property-1 bg-white p-2 rounded">

                            <div class="listing-img-wrapper">
                                <a href="single-property-2.html">
                                    <img src="${activityCard.activityImages[0]?.imageBase64 || './asset/no-image.jpg'}" class="img-fluid mx-auto rounded" alt="" />
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
                                                class="text-muted-2 fs-sm">${activityCard.city.cityName}</span>
                                        </div>
                                        <div class="listing-card d-flex align-items-center">
                                            <div class="square--25 text-muted-2 fs-sm circle gray-simple me-1"><i
                                                    class="fa-solid fa-clone fs-xs"></i></div><span
                                                class="text-muted-2 fs-sm">${activityCard.supplier.supplier_name}</span>
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
        
        let url = `activity/search-activities.html`;

        window.location.href = `${url}?${params.toString()}`;
    })

});