package home.codelab.nontontask

data class Product(val id: String, val name: String)

class ProductsImpl {
    private val products: MutableSet<Product> = mutableSetOf()
    val addProduct: (Product) -> Boolean = { products.add(it) }
    val deleteProduct: (Product) -> Boolean = { products.remove(it) }
    val getName: (String) -> String = { id ->
        products.find() { it.id == id }?.name ?: ""
    }
    val findByName: (String) -> List<String> =
        { name -> products.groupBy { it.name == name }[true]?.map { it.id } ?: listOf() }
}