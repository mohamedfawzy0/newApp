package com.app.torch.base

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProvider
import com.app.torch.R
import com.app.torch.app.CustomApplication
import com.app.torch.di.ApplicationComponent
import com.app.torch.utils.builders.LoadingDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

open class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {
    val component: ApplicationComponent
        get() = (activity?.application as CustomApplication).component

    val loadingDialog by lazy {
        val dialog = LoadingDialog(requireContext()).build()
        dialog
    }

    fun onUnAuthorized() {
        if (activity is OnLogoutCallback) {
            (activity as OnLogoutCallback).onLogoutCallback()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }
}