// 從後端拿到資料
function getActivityIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("id");
}

function fetchActivityById(id) {
  fetch(`/chill-tribe/activity/findActivityById?id=${id}`)
    .then((response) => response.json())
    .then((data) => {
      console.log("從 Servlet 獲取的數據:", data);
      document.getElementById("activityName").value = data.activityName;
      document.getElementById("address").value = data.address;
      document.getElementById("category").value = data.category;
      document.getElementById("unitPrice").value = data.unitPrice;
      document.getElementById("minParticipants").value = data.minParticipants;
      document.getElementById("maxParticipants").value = data.maxParticipants;
      document.getElementById("description").value = data.description;
      document.getElementById("precaution").value = data.precaution;
    });
}
const activityId = getActivityIdFromURL();
if (activityId) {
  fetchActivityById(activityId);
} else {
  console.log("你要的活動找不到喔!");
}

// 刪除

document
  .getElementById("removeBtn")
  .addEventListener("click", function (event) {
    event.preventDefault(); // 防止表單默認提交行為

    const activityId = getActivityIdFromURL();
    if (activityId) {
      removeActivityById(activityId);
      console.log("刪除活動成功!");
    } else {
      console.log("你要的活動找不到喔!");
    }
  });

function removeActivityById(id) {
  fetch(`/chill-tribe/activity/remove?id=${id}`)
    .then((resp) => resp.json())
    .then((body) => {
      location.reload();
    });
}
