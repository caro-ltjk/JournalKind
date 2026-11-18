package com.example.journalkind

import android.content.Context
import android.util.Patterns
import java.security.MessageDigest
import java.util.UUID

sealed class AuthResult {
    object Success : AuthResult()
    data class Error(val message: String) : AuthResult()
}

class AuthService(context: Context) {
    private val storage = LocalStorageService(context)

    fun register(name: String, email: String, password: String): AuthResult {
        val mail = email.trim().lowercase()
        if (name.isBlank()) return AuthResult.Error("Escribe tu nombre")
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) return AuthResult.Error("Correo no válido")
        if (password.length < 8) return AuthResult.Error("La contraseña debe tener al menos 8 caracteres")
        if (storage.getUser(mail) != null) return AuthResult.Error("Ese correo ya está registrado")

        val salt = UUID.randomUUID().toString()
        storage.saveUser(mail, "$salt|${hash(password, salt)}|${name.trim()}")
        storage.saveEmail(mail)
        storage.saveSession(UUID.randomUUID().toString(), name.trim())

        return AuthResult.Success
    }

    fun login(email: String, password: String): AuthResult {
        val mail = email.trim().lowercase()
        val record = storage.getUser(mail) ?: return AuthResult.Error("No existe una cuenta con ese correo")
        val (salt, savedHash, name) = record.split("|", limit = 3)
        if (hash(password, salt) != savedHash) return AuthResult.Error("Contraseña incorrecta")

        storage.saveEmail(mail)
        storage.saveSession(UUID.randomUUID().toString(), name)
        return AuthResult.Success
    }

    fun logout() = storage.clearSession()

    private fun hash(password: String, salt: String): String =
        MessageDigest.getInstance("SHA-256")
            .digest((salt + password).toByteArray())
            .joinToString("") { "%02x".format(it) }
}