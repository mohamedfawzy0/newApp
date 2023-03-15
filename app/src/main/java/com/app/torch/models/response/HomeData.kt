package com.app.torch.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class HomeData(
    val `data`: Home = Home(),
    val status: String = "",
    val message: String = ""
)

data class Home(
    val offers: List<Offer> = ArrayList(),
    val orders_count: Int = 0,
    val reservations_count: Int = 0,
    val bag_total: Int = 0,
    val sliders: List<String> = ArrayList(),
    var chooses: List<Product> = ArrayList(),
    val providers: List<Store> = ArrayList()
)

data class Offer(
    val image: String = "",
    val name: String = "",
    val details: String = "",
    var final_price: Int = 0,
    var original_price: Int = 0,
    val provider_id: Int = 0,
    val id: Int = 0
)

@Parcelize
data class Store(
    val image: String = "",
    val full_name: String = "",
    val id: Int = 0
) : Parcelable

data class HomeViewItemRepresentable(
    val model: Home
) {
    val offers = model.offers
    val ordersCount = model.orders_count
    val reservationsCount = model.reservations_count
    val bagTotal = model.bag_total
    val sliders = model.sliders
}


data class Product(
    var original_price: Int = 0,
    var image: String = "",
    var images: List<String> = mutableListOf(),
    var final_price: Int = 0,
    var is_offer: Int = 0,
    var rates: Double = 0.0,
    var name: String = "",
    var is_fav: Int = 0,
    var is_in_bag: Int = 0,
    var description: String = "",
    var id: Int = 0,
    var is_exclusive: Int = 0
)

interface AddToCartInterface {
    fun addToCart(index: Int, productId: Int)
    fun openCart()
}

interface FavoriteProductInterface {
    fun toggleFavProduct(index: Int, productId: Int)
}

//class ProductViewItemRepresentable(
//    var model: Product, resources: Resources?,
//    var `interface`: FavoriteProductInterface?,
//    var cartInterface: AddToCartInterface?,
//    var forHome: Boolean = false
//) : ViewItemRepresentable {
//    override val viewItem: AbstractViewItem<ViewItemRepresentable>
//        get() = if (!forHome) ProductViewItem(this) else ProductHomeViewItem(this)
//    var id = model.id
//    var name = model.name
//    var description = model.description
//    var finalPrice = model.final_price
//    var original_price = model.original_price
//    var image = model.image
//    var images = model.images
//    var isFavorite = model.is_fav == 1
//    var isInBag = model.is_in_bag == 1
//    var rates = model.rates
//    var is_offer = model.is_offer
//    var is_exclusivee = model.is_exclusive == 1
//
//    fun toggleFavoriteProduct(atIndex: Int) {
//        `interface`?.toggleFavProduct(atIndex, id)
//    }
//
//    fun addProductToBag(index: Int) {
//        cartInterface?.addToCart(index, id)
//    }
//
//    fun openCart() {
//        cartInterface?.openCart()
//    }
//}