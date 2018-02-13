package ui.anwesome.com.plustosquareview

/**
 * Created by anweshmishra on 14/02/18.
 */

import android.content.*
import android.graphics.*
import android.view.*

class PlusToSquareView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val renderer = Renderer(this)
    override fun onDraw(canvas:Canvas) {
        renderer.render(canvas, paint)
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                renderer.handleTap()
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
    data class PlusToSquare(var x:Float, var y:Float, var size:Float) {
        val state = State()
        fun draw(canvas:Canvas, paint:Paint) {
            paint.strokeWidth = size/20
            paint.strokeCap = Paint.Cap.ROUND
            paint.color = Color.parseColor("#d32f2f")
            canvas.save()
            canvas.translate(x,y)
            for(i in 0..1) {
                canvas.save()
                canvas.rotate(90f * i)
                for(j in 0..1) {
                    val y_gap = (size/2) * state.scale
                    canvas.save()
                    canvas.drawLine(-size/2, y_gap * (j*2 - 1), size/2, y_gap * (j*2 - 1), paint)
                    canvas.restore()
                }
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb: (Float) -> Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb : () -> Unit) {
            state.startUpdating(startcb)
        }
    }
    data class Renderer(var view:PlusToSquareView, var time:Int = 0) {
        val animator = Animator(view)
        var plusToSquare:PlusToSquare ?= null
        fun render(canvas:Canvas, paint:Paint) {
            if(time == 0) {
                val w = canvas.width.toFloat()
                val h = canvas.height.toFloat()
                plusToSquare = PlusToSquare(w/2,h/2,Math.min(w,h)/4)
            }
            canvas.drawColor(Color.parseColor("#212121"))
            plusToSquare?.draw(canvas, paint)
            time++
            animator.animate {
                plusToSquare?.update {
                    animator.stop()
                }
            }
        }
        fun handleTap() {
            plusToSquare?.startUpdating {
                animator.start()
            }
        }
    }
}