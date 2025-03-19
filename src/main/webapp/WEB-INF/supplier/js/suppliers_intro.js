// 從 URL 取得 活動 ID
function getSupplierIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("supplierId");
};

const supplierId = getSupplierIdFromURL();

function fetchSupplierById(supplierId) {

};