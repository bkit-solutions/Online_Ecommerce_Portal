// product.js
document.addEventListener("DOMContentLoaded", () => {
  fetch("/api/products")
    .then(res => res.json())
    .then(products => {
      const container = document.getElementById("products");
      products.forEach(p => {
        const html = `
          <div class="product">
            <h3>${p.name}</h3>
            <p>${p.description}</p>
            <p><strong>$${p.price}</strong></p>
            <button onclick="addToCart(${p.id})">Add to Cart</button>
          </div>`;
        container.innerHTML += html;
      });
    });
});

function addToCart(productId) {
  const quantity = 1;
  fetch("/api/cart/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ productId, quantity })
  })
    .then(res => res.text())
    .then(alert);
}
