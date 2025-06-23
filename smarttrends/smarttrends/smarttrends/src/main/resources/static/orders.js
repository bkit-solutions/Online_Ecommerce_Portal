// orders.js
document.addEventListener("DOMContentLoaded", () => {
  fetch("/api/orders")
    .then(res => res.json())
    .then(data => {
      const orders = document.getElementById("orders");
      data.forEach(order => {
        orders.innerHTML += `
          <div>
            <h3>Order #${order.id}</h3>
            <p>Date: ${new Date(order.date).toLocaleDateString()}</p>
            <p>Total: $${order.totalAmount}</p>
          </div>`;
      });
    });
});
