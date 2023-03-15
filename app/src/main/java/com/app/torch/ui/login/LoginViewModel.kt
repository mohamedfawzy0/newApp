package com.app.torch.ui.login

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.torch.repositories.AuthenticationRepoInterface
import com.app.torch.utils.EditTextError
import com.app.torch.utils.managers.ApiRequestManagerInterface
import com.app.torch.utils.managers.InternetConnectionManagerInterface
import com.app.torch.utils.managers.SharedPreferencesManagerInterface
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val internetConnectionManager: InternetConnectionManagerInterface,
    private val apiRequestManager: ApiRequestManagerInterface,
    private val authenticationRepo: AuthenticationRepoInterface,
    private val sharedPreferencesManager: SharedPreferencesManagerInterface,
    private val resources: Resources
) : ViewModel() {

    val editTextError = MutableLiveData<EditTextError>()


    private fun observeEditText(byEditTextId: Int, byErrorMessageId: Int) {
        editTextError.value = EditTextError(byEditTextId, byErrorMessageId)
    }
}