package com.han.fragmentmanagement.Utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import java.security.MessageDigest


public class ETCUtil {
    companion object{
        fun goPlaystore(c:Context,appPackageName:String){
            try {
                c.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: ActivityNotFoundException) {
               c.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
        fun getApplicationSignature(context:Context): List<String> {
            var packageName: String = context.packageName
            val signatureList: List<String>
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    // New signature
                    val sig = context.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES).signingInfo
                    signatureList = if (sig.hasMultipleSigners()) {
                        // Send all with apkContentsSigners
                        sig.apkContentsSigners.map {
                            val digest = MessageDigest.getInstance("SHA")
                            digest.update(it.toByteArray())
                            bytesToHex(digest.digest())
                        }
                    } else {
                        // Send one with signingCertificateHistory
                        sig.signingCertificateHistory.map {
                            val digest = MessageDigest.getInstance("SHA")
                            digest.update(it.toByteArray())
                            bytesToHex(digest.digest())
                        }
                    }
                }

                else {
                    val sig = context.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures
                    signatureList = sig.map {
                        val digest = MessageDigest.getInstance("SHA")
                        digest.update(it.toByteArray())
                        bytesToHex(digest.digest())
                    }
                }

                return signatureList
            } catch (e: Exception) {
                // Handle error
            }
            return emptyList()
        }

        fun bytesToHex(bytes: ByteArray): String {
            val hexArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
            val hexChars = CharArray(bytes.size * 2)
            var v: Int
            for (j in bytes.indices) {
                v = bytes[j].toInt() and 0xFF
                hexChars[j * 2] = hexArray[v.ushr(4)]
                hexChars[j * 2 + 1] = hexArray[v and 0x0F]
            }
            return String(hexChars)
        }


    }
}