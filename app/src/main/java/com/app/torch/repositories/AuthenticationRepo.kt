package com.app.torch.repositories

import retrofit2.Retrofit
import javax.inject.Inject

interface AuthenticationApis {

}

interface AuthenticationRepoInterface {

}

class AuthenticationRepo @Inject constructor(retrofit: Retrofit) : AuthenticationRepoInterface {

}