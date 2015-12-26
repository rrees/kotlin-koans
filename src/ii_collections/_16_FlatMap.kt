package ii_collections

import ii_collections.data.orderedProducts

fun example() {

    val result = listOf("abc", "12").flatMap { it.toCharList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

val Customer.orderedProducts: Set<Product> get() {
    // Return all products ordered by customer
    return orders.flatMap({o -> o.products}).toSet()
}

val Shop.allOrderedProducts: Set<Product> get() {
    // Return all products that were ordered by at least one customer
    return customers.flatMap({ c -> c.orderedProducts }).toSet()
}
