document.getElementById("submitBtn").addEventListener("click", function (event) {
  event.preventDefault(); // 防止表單默認提交行為
  const activityName = document.getElementById("activityName")?.value || "";
  const city = document.getElementById("city")?.value || "";
  const address = document.getElementById("address")?.value || "";
  const category = document.getElementById("category")?.value || "";
  const unitPrice = parseFloat(document.getElementById("unitPrice")?.value) || 0;
  const minParticipants = parseInt(document.getElementById("minParticipants")?.value) || 0;
  const maxParticipants = parseInt(document.getElementById("maxParticipants")?.value) || 0;
  const description = document.getElementById("description")?.value || "";
  const note = document.getElementById("note")?.value || "";

  // 打印即將送出的 JSON 資料
  const requestData = {
    activityName,
    city,
    address,
    category,
    unitPrice,
    minParticipants,
    maxParticipants,
    description,
    note
  };
  
  console.log("Sending JSON:", JSON.stringify(requestData)); // 輸出 JSON 格式資料

  fetch("apply", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestData),
  })
    .then((resp) => resp.json())
    .then((body) => {
      const { successful, message } = body;
      msg.style.color = successful ? "blue" : "red";
      msg.textContent = message;
    })
    .catch((error) => {
      console.error("Error:", error);
    });
});