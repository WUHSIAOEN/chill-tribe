function getSupplierIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("id");
};

const supplier_id = getSupplierIdFromURL();

fetch(`${APP_CONFIG.BASE_URL}supplier/supplierfind/${supplier_id}`)
  .then(response => response.json())
  .then(data => {
    console.log("從後端獲取的數據:", data);

    document.getElementById("supplier_name_1").innerHTML = data.supplier_name || "暫無";
    document.getElementById("supplier_name_2").innerHTML = data.supplier_name || "暫無";
    document.getElementById("about").innerHTML = data.about || "暫無";
    document.getElementById("email").innerHTML = data.email || "暫無";
    document.getElementById("phone").innerHTML = data.phone || "暫無";
    document.getElementById("address").innerHTML = data.address || "暫無";

    // 中間主要圖片
    let mainImgElement = document.getElementById("supplierMainImage");
    mainImgElement.innerHTML = ""; // 清空原內容

    let supplierImage1 = data?.image || "./asset/no-image.jpg";

    let imgElement = document.createElement("img");
    imgElement.src = supplierImage1;
    imgElement.alt = "Supplier Image";

    mainImgElement.appendChild(imgElement);
    
    // 左邊 sidebar
    let sBody = document.getElementById('supplier-body');
    sBody.innerHTML = ""; // 清空目前的內容

    let supplierName = data.supplier_name;
    let supplierPhone = data.phone;
    let supplierImage = data?.image || "./asset/no-image.jpg";

    let supplierElement = document.createElement("div");
    supplierElement.classList.add("sides-widget-header", "bg-primary");

    supplierElement.innerHTML = `
      <div class="agent-photo">
        <img src="${supplierImage}" alt="Supplier Image">
      </div>
      <div class="sides-widget-details">
        <h4><a href="#">${supplierName}</a></h4>
        <span><i class="lni-phone-handset"></i>${supplierPhone}</span>
      </div>
      <div class="clearfix"></div>
    `;

    sBody.appendChild(supplierElement);
  })
  .catch(error => console.error("獲取數據時出錯:", error));
