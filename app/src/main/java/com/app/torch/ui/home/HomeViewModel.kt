package com.app.torch.ui.home

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.app.torch.models.response.AddToCartInterface
import com.app.torch.models.response.FavoriteProductInterface
import com.app.torch.repositories.HomeRepoInterface
import com.app.torch.utils.managers.ApiRequestManagerInterface
import com.app.torch.utils.managers.InternetConnectionManagerInterface
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val internetConnectionManager: InternetConnectionManagerInterface,
    private val homeRepo: HomeRepoInterface,
    private val apiRequestManager: ApiRequestManagerInterface,
    private val resources: Resources
) : ViewModel(), FavoriteProductInterface, AddToCartInterface {
    override fun addToCart(index: Int, productId: Int) {
    }

    override fun openCart() {
    }

    override fun toggleFavProduct(index: Int, productId: Int) {
    }


}