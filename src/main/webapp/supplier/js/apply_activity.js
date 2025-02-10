document.getElementById("submitBtn").addEventListener("click", function (event) {
  event.preventDefault(); // 防止表單默認提交行為

  const supplierId = parseInt(document.getElementById("supplierId")?.value) || 1;
  const activityName = document.getElementById("activityName")?.value || "";
  const cityId = parseInt(document.getElementById("cityId")?.value) || 1;
  const districtId = parseInt(document.getElementById("districtId")?.value) || 3;
  const address = document.getElementById("address")?.value || "";
  const category = document.getElementById("category")?.value || "";
  const unitPrice = parseFloat(document.getElementById("unitPrice")?.value) || 0;
  const minParticipants = parseInt(document.getElementById("minParticipants")?.value) || 4;
  const maxParticipants = parseInt(document.getElementById("maxParticipants")?.value) || 10;
  const inventoryCount = parseInt(document.getElementById("inventoryCount")?.value) || 10;
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

  const requestData = {
    supplierId,
    activityName,
    cityId,
    districtId,
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

  console.log("Sending JSON:", JSON.stringify(requestData)); // 輸出 JSON 格式資料

  fetch("/chill-tribe/activity/apply", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(requestData),
	})
		.then((resp) => {
			if (!resp.ok) {
				throw new Error(`HTTP error! Status: ${resp.status}`);
			}
			return resp.text(); // 先讀取回應作為文字
		});
});
