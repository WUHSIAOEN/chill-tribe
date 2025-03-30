// 從 URL 取得 活動 ID
function getActivityIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("id");
}
const activityId = getActivityIdFromURL();

function fetchActivityById(activityId) {
  

  fetch(`${APP_CONFIG.BASE_URL}supplier/activities/edit/${activityId}`)
    .then((response) => response.json())
    .then((data) => {
      console.log("從 後端獲取的數據:", data);

      document.getElementById("activityName").value = data.activityName;
      document.getElementById("address").value = data.address;
      document.getElementById("category").value = data.category;
      document.getElementById("unitPrice").value = data.unitPrice;
      document.getElementById("minParticipants").value = data.minParticipants;
      document.getElementById("maxParticipants").value = data.maxParticipants;
      document.getElementById("description").value = data.description;
      document.getElementById("precaution").value = data.precaution;

      window.base64Images = [];
      if (data.activityImages && data.activityImages.length > 0) {
        const previewContainer = document.getElementById("previewContainer");

        data.activityImages.forEach(imageData => {
          const base64String = imageData.imageBase64;

          window.base64Images.push(base64String);

          const previewDiv = document.createElement("div");
          previewDiv.classList.add("position-relative", "m-2");
          previewDiv.style.width = "160px";
          previewDiv.style.height = "120px";

          const img = document.createElement("img");
          img.src = base64String;
          img.classList.add("img-thumbnail", "w-100", "h-100");

          const removeBtn = document.createElement("button");
          removeBtn.innerHTML = "✖";
          removeBtn.classList.add("remove-btn", "position-absolute");

          removeBtn.style.fontSize = "12px";
          removeBtn.style.width = "20px";
          removeBtn.style.height = "20px";

          removeBtn.addEventListener("click", () => {
            previewDiv.remove();

            const index = window.base64Images.indexOf(base64String);
            if (index !== -1) {
              window.base64Images.splice(index, 1);
            }
          });

          previewDiv.appendChild(img);
          previewDiv.appendChild(removeBtn);
          previewContainer.appendChild(previewDiv);
        });
      }
    });
}

if (activityId) {
  fetchActivityById(activityId);
} else {
  console.log("你要的活動找不到喔!");
};

// 更新
function newActivityData() {
  const activityId = getActivityIdFromURL();
  const supplierId = localStorage.getItem('supplier_id');
  const activityName = document.getElementById("activityName")?.value || "";
  const cityId = parseInt(document.getElementById("cityId")?.value) || 1;
  const districtId = parseInt(document.getElementById("districtId")?.value) || 3;
  const address = document.getElementById("address")?.value || "";
  const category = document.getElementById("category")?.value || "";
  const unitPrice = parseFloat(document.getElementById("unitPrice")?.value) || 0;
  const minParticipants =
    parseInt(document.getElementById("minParticipants")?.value) || 4;
  const maxParticipants =
    parseInt(document.getElementById("maxParticipants")?.value) || 10;
  const inventoryCount =
    parseInt(document.getElementById("inventoryCount")?.value) || 10;
  const description = document.getElementById("description")?.value || "";
  const precaution = document.getElementById("precaution")?.value || "";
  const note = document.getElementById("note")?.value || "";

  const selectedRange = document.getElementById("reservationtime")?.value || "";
  const selectedRange_1 = document.getElementById("reservationtime_1")?.value || "";
  const images = window.base64Images;

  function formatDateTime(input) {
    if (!input) return "";

    const date = new Date(input);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = "00";

    return `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`;
  }

  const [startRaw, endRaw] = selectedRange.split(" - ");
  const [startRaw_1, endRaw_1] = selectedRange_1.split(" - ");
  const startDateTime = formatDateTime(startRaw);
  const endDateTime = formatDateTime(endRaw);
  const ticketsActivateTime = formatDateTime(startRaw_1);
  const ticketsExpiredTime = formatDateTime(endRaw_1);

  return {
    activityId,
    supplierId,
    activityName,
    // cityId,
    // districtId,
    address,
    startDateTime,
    endDateTime,
    ticketsActivateTime,
    ticketsExpiredTime,
    unitPrice,
    minParticipants,
    maxParticipants,
    // inventoryCount,
    description,
    category,
    precaution
    // not required
  };  
}

document
.getElementById("updateBtn")
.addEventListener("click", function (event) {
  event.preventDefault();
  const images = window.base64Images;
  const requestData = newActivityData();

  fetch(`${APP_CONFIG.BASE_URL}supplier/activities/edit/${activityId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestData),
  })
  .then(response => response.json())
    .then(data => {
      if (data.successful) {
        const activityId = data.activityId;

        // 創造新物件，每個物件都含有 {id: a, base64: b}
        const requestImages = images.map(image => ({
          activityId: activityId,
          imageBase64: image
        }));
  
        // 首先獲取活動資料 - Get
        fetch(`${APP_CONFIG.BASE_URL}supplier/activities/edit/${activityId}`)
          .then(response => response.json())
          .then(activity => {
            console.log('活動信息:', activity);
  
            // 然後發送第二次 PUT 請求來修改圖片
            fetch(`${APP_CONFIG.BASE_URL}supplier/activities/edit/${activityId}/images`, {
              method: "PUT",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(requestImages),
            })
              .then(response => response.json())
              .then(uploadedPictures => {
                console.log('圖片上傳成功:', uploadedPictures);

                setTimeout(function() {
                  window.location.href = `${APP_CONFIG.BASE_URL}supplier/activitySpList.html`;
                }, 1000);
              })
              .catch(error => console.error('圖片上傳失敗:', error));
          })
          .catch(error => console.error('獲取活動失敗:', error));
      } else {
        console.log('圖片更新失敗:', data.message);
      }
    })
    .catch(error => {
      console.error("活動更新失敗:", error);
    })
  .catch(error => console.error('獲取活動失敗:', error));
});
