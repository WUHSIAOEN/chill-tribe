//從後端拿到資料
// function getActivityIdFromURL() {
//   const params = new URLSearchParams(window.location.search);
//   return params.get("id");
// }

// function fetchActivityById(id) {
//   fetch('/chill-tribe/activity/edit')
//     .then((response) => response.json())
//     .then((data) => {
//       console.log("從 後端獲取的數據:", data);
//       document.getElementById("activityName").value = data.activityName;
//       document.getElementById("address").value = data.address;
//       document.getElementById("category").value = data.category;
//       document.getElementById("unitPrice").value = data.unitPrice;
//       document.getElementById("minParticipants").value = data.minParticipants;
//       document.getElementById("maxParticipants").value = data.maxParticipants;
//       document.getElementById("description").value = data.description;
//       document.getElementById("precaution").value = data.precaution;
//     });
// }

// 更新
function newActivityData() {
  activity_id = 1;
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

  return {
    activity_id,
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

  fetch('/chill-tribe/activity/edit', {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestData),
  })
  .then((data) => {
    console.log("從 前端送出的數據:", data);
  });
});
