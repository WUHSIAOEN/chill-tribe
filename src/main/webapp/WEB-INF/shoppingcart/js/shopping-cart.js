$(function(){

    // ============== 假資料↓ =================
    // 先塞假會員資料到SessionStorage
    let MemberData = {
        memberId: 1
    }
    sessionStorage.setItem('MemberData', JSON.stringify(MemberData));
    // ============== 假資料↑ =================


    // ============== 自訂函式↓ =================

    // 函式 - 取得會員資料
    function getMemberData() {
        const memberData = sessionStorage.getItem('MemberData');
        if (memberData) {
            return JSON.parse(memberData);
        }
        return null;
    }
    // ============== 自訂函式↑ =================

    // 將會員購物車內容撈回來
    // path 要再改成從session 取得
    fetch(`/chill-tribe/shoppingcart/list/${getMemberData().memberId}`)
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
            $("#shopping-cart-item").remove();

            // 將itemDOM字串塞到DOM裡
            for (let i = 0; i < shoppingCartItems.length; i++) {

            // 將Item DOM 字串抓出來
            let itemDOM = `
            <!-- Item #1 -->
            <tr id="shopping-cart-item">
                <td class="action text-center">
                    <input type="radio" class="form-check-input item-button" name="shopping-cart-item">
                </td>
                <td class="property-container">
                    <img src="${shoppingCartItems[i]?.activity?.activityImages[0]?.imageBase64 || '../activity/asset/no-image.jpg'}" alt="${shoppingCartItems[i]?.activity?.activityImages[0]?.imageName || 'No Image'}" />
                    <div class="title">
                        <h4><a href="#">${shoppingCartItems[i].activity.activityName}</a></h4>
                        <span>${shoppingCartItems[i].activity.supplier.supplier_name}</span>
                    </div>
                </td>
                <td>
                    <span class="table-property-price">$${shoppingCartItems[i].activity.unitPrice} /
                        人</span>
                </td>
                <td>
                    <input type="number" class="form-control text-center mx-2"
                        value="${shoppingCartItems[i].quantity}" min="${shoppingCartItems[i].activity.minParticipants}" max="${shoppingCartItems[i].activity.maxParticipants}" />
                </td>
                <td class="action">
                    <a href="#" class="delete"><i class="fa-solid fa-trash-can"></i>
                        Delete</a>
                </td>
            </tr>
            `;

            $("#shopping-cart-list").append(itemDOM);
            }
            // console.log(shoppingCartItems);

            // 預設button 在第一個item
            $("#item-button").prop("checked", true);

            
        })
        .catch(error => {            
            console.error(error);
        });


    // 計算總價 - 要抓單選的活動: 單價*數量
    // 
    // 綁定button click 事件
    
    
    
    
    



























})