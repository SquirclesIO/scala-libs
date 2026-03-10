package io.squircles.lib.postgres

case class DbConfig(port: Int,
                    host: String,
                    name: String,
                    login: String,
                    password: String,
                    connectionPoolSize: Int,
                    schema: Option[String] = None)
