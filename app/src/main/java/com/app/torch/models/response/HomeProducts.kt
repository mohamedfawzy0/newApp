package com.app.torch.models.response

data class HomeProducts(
    val products: List<ProductModel> = ArrayList(),
    val status: String = "",
    val message: String = ""
)

data class ProductModel(
    val image: String = "",
    val name: String = "",
    val id: Int = 0
)

