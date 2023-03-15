package com.app.torch.ui.register

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.app.torch.repositories.AuthenticationRepoInterface
import com.app.torch.utils.managers.ApiRequestManagerInterface
import com.app.torch.utils.managers.InternetConnectionManagerInterface
import com.app.torch.utils.managers.Validator
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val internetConnectionManager: InternetConnectionManagerInterface,
    private val apiRequestManager: ApiRequestManagerInterface,
    private val authenticationRepo: AuthenticationRepoInterface,
    private val validator: Validator,
    private val resources: Resources
) : ViewModel() {

}