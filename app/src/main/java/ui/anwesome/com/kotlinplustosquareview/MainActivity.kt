package ui.anwesome.com.kotlinplustosquareview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.plustosquareview.PlusToSquareView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlusToSquareView.create(this)
    }
}
