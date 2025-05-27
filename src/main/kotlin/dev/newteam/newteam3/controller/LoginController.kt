package dev.newteam.newteam3.controller

import org.mindrot.jbcrypt.BCrypt

class LoginController {
    fun login(username: String, password: String): Boolean {
        val correctUsername = "admin"
        val hashedPassword = "\$2a\$12\$nb2drgFJPylkW9PfeQ01oeI0SFEH/XdBKMhJNpmLpACMqeTvVLUz2"

        // user:      admin
        // password:  contra123

        return username == correctUsername && BCrypt.checkpw(password, hashedPassword)
    }
}