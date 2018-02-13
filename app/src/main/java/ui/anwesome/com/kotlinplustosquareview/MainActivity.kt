package ui.anwesome.com.kotlinplustosquareview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import ui.anwesome.com.plustosquareview.PlusToSquareView
import kotlin.jvm.internal.FunctionReference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = PlusToSquareView.create(this)
        view.addPlusToSquareListener({
            Toast.makeText(this, "square created", Toast.LENGTH_SHORT).show()
        },{
            Toast.makeText(this, "plus created", Toast.LENGTH_SHORT).show()
        })
        fullScreen()
    }
}
fun MainActivity.fullScreen() {
    supportActionBar?.hide()
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}