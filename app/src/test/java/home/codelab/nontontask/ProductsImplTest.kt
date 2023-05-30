package home.codelab.nontontask

import home.codelab.nontontask.data.Datasource.testProducts
import org.junit.Test

import org.junit.Assert.*


class ProductsImplTest {
    @Test
    fun addProduct_uniqueProduct() {
        val productsImpl = ProductsImpl()
        assertEquals(true, productsImpl.run {
            addProduct(testProducts[0])
            addProduct(testProducts[1])
            addProduct(testProducts[2])
        })
    }

    @Test
    fun addProduct_equalProduct() {
        val productsImpl = ProductsImpl()
        assertEquals(false, productsImpl.run {
            addProduct(testProducts[0])
            addProduct(testProducts[0])
        })
    }

    @Test
    fun deleteProduct_existingProduct() {
        val productsImpl = ProductsImpl().apply { addProduct(testProducts[0]) }
        assertEquals(true, productsImpl.deleteProduct(testProducts[0]))
    }

    @Test
    fun deleteProduct_nonExistentProduct() {
        val productsImpl = ProductsImpl().apply { addProduct(testProducts[0]) }
        assertEquals(false, productsImpl.deleteProduct(testProducts[3]))
        println(productsImpl.toString())
    }

    @Test
    fun getName_existingId() {
        val productsImpl = ProductsImpl().apply { addProduct(testProducts[1]) }
        assertEquals(testProducts[1].name, productsImpl.getName(testProducts[1].id))
    }

    @Test
    fun getName_nonExistentId() {
        val productsImpl = ProductsImpl().apply { addProduct(testProducts[1]) }
        assertEquals("", productsImpl.getName(testProducts[4].id))
    }

    @Test
    fun findByName_existingName() {
        val productsImpl = ProductsImpl().apply {
            for (p in testProducts)
                addProduct(p)
        }
        assertEquals(
            listOf(testProducts[1].id, testProducts[2].id),
            productsImpl.findByName(testProducts[1].name)
        )
    }
    @Test
    fun findByName_nonExistentName() {
        val productsImpl = ProductsImpl().apply {
            for (p in testProducts)
                addProduct(p)
        }
        assertEquals(
            listOf<Product>(),
            productsImpl.findByName("Some product name")
        )
    }
}