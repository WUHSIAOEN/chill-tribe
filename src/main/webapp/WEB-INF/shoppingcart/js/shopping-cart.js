$(function(){

    // ============== 假資料↓ =================
    // 先塞假會員資料到SessionStorage
    let memberid = {
        memberid: 1
    }
    sessionStorage.setItem('memberid', JSON.stringify(memberid));
    // ============== 假資料↑ =================


    // ============== 自訂函式↓ =================

    // 函式 - 取得會員資料
    function getMemberData() {
        const memberData = sessionStorage.getItem('memberid');
        if (memberData) {
            return JSON.parse(memberData);
        }
        return null;
    }

    // 函式 - 計算總價 - 單價*數量
    function calculateTotalPrice() {
        var checkedQuantity = $('#shopping-cart-list input[type="radio"]:checked')
        .closest('tr')
        .find('td .item-quantity')
        .val();

        var unitPrice = $('#shopping-cart-list input[type="radio"]:checked')
        .closest('tr')
        .find('.table-property-price')
        .attr('data-price');

        var totalPrice_el = $('.total-price');
        var totalPrice = checkedQuantity * unitPrice;

        totalPrice_el.text(`總計: NT$${totalPrice}`);
    }


    // ============== 自訂函式↑ =================

    // 將會員購物車內容撈回來
    // path 要再改成從session 取得
    fetch(`/chill-tribe/shoppingcart/list/${getMemberData().memberid}`)
    .then(resp => {
        if (resp.ok) {
            return resp.json();
        } else {
            const { status, statusText } = resp;
            throw Error(`${status}: ${statusText}`);
        }
    })
    .then(shoppingCartItems => {

        // 清空item DOM
        $(".shopping-cart-item").remove();

        // 將itemDOM字串塞到DOM裡
        for (let i = 0; i < shoppingCartItems.length; i++) {

        // 將Item DOM 字串抓出來
        let itemDOM = `
        <!-- Item #1 -->
        <tr id="shopping-cart-item">
            <td class="action text-center">
                <input type="radio" class="form-check-input item-button" name="shopping-cart-item" value="${shoppingCartItems[i]?.activity?.activityId}" />
            </td>
            <td class="property-container">
                <img src="${shoppingCartItems[i]?.activity?.activityImages[0]?.imageBase64 || '../activity/asset/no-image.jpg'}" alt="${shoppingCartItems[i]?.activity?.activityImages[0]?.imageName || 'No Image'}" />
                <div class="title">
                    <h4><a href="#">${shoppingCartItems[i].activity.activityName}</a></h4>
                    <span>${shoppingCartItems[i].activity.supplier.supplier_name}</span>
                </div>
            </td>
            <td>
                <span class="table-property-price" data-price="${shoppingCartItems[i].activity.unitPrice}">$${shoppingCartItems[i].activity.unitPrice} /
                    人</span>
            </td>
            <td>
                <input type="number" class="form-control text-center mx-2 item-quantity"
                    value="${shoppingCartItems[i].quantity}" min="1" max="${shoppingCartItems[i].activity.inventoryCount}" />
            </td>
            <td class="action">
                <a href="#" class="delete"><i class="fa-solid fa-trash-can"></i>
                    刪除</a>
            </td>
        </tr>
        `;

        $("#shopping-cart-list").append(itemDOM);
        }
        // console.log(shoppingCartItems);

        // 如果購物車有東西，就將第一個item的radio button打勾
        if (shoppingCartItems.length > 0) {
            $("#shopping-cart-list .item-button").first().prop("checked", true);
            calculateTotalPrice()
        }

        // 預設計算總價 - 將第一個項目的總計做計算
        
    })
    .catch(error => {            
        console.error(error);
    });


    // 綁定radio button change 事件
    $('#shopping-cart-list').on('change', 'input[type="radio"]', function() {
        calculateTotalPrice();
    });

    // 綁定數量input change 事件
    $('#shopping-cart-list').on('change', '.item-quantity', function() {
        calculateTotalPrice();
    });


    // 綁定刪除按鈕事件
    // $('#shopping-cart-list').on('click', '.delete', function() {
    //     // 取得被刪除的item的activityId
    //     const activityId = $(this).closest('tr').find('.item-button').val();

    //     // 刪除該item
    //     $(this).closest('tr').remove();

    //     // 如果購物車沒有東西，就將總價歸零
    //     if ($('#shopping-cart-list tr').length === 0) {
    //         $('.total-price').text('總計: NT$0');
    //     }

    //     // 刪除購物車內容
    //     fetch(`/chill-tribe/shoppingcart/delete/${getMemberData().memberId}/${activityId}`, {
    //         method: 'DELETE'
    //     })
    //     .then(resp => {
    //         if (resp.ok) {
    //             return resp.json();
    //         } else {
    //             const { status, statusText } = resp;
    //             throw Error(`${status}: ${statusText}`);
    //         }
    //     })
    //     .then(data => {
    //         console.log(data);
    //     })
    //     .catch(error => {
    //         console.error(error);
    //     });
    // }

    // 進入結帳頁面
    $('#checkout').on('click', function(e) {
        e.preventDefault();

        // 取得被選取的item的activityId
        var activityId = $('#shopping-cart-list input[type="radio"]:checked').closest('tr').find('.item-button').val();
        var memberId = getMemberData();
        var quantity = $('#shopping-cart-list input[type="radio"]:checked').closest('tr').find('.item-quantity').val();
        var unitPrice = $('#shopping-cart-list input[type="radio"]:checked').closest('tr').find('.table-property-price').attr('data-price');
        var orderStatus, paymentMethod;
        if (unitPrice === "0") {
            orderStatus = "no_payment_required";
            paymentMethod = "none";
        } else {
            orderStatus = "pending_payment";
            paymentMethod = "credit_card";
        }
        const checkoutActivity = {
            // memberId: memberId,
            activityId: activityId,
            quantity: quantity,
            unitPrice: unitPrice,
            orderStatus: orderStatus,
            paymentMethod: paymentMethod
        };

        console.log(checkoutActivity);

        // 將activity 存到sessionStorage
        localStorage.setItem('checkoutActivity', JSON.stringify(checkoutActivity));

        // 導向結帳頁面
        window.location.href = '/chill-tribe/order/order-form.html';
    });
    
    
    
    
    



























})