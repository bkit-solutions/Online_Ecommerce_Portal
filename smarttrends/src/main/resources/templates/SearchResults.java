public class searchResults {
}

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 1 
<title>Search Results | FlipKart</title>
<style>
body { font-family: sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
h1, h2 { color: #007bff; }
.product-list { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin-top: 20px; }
.product-card { background-color: white; border: 1px solid #ddd; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); 2  }
.product-card h3 { margin-top: 0; }
.no-results { text-align: center; font-style: italic; color: #777; }
</style>
</head>
<body>
<header>
<h1>SmartKart</h1>
<div class="search-container">
<input type="text" id="searchInput" th:value="${searchQuery}" placeholder="Search products...">
<button onclick="searchProducts()">Search</button>
</div>
<nav>
<a href="/home">Home</a>
</nav>
</header>  

<main>
<h2>Search Results for: <span th:text="${searchQuery}"></span></h2>

<div class="product-list" th:if="${not products.empty}">
    <div class="product-card" th:each="product : ${products}">
        <h3 th:text="${product.name}">Product Name</h3>
        <p th:text="${product.description}">Product Description</p>
        <p>Price: $<span th:text="${product.price}"></span></p>
        </div>
</div>
<p class="no-results" th:if="${products.empty}">No products found matching your search criteria.</p>
</main>

<script>
function searchProducts() {
    const searchTerm = document.getElementById('searchInput').value;
    if (searchTerm.trim() !== "") {
        window.location.href = '/search?query=' + encodeURIComponent(searchTerm);
    }
}
</script>
</body>
</html>
```

//
//* **Explanation:**
//* This is a Thymeleaf template (`.html` file located in `src/main/resources/templates`).
//* `xmlns:th="http://www.thymeleaf.org"` enables Thymeleaf features.
//* We display the `searchQuery` using `th:text="${searchQuery}"`.
//* We use `th:if="${not products.empty}"` to check if the `products` list (passed from the controller) is not empty. If it's not, we iterate through the list using `th:each="product : ${products}"` and display the product details using `th:text`.
//* If the `products` list is empty, we display a "No products found" message using `th:if="${products.empty}"`.
//* We've also included the search bar on the results page so users can perform another search.
//
//
