package ui.anwesome.com.plustosquareview

/**
 * Created by anweshmishra on 14/02/18.
 */

import android.content.*
import android.graphics.*
import android.view.*

class PlusToSquareView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class Animator(var view:PlusToSquareView, var animated:Boolean = false) {
        fun animate(updatecb:() -> Unit) {
            if(animated) {
                updatecb()
                try {
                   Thread.sleep(50)
                   view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
}