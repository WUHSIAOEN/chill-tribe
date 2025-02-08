$(function () {

    // ============== 測試 - 頁面載入後直接網查詢活動的Servlet 發出Get 請求 ================
    // fetch('activity/SearchActivities')
    //     .then(resp => {
    //         if (resp.ok) {
    //             return resp.json();
    //         } else {
    //             const { status, statusText } = resp;
    //             throw Error(`${status}: ${statusText}`);
    //         }
    //     })
    //     // 整理fetch 回傳回來的資料塞進DOM 中
    //     // .then(text => console.log(text))
    //     .then(activities => {
    //         $(".property-slide").empty();
    //         const [firstActivity, ...restActivities] = activities;
    //         activities.forEach(activity => {
    //             //    console.log(activity.activityName);

    //             let activity_item_html = `
    //     <!-- Single Property 以一個活動為單位-->
    //     <div class="single-items">
    //         <div class="property-listing card border rounded-3">
    
    //             <div class="listing-img-wrapper p-3">
    //                 <div class="list-img-slide position-relative">
    //                     <!-- tag -->
    //                     <div class="position-absolute top-0 left-0 ms-3 mt-3 z-1">
    //                         <div
    //                             class="label bg-success text-light d-inline-flex align-items-center justify-content-center">
    //                             <span class="svg-icon text-light svg-icon-2hx me-1">
    //                                 <svg width="14" height="14" viewBox="0 0 24 24" fill="none"
    //                                     xmlns="http://www.w3.org/2000/svg">
    //                                     <path opacity="0.3"
    //                                         d="M20.5543 4.37824L12.1798 2.02473C12.0626 1.99176 11.9376 1.99176 11.8203 2.02473L3.44572 4.37824C3.18118 4.45258 3 4.6807 3 4.93945V13.569C3 14.6914 3.48509 15.8404 4.4417 16.984C5.17231 17.8575 6.18314 18.7345 7.446 19.5909C9.56752 21.0295 11.6566 21.912 11.7445 21.9488C11.8258 21.9829 11.9129 22 12.0001 22C12.0872 22 12.1744 21.983 12.2557 21.9488C12.3435 21.912 14.4326 21.0295 16.5541 19.5909C17.8169 18.7345 18.8277 17.8575 19.5584 16.984C20.515 15.8404 21 14.6914 21 13.569V4.93945C21 4.6807 20.8189 4.45258 20.5543 4.37824Z"
    //                                         fill="currentColor"></path>
    //                                     <path
    //                                         d="M14.854 11.321C14.7568 11.2282 14.6388 11.1818 14.4998 11.1818H14.3333V10.2272C14.3333 9.61741 14.1041 9.09378 13.6458 8.65628C13.1875 8.21876 12.639 8 12 8C11.361 8 10.8124 8.21876 10.3541 8.65626C9.89574 9.09378 9.66663 9.61739 9.66663 10.2272V11.1818H9.49999C9.36115 11.1818 9.24306 11.2282 9.14583 11.321C9.0486 11.4138 9 11.5265 9 11.6591V14.5227C9 14.6553 9.04862 14.768 9.14583 14.8609C9.24306 14.9536 9.36115 15 9.49999 15H14.5C14.6389 15 14.7569 14.9536 14.8542 14.8609C14.9513 14.768 15 14.6553 15 14.5227V11.6591C15.0001 11.5265 14.9513 11.4138 14.854 11.321ZM13.3333 11.1818H10.6666V10.2272C10.6666 9.87594 10.7969 9.57597 11.0573 9.32743C11.3177 9.07886 11.6319 8.9546 12 8.9546C12.3681 8.9546 12.6823 9.07884 12.9427 9.32743C13.2031 9.57595 13.3333 9.87594 13.3333 10.2272V11.1818Z"
    //                                         fill="currentColor"></path>
    //                                 </svg>
    //                             </span>${activity.approved}
    //                         </div>
    //                     </div>
    //                     <!-- 活動圖片 -->
    //                     <div class="click rounded-3 overflow-hidden mb-0">
    //                         <div><a href="single-property-1.html"><img
    //                                     src="https://placehold.co/1200x800" class="img-fluid"
    //                                     alt="" /></a></div>
    //                         <div><a href="single-property-1.html"><img
    //                                     src="https://placehold.co/1200x800" class="img-fluid"
    //                                     alt="" /></a></div>
    //                         <div><a href="single-property-1.html"><img
    //                                     src="https://placehold.co/1200x800" class="img-fluid"
    //                                     alt="" /></a></div>
    //                     </div>
    //                 </div>
    //             </div>
    
    //             <div class="listing-caption-wrapper px-3">
    //                 <div class="listing-detail-wrapper">
    //                     <div class="listing-short-detail-wrap">
    //                         <div class="listing-short-detail">
    //                             <!-- 次標籤 -->
    //                             <div class="d-flex align-items-center">
    //                                 <span
    //                                     class="label bg-light-success text-success prt-type me-2">${activity.category}</span><span
    //                                     class="label bg-light-purple text-purple property-cats">Apartment</span>
    //                             </div>
    //                             <!-- 活動標題 -->
    //                             <h4 class="listing-name fw-semibold fs-5 mb-1"><a
    //                                     href="single-property-1.html">${activity.activityName}</a>
    //                             </h4>
    //                             <!-- 活動地點 -->
    //                             <div class="prt-location text-muted-2">
    //                                 <span class="svg-icon svg-icon-2hx">
    //                                     <svg width="18" height="18" viewBox="0 0 24 24" fill="none"
    //                                         xmlns="http://www.w3.org/2000/svg">
    //                                         <path opacity="0.3"
    //                                             d="M18.0624 15.3453L13.1624 20.7453C12.5624 21.4453 11.5624 21.4453 10.9624 20.7453L6.06242 15.3453C4.56242 13.6453 3.76242 11.4453 4.06242 8.94534C4.56242 5.34534 7.46242 2.44534 11.0624 2.04534C15.8624 1.54534 19.9624 5.24534 19.9624 9.94534C20.0624 12.0453 19.2624 13.9453 18.0624 15.3453Z"
    //                                             fill="currentColor" />
    //                                         <path
    //                                             d="M12.0624 13.0453C13.7193 13.0453 15.0624 11.7022 15.0624 10.0453C15.0624 8.38849 13.7193 7.04535 12.0624 7.04535C10.4056 7.04535 9.06241 8.38849 9.06241 10.0453C9.06241 11.7022 10.4056 13.0453 12.0624 13.0453Z"
    //                                             fill="currentColor" />
    //                                     </svg>
    //                                 </span>
    //                                 ${activity.address}
    //                             </div>
    //                         </div>
    //                     </div>
    //                 </div>
    //                 <!-- 額外顯示的資訊 -->
    //                 <div class="price-features-wrapper">
    //                     <div
    //                         class="list-fx-features d-flex align-items-center justify-content-between">
    //                         <div class="listing-card d-flex align-items-center">
    //                             <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2">
    //                                 <i class="fa-solid fa-building-shield fs-sm"></i>
    //                             </div><span class="text-muted-2">剩餘${activity.inventoryCount}</span>
    //                         </div>
    //                         <div class="listing-card d-flex align-items-center">
    //                             <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2">
    //                                 <i class="fa-solid fa-bed fs-sm"></i>
    //                             </div><span class="text-muted-2">3 Beds</span>
    //                         </div>
    //                         <div class="listing-card d-flex align-items-center">
    //                             <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2">
    //                                 <i class="fa-solid fa-clone fs-sm"></i>
    //                             </div><span class="text-muted-2">1800 SQFT</span>
    //                         </div>
    //                     </div>
    //                 </div>
    //                 <!-- 費用 及 跳轉Button -->
    //                 <div
    //                     class="listing-detail-footer d-flex align-items-center justify-content-between py-4">
    //                     <div class="listing-short-detail-flex">
    //                         <h6 class="listing-card-info-price m-0">$${activity.unitPrice}</h6>
    //                     </div>
    //                     <div class="footer-flex">
    //                         <a href="property-detail.html" class="prt-view">
    //                             <span class="svg-icon text-primary svg-icon-2hx">
    //                                 <svg width="24" height="24" viewBox="0 0 24 24" fill="none"
    //                                     xmlns="http://www.w3.org/2000/svg">
    //                                     <path
    //                                         d="M15.43 8.56949L10.744 15.1395C10.6422 15.282 10.5804 15.4492 10.5651 15.6236C10.5498 15.7981 10.5815 15.9734 10.657 16.1315L13.194 21.4425C13.2737 21.6097 13.3991 21.751 13.5557 21.8499C13.7123 21.9488 13.8938 22.0014 14.079 22.0015H14.117C14.3087 21.9941 14.4941 21.9307 14.6502 21.8191C14.8062 21.7075 14.9261 21.5526 14.995 21.3735L21.933 3.33649C22.0011 3.15918 22.0164 2.96594 21.977 2.78013C21.9376 2.59432 21.8452 2.4239 21.711 2.28949L15.43 8.56949Z"
    //                                         fill="currentColor" />
    //                                     <path opacity="0.3"
    //                                         d="M20.664 2.06648L2.62602 9.00148C2.44768 9.07085 2.29348 9.19082 2.1824 9.34663C2.07131 9.50244 2.00818 9.68731 2.00074 9.87853C1.99331 10.0697 2.04189 10.259 2.14054 10.4229C2.23919 10.5869 2.38359 10.7185 2.55601 10.8015L7.86601 13.3365C8.02383 13.4126 8.19925 13.4448 8.37382 13.4297C8.54839 13.4145 8.71565 13.3526 8.85801 13.2505L15.43 8.56548L21.711 2.28448C21.5762 2.15096 21.4055 2.05932 21.2198 2.02064C21.034 1.98196 20.8409 1.99788 20.664 2.06648Z"
    //                                         fill="currentColor" />
    //                                 </svg>
    //                             </span>
    //                         </a>
    //                     </div>
    //                 </div>
    
    //             </div>
    
    //         </div>
    //     </div>
    //     `;

    //             $(".property-slide").append(activity_item_html);
    //         });
    //     })
    //     .catch(({ message }) => console.log(message));


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

        // fetch(url)
        // .then(resp => {
        //     if (resp.ok) {
        //         return resp.json();
        //     } else {
        //         const { status, statusText } = resp;
        //         throw Error(`${status}: ${statusText}`);
        //     }
        // })
        // .then(activityCards => {
        //     $("#coming-soon-activities").empty();
        //     const [firstActivityCard, ...restActivityCards] = activityCards;
        //     activityCards.forEach(activityCard => {
        //         //    console.log(activityCard.activityName);

        //         let activity_card_html = `
        //             <!-- Single Property 以一個活動為單位-->
        //             <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
        //                 <div class="property-listing list_view style_new">

        //                     <div class="listing-img-wrapper position-relative">
        //                         <!-- 下面這段是圖片上的標籤，還不確定想放什麼先display none -->
        //                         <div class="position-absolute top-0 left-0 ms-3 mt-3 z-1" style="display: none;">
        //                             <div
        //                                 class="label bg-success text-light d-inline-flex align-items-center justify-content-center" >
        //                                 <span class="svg-icon text-light svg-icon-2hx me-1">
        //                                     <svg width="14" height="14" viewBox="0 0 24 24" fill="none"
        //                                         xmlns="http://www.w3.org/2000/svg">
        //                                         <path opacity="0.3"
        //                                             d="M20.5543 4.37824L12.1798 2.02473C12.0626 1.99176 11.9376 1.99176 11.8203 2.02473L3.44572 4.37824C3.18118 4.45258 3 4.6807 3 4.93945V13.569C3 14.6914 3.48509 15.8404 4.4417 16.984C5.17231 17.8575 6.18314 18.7345 7.446 19.5909C9.56752 21.0295 11.6566 21.912 11.7445 21.9488C11.8258 21.9829 11.9129 22 12.0001 22C12.0872 22 12.1744 21.983 12.2557 21.9488C12.3435 21.912 14.4326 21.0295 16.5541 19.5909C17.8169 18.7345 18.8277 17.8575 19.5584 16.984C20.515 15.8404 21 14.6914 21 13.569V4.93945C21 4.6807 20.8189 4.45258 20.5543 4.37824Z"
        //                                             fill="currentColor"></path>
        //                                         <path
        //                                             d="M14.854 11.321C14.7568 11.2282 14.6388 11.1818 14.4998 11.1818H14.3333V10.2272C14.3333 9.61741 14.1041 9.09378 13.6458 8.65628C13.1875 8.21876 12.639 8 12 8C11.361 8 10.8124 8.21876 10.3541 8.65626C9.89574 9.09378 9.66663 9.61739 9.66663 10.2272V11.1818H9.49999C9.36115 11.1818 9.24306 11.2282 9.14583 11.321C9.0486 11.4138 9 11.5265 9 11.6591V14.5227C9 14.6553 9.04862 14.768 9.14583 14.8609C9.24306 14.9536 9.36115 15 9.49999 15H14.5C14.6389 15 14.7569 14.9536 14.8542 14.8609C14.9513 14.768 15 14.6553 15 14.5227V11.6591C15.0001 11.5265 14.9513 11.4138 14.854 11.321ZM13.3333 11.1818H10.6666V10.2272C10.6666 9.87594 10.7969 9.57597 11.0573 9.32743C11.3177 9.07886 11.6319 8.9546 12 8.9546C12.3681 8.9546 12.6823 9.07884 12.9427 9.32743C13.2031 9.57595 13.3333 9.87594 13.3333 10.2272V11.1818Z"
        //                                             fill="currentColor"></path>
        //                                     </svg>
        //                                 </span>Verified
        //                             </div>
        //                             <div class="label bg-danger text-light d-inline-flex align-items-center justify-content-center ms-1">
        //                                 <span class="svg-icon text-light svg-icon-2hx me-1">
        //                                     <svg width="14" height="14" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        //                                         <path d="M19.0647 5.43757C19.3421 5.43757 19.567 5.21271 19.567 4.93534C19.567 4.65796 19.3421 4.43311 19.0647 4.43311C18.7874 4.43311 18.5625 4.65796 18.5625 4.93534C18.5625 5.21271 18.7874 5.43757 19.0647 5.43757Z" fill="currentColor"/>
        //                                         <path d="M20.0692 9.48884C20.3466 9.48884 20.5714 9.26398 20.5714 8.98661C20.5714 8.70923 20.3466 8.48438 20.0692 8.48438C19.7918 8.48438 19.567 8.70923 19.567 8.98661C19.567 9.26398 19.7918 9.48884 20.0692 9.48884Z" fill="currentColor"/>
        //                                         <path d="M12.0335 20.5714C15.6943 20.5714 18.9426 18.2053 20.1168 14.7338C20.1884 14.5225 20.1114 14.289 19.9284 14.161C19.746 14.034 19.5003 14.0418 19.3257 14.1821C18.2432 15.0546 16.9371 15.5156 15.5491 15.5156C12.2257 15.5156 9.48884 12.8122 9.48884 9.48886C9.48884 7.41079 10.5773 5.47137 12.3449 4.35752C12.5342 4.23832 12.6 4.00733 12.5377 3.79251C12.4759 3.57768 12.2571 3.42859 12.0335 3.42859C7.32556 3.42859 3.42857 7.29209 3.42857 12C3.42857 16.7079 7.32556 20.5714 12.0335 20.5714Z" fill="currentColor"/>
        //                                         <path d="M13.0379 7.47998C13.8688 7.47998 14.5446 8.15585 14.5446 8.98668C14.5446 9.26428 14.7693 9.48891 15.0469 9.48891C15.3245 9.48891 15.5491 9.26428 15.5491 8.98668C15.5491 8.15585 16.225 7.47998 17.0558 7.47998C17.3334 7.47998 17.558 7.25535 17.558 6.97775C17.558 6.70015 17.3334 6.47552 17.0558 6.47552C16.225 6.47552 15.5491 5.76616 15.5491 4.93534C15.5491 4.65774 15.3245 4.43311 15.0469 4.43311C14.7693 4.43311 14.5446 4.65774 14.5446 4.93534C14.5446 5.76616 13.8688 6.47552 13.0379 6.47552C12.7603 6.47552 12.5357 6.70015 12.5357 6.97775C12.5357 7.25535 12.7603 7.47998 13.0379 7.47998Z" fill="currentColor"/>
        //                                     </svg>
        //                                 </span>New
        //                             </div>
        //                             <div class="label bg-purple text-light d-inline-flex align-items-center justify-content-center">
        //                                 <span class="svg-icon text-light svg-icon-2hx me-1">
        //                                     <svg width="14" height="14" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        //                                         <path opacity="0.3" d="M20.5543 4.37824L12.1798 2.02473C12.0626 1.99176 11.9376 1.99176 11.8203 2.02473L3.44572 4.37824C3.18118 4.45258 3 4.6807 3 4.93945V13.569C3 14.6914 3.48509 15.8404 4.4417 16.984C5.17231 17.8575 6.18314 18.7345 7.446 19.5909C9.56752 21.0295 11.6566 21.912 11.7445 21.9488C11.8258 21.9829 11.9129 22 12.0001 22C12.0872 22 12.1744 21.983 12.2557 21.9488C12.3435 21.912 14.4326 21.0295 16.5541 19.5909C17.8169 18.7345 18.8277 17.8575 19.5584 16.984C20.515 15.8404 21 14.6914 21 13.569V4.93945C21 4.6807 20.8189 4.45258 20.5543 4.37824Z" fill="currentColor"/>
        //                                         <path d="M12.0006 11.1542C13.1434 11.1542 14.0777 10.22 14.0777 9.0771C14.0777 7.93424 13.1434 7 12.0006 7C10.8577 7 9.92348 7.93424 9.92348 9.0771C9.92348 10.22 10.8577 11.1542 12.0006 11.1542Z" fill="currentColor"/>
        //                                         <path d="M15.5652 13.814C15.5108 13.6779 15.4382 13.551 15.3566 13.4331C14.9393 12.8163 14.2954 12.4081 13.5697 12.3083C13.479 12.2993 13.3793 12.3174 13.3067 12.3718C12.9257 12.653 12.4722 12.7981 12.0006 12.7981C11.5289 12.7981 11.0754 12.653 10.6944 12.3718C10.6219 12.3174 10.5221 12.2902 10.4314 12.3083C9.70578 12.4081 9.05272 12.8163 8.64456 13.4331C8.56293 13.551 8.49036 13.687 8.43595 13.814C8.40875 13.8684 8.41781 13.9319 8.44502 13.9864C8.51759 14.1133 8.60828 14.2403 8.68991 14.3492C8.81689 14.5215 8.95295 14.6757 9.10715 14.8208C9.23413 14.9478 9.37925 15.0657 9.52439 15.1836C10.2409 15.7188 11.1026 15.9999 11.9915 15.9999C12.8804 15.9999 13.7421 15.7188 14.4586 15.1836C14.6038 15.0748 14.7489 14.9478 14.8759 14.8208C15.021 14.6757 15.1661 14.5215 15.2931 14.3492C15.3838 14.2312 15.4655 14.1133 15.538 13.9864C15.5833 13.9319 15.5924 13.8684 15.5652 13.814Z" fill="currentColor"/>
        //                                     </svg>
        //                                 </span>SuperAgent
        //                             </div>
        //                         </div>
        //                         <!-- 活動圖片放這裡 -->
        //                         <div class="list-img-slide">
        //                             <div class="clior">
        //                                 <div><a href="#"><img
        //                                             src="https://placehold.co/1200x800"
        //                                             class="img-fluid mx-auto" alt="" /></a></div>
        //                                 <div><a href="#"><img
        //                                             src="https://placehold.co/1200x800"
        //                                             class="img-fluid mx-auto" alt="" /></a></div>
        //                                 <div><a href="#"><img
        //                                             src="https://placehold.co/1200x800"
        //                                             class="img-fluid mx-auto" alt="" /></a></div>
        //                             </div>
        //                         </div>
        //                     </div>
        //                     <!-- 活動返回資訊 1 -->
        //                     <div class="list_view_flex">

        //                         <div class="listing-detail-wrapper mt-1">
        //                             <div class="listing-short-detail-wrap">
        //                                 <div class="_card_list_flex mb-2">
        //                                     <div class="_card_flex_01 d-flex align-items-center">
        //                                         <span class="label bg-light-danger text-danger me-2">For
        //                                             Sell</span>
        //                                     </div>
        //                                     <div class="_card_flex_last">
        //                                         <h6 class="listing-info-price text-primary fs-4 mb-0">$167M</h6>
        //                                     </div>
        //                                 </div>
        //                                 <div class="_card_list_flex">
        //                                     <div class="_card_flex_01" style="visibility: hidden;">
        //                                         <h3 class="listing-name"><a href="single-property-1.html"
        //                                                 class="prt-link-detail"> Resot Relly Market, Montreal
        //                                                 Canad4789a, HAQC445</a></h3>
        //                                     </div>
        //                                 </div>
        //                                 <div class="_card_list_flex">
        //                                     <div class="_card_flex_01">
        //                                         <h4 class="listing-name"><a href="single-property-1.html"
        //                                                 class="prt-link-detail"> 網球</a></h4>
        //                                     </div>
        //                                 </div>
        //                             </div>
        //                         </div>
        //                         <!-- 活動返回資訊 2 -->
        //                         <div class="price-features-wrapper">
        //                             <div class="list-fx-features d-flex align-items-center justify-content-between">
        //                                 <div class="listing-card d-flex align-items-center">
        //                                     <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2"><i class="fa-solid fa-building-shield fs-sm"></i></div><span class="text-muted-2">3BHK</span>
        //                                 </div>
        //                                 <div class="listing-card d-flex align-items-center">
        //                                     <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2"><i class="fa-solid fa-bed fs-sm"></i></div><span class="text-muted-2">3 Beds</span>
        //                                 </div>
        //                                 <div class="listing-card d-flex align-items-center">
        //                                     <div class="square--30 text-muted-2 fs-sm circle gray-simple me-2"><i class="fa-solid fa-clone fs-sm"></i></div><span class="text-muted-2">1800 SQFT</span>
        //                                 </div>
        //                             </div>
        //                         </div>
        //                         <!-- 幾顆星 -->
        //                         <div
        //                             class="listing-detail-footer d-flex align-items-center justify-content-between">
        //                             <div class="footer-first">
        //                                 <div class="foot-rates">
        //                                     <span class="elio_rate perfect">4.7</span>
        //                                     <div class="_rate_stio">
        //                                         <i class="fa fa-star"></i>
        //                                         <i class="fa fa-star"></i>
        //                                         <i class="fa fa-star"></i>
        //                                         <i class="fa fa-star"></i>
        //                                         <i class="fa fa-star"></i>
        //                                     </div>
        //                                 </div>
        //                             </div>
        //                             <div class="footer-flex">
        //                                 <a href="#"
        //                                     class="btn btn-md btn-primary fw-medium">View Detail</a>
        //                             </div>
        //                         </div>
        //                     </div>

        //                 </div>
        //             </div>
        //         `;

        //         $("#coming-soon-activities").append(activity_card_html);
        //     });
        // })
        // .catch(({ message }) => console.log(message));

    })

});