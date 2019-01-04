package com.example.handler

import com.example.service.ProductService
import org.http4k.core.*
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

class ProductHandler(val productService : ProductService) {

     fun routes(): RoutingHttpHandler {

        return org.http4k.routing.routes(

               "/search" bind Method.GET to { request: Request ->
                    Response(Status.OK) .with(productService.data of productService.processProducts())
                }
        )
    }

}