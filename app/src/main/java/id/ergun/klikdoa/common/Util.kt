package id.ergun.klikdoa.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.17
 */
object Util {

    const val FAVORITE_DOA_TABLE_NAME = "doa_favorite_table"

    private const val LINKED_IN_URL = "https://www.linkedin.com/in/ergun4/"
    private const val GITHUB_URL = "https://github.com/erikgunawan"
    private const val INSTAGRAM_URL = "https://www.instagram.com/_erikgunawan_/"
    private const val TELEGRAM_URL = "https://t.me/erikgunawan"

    fun Context.openLinkedIn() {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(LINKED_IN_URL)
                setPackage("com.linkedin.android")
            }
            startActivity(intent)
        } catch (exception: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(LINKED_IN_URL)
                )
            )
        }
    }

    fun Context.openGithub() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(GITHUB_URL)
            )
        )
    }

    fun Context.openInstagram() {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(INSTAGRAM_URL)
                setPackage("com.instagram.android")
            }
            startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(INSTAGRAM_URL)
                )
            )
        }
    }

    fun Context.openTelegram() {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(TELEGRAM_URL)
                setPackage("org.telegram.messenger")
            }
            startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(TELEGRAM_URL)
                )
            )
        }
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}