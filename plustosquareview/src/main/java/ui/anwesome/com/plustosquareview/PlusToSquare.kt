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
    data class State(var scale:Float = 0f, var dir:Float = 0f, var prevScale:Float = 0f) {
        fun update(stopcb: (Float) -> Unit) {
            scale += 0.1f * dir
            if(Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                stopcb(scale)
            }
        }
        fun startUpdating(startcb: () -> Unit) {
            if(dir == 0f) {
                dir = 1f - 2*scale
                startcb()
            }
        }
    }
}