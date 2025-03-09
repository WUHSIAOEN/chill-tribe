// 從 URL 取得 活動 ID
function getActivityIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("id");
}
const activityId = getActivityIdFromURL();

function fetchActivityById(activityId) {
  if (!window.base64Images) {
    window.base64Images = [];
  }

  fetch(`/chill-tribe/supplier/activities/edit/${activityId}`)
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

      if (data.activityImages && data.activityImages.length > 0) {
        const previewContainer = document.getElementById("previewContainer");

        data.activityImages.forEach(imageData => {
          const base64String = imageData.imageBase64;

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
  const supplierId = parseInt(document.getElementById("supplierId")?.value) || 1;
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
  const startDateTime = formatDateTime(startRaw);
  const endDateTime = formatDateTime(endRaw);

  const requestImages = images.map(image => ({
    activityId: activityId,
    imageBase64: image
  }));

  return {
    activityId,
    // supplierId,
    activityName,
    // cityId,
    // districtId,
    address,
    startDateTime,
    endDateTime,
    unitPrice,
    minParticipants,
    maxParticipants,
    // inventoryCount,
    description,
    category,
    precaution,
    // not required
  };  
}

document
.getElementById("updateBtn")
.addEventListener("click", function (event) {
  event.preventDefault();
  const requestData = newActivityData();

  fetch(`/chill-tribe/supplier/activities/edit/${activityId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestData),
  })
  .then((data) => {
    console.log("從 前端送出的數據:", data);
  })
});
