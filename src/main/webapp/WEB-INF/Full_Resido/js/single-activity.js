// 從 URL 取得 活動 ID
function getActivityIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("id");
};

const activityId = getActivityIdFromURL();

function fetchActivityById(activityId) {
  fetch(`/chill-tribe/activities/${activityId}`)
  .then(response => response.json())
  .then(data => {
    console.log("從 後端 獲取的數據:", data);
    // 更新 HTML 內容

    let imageGallery = document.getElementById("imageGallery");
    let imageGallery2 = document.getElementById("imageGallery2");
    imageGallery.innerHTML = "";
    imageGallery2.innerHTML = "";

    // 背景圖片
    if (data.activityImages.length > 0) {
      data.activityImages.forEach(image => {
          let base64Image = `${image.imageBase64}`;
          let imageElement = `
              <div class="featured_slick_padd">
                  <a href="${base64Image}" class="mfp-gallery">
                      <img src="${base64Image}" class="img-fluid mx-auto custom-img" alt="活動圖片" />
                  </a>
              </div>
          `;
          imageGallery.innerHTML += imageElement;

          let imageElement2 = `
              <li>
                <a href="https://placehold.co/1200x800" class="mfp-gallery">
                  <img src="${base64Image}" class="img-fluid mx-auto" alt="活動圖片" />
                </a>
              </li>
          `;
          imageGallery2.innerHTML += imageElement2;
      });

      // **重新初始化 Slick 輪播 **
      if ($(".featured_slick_gallery-slide").hasClass("slick-initialized")) {
              $(".featured_slick_gallery-slide").slick("unslick");
          }
        $(".featured_slick_gallery-slide").slick({
            slidesToShow: 2,
            slidesToScroll: 1,
            autoplay: false,
            autoplaySpeed: 3000,
            dots: false,
            arrows: true
        });
    }

    // 評論
    if (data.comments.length > 0) {

      let cBody = document.getElementById('comment-body');
      cBody.innerHTML = "";
      data.comments.forEach(comment => {
        let memberPhoto;
        if (memberPhoto != null) {
          memberPhoto = `${data.comments.member?.photobase64}`;
        } else {
          memberPhoto = "https://placehold.co/500x500";
        }
        let totalComments = data.comments.length;
        let commentName = comment.member.membername
        let commentTime = comment.commentTime;
        let content = comment.content;
        let staring = comment.starRating;

        let commentElement = document.createElement("li");

        // 設置 data-id，方便查找對應商品
        commentElement.setAttribute("comment-id", comment.member.commentId);
        commentElement.innerHTML = `
          <li class="article_comments_wrap">
            <article>
              <div class="article_comments_thumb">
                <img src="${memberPhoto}" alt="">
              </div>
              <div class="comment-details">
                <div class="comment-meta">
                  <div class="comment-left-meta d-flex align-items-center justify-content-between">
                    <h4 id="author-name" class="author-name">${commentName}</h4>
                      <div id="staring" class="staring" comment-id="${comment.commentId}">
                      <span class="star" data-value="1">&#9733;</span>
                      <span class="star" data-value="2">&#9733;</span>
                      <span class="star" data-value="3">&#9733;</span>
                      <span class="star" data-value="4">&#9733;</span>
                      <span class="star" data-value="5">&#9733;</span>
                    </div>
                  </div>
                  <div id="comment-date" class="comment-date">${commentTime}</div>
                </div>
                <div id="comment-text" class="comment-text">
                  <p>${content}</p>
                </div>
              </div>
            </article>
          </li>
        `;
        cBody.appendChild(commentElement);
        document.getElementById("comment-count").textContent = `${totalComments} 則留言`;

        let stars = commentElement.querySelectorAll('.star');

        // 星星
        stars.forEach((star, index) => {
            if (index < staring) {
                star.style.color = "gold";
            } else {
                star.style.color = "gray";
            }
        });
      });
    } else {
      let cBody = document.getElementById('comment-body');
      let commentCount = document.getElementById("comment-count");
      commentCount.textContent = "";
      
      cBody.innerHTML = `
        <div colspan="5" style="text-align: center; color: gray;">
          暫無評論
        </div>
      `;
      
      commentCount.textContent = "";
    };
    
    // 供應商
    console.log(data.supplier.supplier_name)
    let sBody = document.getElementById('supplier-body');
    sBody.innerHTML = ""; // 清空目前的內容

    let supplierName = data.supplier.supplier_name;
    let supplierPhone = data.supplier.phone;
    let supplierImage = data.supplier?.image || "https://placehold.co/500x500";

    let supplierElement = document.createElement("div");
    supplierElement.classList.add("sides-widget-header", "bg-primary");

    supplierElement.innerHTML = `
      <div class="agent-photo">
        <img src="${supplierImage}" alt="Supplier Image">
      </div>
      <div class="sides-widget-details">
        <h4><a href="#">${supplierName}</a></h4>
        <span><i class="lni-phone-handset"></i>${supplierPhone}</span>
      </div>
      <div class="clearfix"></div>
    `;

    sBody.appendChild(supplierElement);


    document.getElementById("activityName-1").innerHTML = data.activityName || "暫無";
    document.getElementById("activityName-2").innerHTML = data.activityName || "暫無";
    document.getElementById("address").innerHTML = data.address || "暫無";
    document.getElementById("unitPrice-1").textContent = data.unitPrice || "暫無";
    document.getElementById("unitPrice-2").textContent = data.unitPrice || "暫無";
    document.getElementById("minParticipants").innerHTML = data.minParticipants || "暫無";
    document.getElementById("maxParticipants").innerHTML = data.maxParticipants || "暫無";
    document.getElementById("description").innerHTML = data.description || "暫無";
    document.getElementById("precaution").innerHTML = data.precaution || "暫無";
    document.getElementById("category-1").innerHTML = data.category || "暫無";
    document.getElementById("category-2").innerHTML = data.category || "暫無";
    document.getElementById("startDateTime").innerHTML = data.startDateTime || "暫無";
    document.getElementById("endDateTime").innerHTML = data.endDateTime || "暫無";

    const city = addrData.find(item => item.city_id === data.city_id);
    const district = city?.area.find(area => area.zipcode === data.district_id);
    console.log(city)
    console.log(district)
    document.getElementById("cityId").innerHTML = city ? city.city : "未知";
  })
  
}

if (activityId) {
  fetchActivityById(activityId);
} else {
  console.log("你要的活動找不到喔!");
};


// 新增活動商品到購物車
function ShoppingCartItems() {
  const activityId = parseInt(getActivityIdFromURL()) || 1;
  const quantity = parseInt(document.getElementById("quantity")?.value) || 1;
  const unitPrice = parseInt(document.getElementById("unitPrice-2").textContent);
  const totalPrice = quantity * unitPrice;
  const memberId = parseInt(document.getElementById("member_id")?.value) || 1;
  console.log(activityId, quantity, unitPrice);
  return {
    activityId,
    quantity,
    totalPrice,
    memberId
  };
};

document
.getElementById("addToCartBtn")
.addEventListener("click", function (event) {
  event.preventDefault();
  const userConfirmed = window.confirm("確定要加入此商品到購物車嗎？");

  if (userConfirmed) {
    const requestData = ShoppingCartItems();
    console.log("送出的資料:", requestData);
    fetch(`/chill-tribe/cart/${activityId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    })
  }	
});

// 新增活動商品到我的最愛
function MyFavoritesItems() {
  const activityId = parseInt(getActivityIdFromURL()) || 1;
  const memberId = parseInt(document.getElementById("member_id")?.value) || 1;
  console.log(activityId, memberId);

  return {
    activityId,
    memberId
  };
};

document
.getElementById("addToFavoritesBtn")
.addEventListener("click", function (event) {
  event.preventDefault();
  const userConfirmed = window.confirm("確定要加入此商品到我的最愛嗎？");
  if (userConfirmed) {
    const requestMyforitesData = MyFavoritesItems();
    console.log("送出的資料:", requestMyforitesData);
    fetch(`/chill-tribe/myfavorites/${activityId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestMyforitesData),
    })
  }
});


// 取得使用者輸入的評論和評分
// 送出評分
document.getElementById('commentBtn').addEventListener('click', function(event) {
  event.preventDefault();

  function getFormattedTime() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0'); // 月份從 0 開始，所以要 +1
    const day = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    
    return `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`;
  }
  
  let content = document.getElementById('content').value.trim();
  content = content.replace(/\s+/g, ' ');
  const starRating = parseInt(document.getElementById('starRating').value);
  const commentTime = getFormattedTime(); // 獲取當前時間
  const memberId = 1;

  const reviewData = [{
    content: content,
    starRating: starRating,
    commentTime: commentTime,
    memberId: memberId
  }];

  console.log(reviewData);

  if (!content || !starRating) {
    alert("請留下評論和星星");
    return;
  } else {
    const userConfirmed = window.confirm("確定要送出活動評論嗎？");
      if (userConfirmed) {
        console.log("送出的資料:", reviewData);
        fetch(`/chill-tribe/activities/${activityId}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(reviewData),
      })
    }
  };
});