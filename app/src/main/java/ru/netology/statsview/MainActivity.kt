package ru.netology.statsview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation

import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import ru.netology.statsview.ui.StatsView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<StatsView>(R.id.stats)
        view.postDelayed( {
            view.data = listOf(
            500F,
            500F,
            500F,
            0F,
//            0F,
//            0.25F,
//            0.25F,
//            0.25F,
            )
        }, 1000
        )
//        val rotation = ObjectAnimator.ofFloat(view, View.ROTATION, 0F, 360F).apply {
//            duration = 2000
//            interpolator = LinearInterpolator()
//        }
//        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0F, 1F)
//        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0F, 1F)
//        val scale = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
//            duration = 2000
//            interpolator = BounceInterpolator()
//        }
//        AnimatorSet().apply {
//            //startDelay = 500
//            playTogether(scale, rotation)
//
//        }.start()







//        val text = findViewById<TextView>(R.id.textView)
        //view.setOnClickListener {
//            view.startAnimation(
//                AnimationUtils.loadAnimation(this, R.anim.animation).apply {
//                    setAnimationListener(object : Animation.AnimationListener {
//                        override fun onAnimationStart(p0: Animation?) {
//                            text.text = getString(R.string.onanimationstart)
//                        }
//
//                        override fun onAnimationEnd(p0: Animation?) {
//                            text.text = getString(R.string.onanimationend)
//                        }
//
//                        override fun onAnimationRepeat(p0: Animation?) {
//                            text.text = getString(R.string.onanimationrepeat)
//                        }
//
//                    })
//                }
//            )
        //}



    }
}