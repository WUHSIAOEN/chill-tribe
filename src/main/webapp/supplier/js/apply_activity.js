document.getElementById("submitBtn").addEventListener("click", function (event) {
  event.preventDefault(); // 防止表單默認提交行為

  const supplierId = parseInt(document.getElementById("supplierId")?.value) || 1;
  const activityName = document.getElementById("activityName")?.value || "";
  const city_id = parseInt(document.getElementById("city_id")?.value) || 1;
  const district_id = parseInt(document.getElementById("area")?.value) || 3;
  const address = document.getElementById("address")?.value || "";
  const category = document.getElementById("category")?.value || "";
  const unitPrice = parseFloat(document.getElementById("unitPrice")?.value) || 0;
  const minParticipants = parseInt(document.getElementById("minParticipants")?.value) || 4;
  const maxParticipants = parseInt(document.getElementById("maxParticipants")?.value) || 10;
  const inventoryCount = parseInt(document.getElementById("inventoryCount")?.value) || 10;
  const description = document.getElementById("description")?.value || "";
  const precaution = document.getElementById("precaution")?.value || "";
  const selectedRange = document.getElementById("reservationtime")?.value || "";
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
  const startDateTime = formatDateTime(startRaw);
  const endDateTime = formatDateTime(endRaw);

  const requestData = {
    supplierId,
    activityName,
    city_id,
    district_id,
    address,
    startDateTime,
    endDateTime,
    unitPrice,
    minParticipants,
    maxParticipants,
    inventoryCount,
    description,
    category,
    precaution,
    // not required
  };

  const requestImages = {
    images
  }

  console.log("Sending JSON:", JSON.stringify(requestData, requestImages)); // 輸出 JSON 格式資料

    fetch("/chill-tribe/activity/apply", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    }).catch(error => {
      console.error("Activity created failed:", error)}),

	console.log('................', Array.isArray(window.base64Images));
	console.log('----------------------', window.base64Images);
    fetch("/chill-tribe/activity/applyimages", {
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
