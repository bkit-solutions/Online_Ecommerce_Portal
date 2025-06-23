// checkout.js
function placeOrder() {
  fetch("/api/orders", { method: "POST" })
    .then(res => res.text())
    .then(msg => {
      alert(msg);
      window.location.href = "order-history.html";
    });
}
