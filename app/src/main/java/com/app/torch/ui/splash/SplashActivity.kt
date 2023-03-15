package com.app.torch.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.app.torch.R
import com.app.torch.base.BaseActivity
import com.app.torch.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // slide-up animation
        val slideUp: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        if (logo.visibility === View.INVISIBLE) {
            logo.visibility = View.VISIBLE
            logo.startAnimation(slideUp)
        }

        val delayTime = 3000L // 3 seconds and time animation
        CoroutineScope(Dispatchers.IO).launch {
            delay(delayTime)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}
