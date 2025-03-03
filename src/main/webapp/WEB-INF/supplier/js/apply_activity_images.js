// function getActivityIdFromURL() {
//     const params = new URLSearchParams(window.location.search);
//     return params.get("id");
// };

// getActivityIdFromURL();

fetch(`/chill-tribe/supplier/applyAct/images/${activityId}`, {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
  },
})
.then(response => response.json())
.then(data => {
  console.log("活動資料", data);
})
.catch(error => {
  console.error("活動資料:", error);
});

document.getElementById("submitBtn").addEventListener("click", function (event) {
  event.preventDefault(); // 防止表單默認提交行為
  const images = window.base64Images;

  const requestImages = {
    images
  }

  console.log("Sending JSON:", JSON.stringify(requestImages)); // 輸出 JSON 格式資料

  fetch(`/chill-tribe/supplier/applyAct/images/${activityId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
    images: window.base64Images
  }),
  })
  .catch(error => {
    console.error("Image upload failed:", error);
  })
});
