package com.example.config


import io.github.konfigur8.Configuration
import org.apache.http.client.config.CookieSpecs
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.HttpClients
import org.eclipse.jetty.server.Server
import org.http4k.client.ApacheClient
import org.http4k.core.BodyMode
import org.http4k.core.HttpHandler
import org.http4k.server.Http4kServer
import org.http4k.server.Jetty
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS
import java.util.concurrent.TimeUnit

class Bootstrap{

    val configuration:Configuration
    private val timeToLive: Duration
    private val timeout :Duration

    constructor(configuration: Configuration = Settings.default){
        this.configuration = configuration
        this.timeToLive = Duration.of(configuration.get(Settings.TIME_TO_LIVE),SECONDS)
        this.timeout = Duration.of(configuration.get(Settings.TIMEOUT),SECONDS)
    }

    fun createHttpClient(bodyMode: BodyMode = BodyMode.Memory) : HttpHandler {
        return ApacheClient(HttpClients.custom()
            .setDefaultRequestConfig(RequestConfig.custom()
                .setConnectTimeout(timeout.toMillis().toInt())
                .setConnectionRequestTimeout(timeout.toMillis().toInt())
                .setRedirectsEnabled(false)
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .build())
            .setConnectionTimeToLive(timeToLive.toNanos(),TimeUnit.NANOSECONDS)
            .setMaxConnTotal(100)
            .evictExpiredConnections()
            .build(),bodyMode,bodyMode)

    }

    fun createHttpServer(handler: HttpHandler, port: Int): Http4kServer {
        val jetty = Server(port)
        return  Jetty(port, jetty).toServer(handler)
    }
}