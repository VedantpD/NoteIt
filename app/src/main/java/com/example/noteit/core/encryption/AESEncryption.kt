package com.example.noteit.core.encryption

import android.os.Build
import androidx.annotation.RequiresApi
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security
import java.util.Base64

class AESEncryption {

    init {
        // Add Bouncy Castle as a security provider
        Security.addProvider(BouncyCastleProvider())
    }

    fun generateSecretKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance("AES", "BC")
        keyGenerator.init(256)
        return keyGenerator.generateKey()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(text: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(text.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(encryptedText: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText))
        return String(decryptedBytes)
    }
}
