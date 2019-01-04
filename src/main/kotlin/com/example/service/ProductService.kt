package com.example.service

import com.example.model.Product
import org.http4k.core.*
import org.http4k.format.Jackson.auto

class ProductService{

    val data = Body.auto<List<Any>>().toLens()

    fun processProducts(): List<Product> {
        return listOf(Product(1,"table",2008.00 ),
                Product(2,"chair",5408.00 ),
                        Product(3,"box",108.00 ))
    }

}
