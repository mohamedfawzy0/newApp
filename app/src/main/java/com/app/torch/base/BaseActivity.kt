package com.app.torch.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.torch.R
import com.app.torch.app.CustomApplication
import com.app.torch.di.ApplicationComponent
import com.app.torch.ui.splash.SplashActivity
import com.app.torch.utils.Constants
import com.app.torch.utils.LocaleHelper
import com.app.torch.utils.builders.LoadingDialog
import com.app.torch.utils.enums.UserSavedDataKeys
import com.app.torch.utils.extensions.toast.toast
import com.app.torch.utils.managers.SharedPreferencesManagerInterface
import kotlinx.android.synthetic.main.app_bar_layout.*
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), OnLogoutCallback {
    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManagerInterface

    val component: ApplicationComponent
        get() = (application as CustomApplication).component


    val loadingDialog by lazy {
        val dialog = LoadingDialog(this).build()
        dialog
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appResources: Resources

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    fun setupToolBar(activityTitle: String, showBackButton: Boolean = false) {
        setSupportActionBar(toolBar)
        if (showBackButton) {
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        supportActionBar?.title = ""
        titleTextView.text = activityTitle
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun onUnAuthorized() {
        onLogoutCallback()
    }

    fun logout() {
        sharedPreferencesManager.clear()
    }

    override fun onLogoutCallback(byClickLogout: Boolean) {
        if (sharedPreferencesManager.get(UserSavedDataKeys.TOKEN.key, "").isNotEmpty()) {
            if (!byClickLogout) {
                toast(R.string.str_session_expire)
            } else {
                toast(R.string.str_logout_message)
            }
            logout()
            startActivity(Intent(this, SplashActivity::class.java))
            sharedPreferencesManager.put(Constants.Keys.KEY_GUEST_USER, false)
            Log.i("onLogoutCallback", this.javaClass.simpleName)
            finishAffinity()
        }
    }
}

interface OnLogoutCallback {
    fun onLogoutCallback(byClickLogout: Boolean = false)
}