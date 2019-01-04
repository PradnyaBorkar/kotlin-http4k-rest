package com.example

import com.example.config.Bootstrap
import com.example.config.Settings
import com.example.handler.ProductHandler
import com.example.service.ProductService
import org.http4k.server.Http4kServer
import org.slf4j.LoggerFactory

object Application {

    val log = LoggerFactory.getLogger("main")

    //val a = Database.connect(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD)

    fun create(): Http4kServer {
        val bootstrap = Bootstrap()
        return bootstrap.createHttpServer(
                ProductHandler(ProductService()).routes(),
                bootstrap.configuration[Settings.SERVER_PORT]
        )
    }

    @JvmStatic
    fun main(args: Array<String>) {

        create().start()

        log.info("Server ready at port $Settings.SERVER_PORT")
    }
}



