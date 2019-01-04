package com.example.config

import io.github.konfigur8.ConfigurationTemplate
import io.github.konfigur8.Property


object Settings {

 val SERVER_PORT= Property.int("SERVER_PORT")
 val CLIENT_URL = Property.string("CLIENT_URL")
 val TIME_TO_LIVE= Property.long("TIME_TO_LIVE")
 val TIMEOUT = Property.long("TIMEOUT")
 val DB_URL = Property.long("TIMEOUT")
 val DB_USER = Property.long("TIMEOUT")
 val DB_PASSWORD = Property.long("TIMEOUT")


 val default = ConfigurationTemplate()
  .withProp(SERVER_PORT,8080)
  .withProp(CLIENT_URL,"")
  .withProp(TIME_TO_LIVE,30)
  .withProp(TIMEOUT,15)
  .reify()

}
