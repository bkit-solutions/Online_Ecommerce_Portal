// cart.js
document.addEventListener("DOMContentLoaded", () => {
  fetch("/api/cart")
    .then(res => res.json())
    .then(data => {
      const cart = document.getElementById("cart");
      let total = 0;
      data.forEach(item => {
        total += item.product.price * item.quantity;
        cart.innerHTML += `<p>${item.product.name} - ${item.quantity} pcs - $${item.product.price}</p>`;
      });
      cart.innerHTML += `<p><strong>Total: $${total}</strong></p>`;
    });
});

function checkout() {
  window.location.href = "checkout.html";
}
