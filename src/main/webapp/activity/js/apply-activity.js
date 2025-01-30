document.getElementById("submitBtn").addEventListener("click", function () {
  const activityName = document.getElementById("activityName")?.value || "";
  const city = document.getElementById("city")?.value || "";
  const address = document.getElementById("address")?.value || "";
  const category = document.getElementById("category")?.value || "";
  const unitPrice =
    parseFloat(document.getElementById("unitPrice")?.value) || 0;
  const minParticipants =
    parseInt(document.getElementById("minParticipants")?.value) || 0;
  const maxParticipants =
    parseInt(document.getElementById("maxParticipants")?.value) || 0;
  const description = document.getElementById("description")?.value || "";
  const note = document.getElementById("note")?.value || "";
  // const eventImages = document.getElementById('eventImages').value;

  fetch("apply", {
    // 確保你的後端有對應的 API 路由
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      activityName: activityName.value,
      city: city.value,
      address: address.value,
      category: category.value,
      unitPrice: parseFloat(unitPrice.value) || 0,
      minParticipants: parseInt(minParticipants.value) || 0,
      maxParticipants: parseInt(maxParticipants.value) || 0,
      description: description.value,
      note: note.value
    }),
  })
    .then((resp) => resp.json())
    .then((body) => {
      const { successful, message } = body;
      msg.style.color = successful ? "blue" : "red";
      msg.textContent = message;
    })
    .catch((error) => {
      console.error("Error:", error);
      msg.style.color = "red";
      msg.textContent = "提交失敗，請重試！";
    });
});
