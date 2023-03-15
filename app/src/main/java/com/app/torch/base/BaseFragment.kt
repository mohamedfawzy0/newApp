package com.app.torch.base

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.torch.app.CustomApplication
import com.app.torch.di.ApplicationComponent
import com.app.torch.utils.builders.LoadingDialog
import javax.inject.Inject

open class BaseFragment : Fragment() {
    val component: ApplicationComponent
        get() = (activity?.application as CustomApplication).component

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appResources: Resources

    val loadingDialog by lazy {
        val dialog = LoadingDialog(requireActivity()).build()
        dialog
    }

    fun onUnAuthorized() {
        if (activity is OnLogoutCallback) {
            (activity as OnLogoutCallback).onLogoutCallback()
        }
    }
}