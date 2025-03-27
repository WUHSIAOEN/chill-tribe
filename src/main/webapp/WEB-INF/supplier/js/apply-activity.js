document.getElementById("submitBtn").addEventListener("click", function (event) {
  event.preventDefault(); // 防止表單默認提交行為

  // 從 localstorage 拿後端傳來的會員資料
  const supplierId = localStorage.getItem('supplier_id');
  
  const activityName = document.getElementById("activityName")?.value;
  const cityId = parseInt(document.getElementById("city_id")?.value) || 1;
  const districtId = parseInt(document.getElementById("area")?.value) || 2;
  const address = document.getElementById("address")?.value;
  const category = document.getElementById("category")?.value || "";
  const unitPrice = parseFloat(document.getElementById("unitPrice")?.value);
  const minParticipants = parseInt(document.getElementById("minParticipants")?.value);
  const maxParticipants = parseInt(document.getElementById("maxParticipants")?.value)
  const inventoryCount = parseInt(document.getElementById("maxParticipants")?.value);
  const description = document.getElementById("description")?.value;
  const precaution = document.getElementById("precaution")?.value || "";
  const selectedRange = document.getElementById("reservationtime")?.value;
  const selectedRange_1 = document.getElementById("reservationtime_1")?.value;
  const images = window.base64Images;

  // 檢查欄位是否為空
  if (!activityName) {
    alert("活動名稱不能為空！");
    activityName.focus();
    return;
  };

  if (!address) {
    alert("詳細地址不能為空！");
    address.focus();
    return;
  };

  if (!unitPrice) {
    alert("參加費用不能為空！");
    unitPrice.focus();
    return;
  };

  if (!minParticipants) {
    alert("成團人數不能為空！");
    minParticipants.focus();
    return;
  };

  if (!maxParticipants) {
    alert("人數上限不能為空！");
    maxParticipants.focus();
    return;
  };

  if (!description) {
    alert("介紹不能為空！");
    description.focus();
    return;
  };

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

  const requestData = {
    supplierId,
    activityName,
    cityId,
    districtId,
    address,
    startDateTime,
    endDateTime,
    ticketsActivateTime,
    ticketsExpiredTime,
    unitPrice,
    minParticipants,
    maxParticipants,
    inventoryCount,
    description,
    category,
    precaution,
    // not required
  };

  console.log("Sending JSON text:", JSON.stringify(requestData));

  // 申請活動 - Post
  fetch("http://localhost:8080/chill-tribe/supplier/applyAct", {
    method: "POST",
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
        fetch(`http://localhost:8080/chill-tribe/supplier/applyAct/${activityId}`)
          .then(response => response.json())
          .then(activity => {
            console.log('活動信息:', activity);
  
            // 然後發送第二次 POST 請求來提交圖片 - Post
            fetch(`http://localhost:8080/chill-tribe/supplier/applyAct/${activityId}`, {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(requestImages),
            })
              .then(response => response.json())
              .then(uploadedPictures => {
                console.log('圖片上傳成功:', uploadedPictures);
                
                // 上傳成功後跳轉到活動總攬頁面
                window.location.href = "http://localhost:8080/chill-tribe/supplier/activitySpList.html";
              })
              .catch(error => console.error('圖片上傳失敗:', error));
          })
          .catch(error => console.error('獲取活動失敗:', error));
      } else {
        console.log('申請活動失敗:', data.message);
      }
    })
    .catch(error => {
      console.error("活動創建失敗:", error);
    });
});
