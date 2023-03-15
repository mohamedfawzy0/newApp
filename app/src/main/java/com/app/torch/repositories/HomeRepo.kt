package com.app.torch.repositories

import com.app.torch.models.response.*
import com.app.torch.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*
import javax.inject.Inject

interface HomeRepoAPIs {
    @GET(Constants.Endpoints.client + Constants.Endpoints.home + "/{type}")
    suspend fun getHome(@Path("type") type: String): Response<HomeData>

    @GET(Constants.Endpoints.client + Constants.Endpoints.products + "/" + Constants.Endpoints.exclusive)
    suspend fun getProductsExclusive(): Response<HomeProducts>

    @GET(Constants.Endpoints.client + Constants.Endpoints.products + "/" + Constants.Endpoints.chosen)
    suspend fun getProductsChosen(): Response<HomeProducts>

    @GET(Constants.Endpoints.client + Constants.Endpoints.sections + "/{type}")
    suspend fun getSections(@Path("type") type: String): Response<HomeSections>
}

interface HomeRepoInterface {
    suspend fun getHome(type: String): Response<HomeData>

    suspend fun getSections(type: String): Response<HomeSections>

    suspend fun getProductsExclusive(): Response<HomeProducts>

    suspend fun getProductsChosen(): Response<HomeProducts>
}

class HomeRepo @Inject constructor(retrofit: Retrofit) : HomeRepoInterface {
    private val api = retrofit.create(HomeRepoAPIs::class.java)

    override suspend fun getHome(type: String): Response<HomeData> {
        return api.getHome(type)
    }

    override suspend fun getSections(type: String): Response<HomeSections> {
        return api.getSections(type)
    }

    override suspend fun getProductsChosen(): Response<HomeProducts> {
        return api.getProductsChosen()
    }

    override suspend fun getProductsExclusive(): Response<HomeProducts> {
        return api.getProductsExclusive()
    }
}