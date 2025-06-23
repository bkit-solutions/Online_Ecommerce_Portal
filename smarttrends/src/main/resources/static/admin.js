// admin.js
document.getElementById('addProductForm').addEventListener('submit', function (e) {
  e.preventDefault();

  const product = {
    name: document.getElementById("name").value,
    description: document.getElementById("description").value,
    price: parseFloat(document.getElementById("price").value),
    category: document.getElementById("category").value,
    imageUrl: document.getElementById("imageUrl").value
  };

  fetch("/api/products", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(product)
  })
    .then(res => res.text())
    .then(alert);
});
