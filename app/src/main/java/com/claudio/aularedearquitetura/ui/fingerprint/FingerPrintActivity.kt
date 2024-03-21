package com.claudio.aularedearquitetura.ui.fingerprint

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.claudio.aularedearquitetura.R
import com.claudio.aularedearquitetura.ui.movie.MovieListActivity
import java.util.concurrent.Executor

class FingerPrintActivity : AppCompatActivity() {
    private lateinit var biometricPrompt: BiometricPrompt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger_print)

        checkBiometricSupport()
    }

    fun checkBiometricSupport() {
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
//            showToast("Dispositivo com suporte a biometria!")
            when(BiometricManager.from(this).canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
//                    showToast("Biometria cadastrada")
                    authenticateUser()
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    showToast("Dispositivo sem suporte a biometria!")
                }
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    showToast("O hardware de biometria não está disponível no momento.")
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    showToast("Dispositivo sem biometria cadastrada!")
                }
            }
        } else {
            showToast("Dispositivo sem suporte a biometria!")
        }
    }

    fun authenticateUser() {
        val executor: Executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(
            this,
            executor,
            object: BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    showToast("Erro na autenticacao: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    navigateToHome()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    showToast("Falha na autenticação. Tente novamente.")
                }

            }
        )
        val promptDialog = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticação por biometria")
            .setSubtitle("Toque no sensor de biometria para autenticar ou olhe para a tela")
            .setNegativeButtonText("Cancelar")
            .build()
        biometricPrompt.authenticate(promptDialog)
    }

    fun navigateToHome() {
        val intent = Intent(this, MovieListActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}