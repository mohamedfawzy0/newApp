package com.app.torch.utils

class Constants {
    class Keys {
        companion object {
            const val ORDER_STATUS_PENDING = "pending"
            const val ORDER_STATUS_CANCELED = "canceled"
            const val ORDER_STATUS_READY_TO_PAY = "ready_to_pay"
            const val ORDER_STATUS_CONFIRMED = "confirmed"
            const val ORDER_STATUS_PREPARATION = "preparation"
            const val ORDER_STATUS_RECEIVED = "received"

            const val RESERVATION_STATUS_NEW = "new"
            const val RESERVATION_STATUS_PENDING = "pending"
            const val RESERVATION_STATUS_CANCELED = "canceled"
            const val RESERVATION_STATUS_REJECTED = "rejected"
            const val RESERVATION_STATUS_CONFIRMED = "confirmed"
            const val RESERVATION_STATUS_COMPLETED = "completed"

            const val KEY_OBJECT = "default_key"
            const val KEY_MESSAGE = "message_key"
            const val KEY_TYPE = "type"
            const val KEY_NUM = "type"
            const val KEY_ORDERS = "orders"
            const val KEY_RESERVATIONS = "reservations"
            const val KEY_DATE = "date"
            const val KEY_STATUS = "status"
            const val KEY_PRICE = "price"
            const val KEY_MAIL = "mail"
            const val KEY_PHONE = "phone"
            const val KEY_NAME = "name"
            const val ORDER_NAME = "Order"
            const val RESERVATION_NAME = "Reservation"
            const val KEY_ID = "id"
            const val KEY_PRODUCT = "product"
            const val KEY_SERVICE = "service"
            const val KEY_IMAGE = "image"
            const val KEY_PENDING = "pending"
            const val KEY_ACCEPTED = "accepted"
            const val KEY_DONE = "done"
            const val KEY_TITLE = "title"
            const val KEY_GUEST_USER = "guest_user"
            const val KEY_URL = "url"
            const val KEY_PAYMENT = "payment"
            const val KEY_CODE = "qr_code"
            const val KEY_DETAILS = "details"

            const val HOME_SECTIONS_BRANDS = "brands"
            const val HOME_SECTIONS_PRODUCTS = "products"

            const val ACCOUNT_STATUS_DEACTIVATE = "deactive"
            const val ACCOUNT_STATUS_ACTIVATE = "active"

            const val ACCOUNT_TYPE_CLIENT = "client"
            const val ACCOUNT_TYPE_PROVIDER = "provider"

            const val PAYMENTS_FOR_WALLET = "User"
            const val PAYMENTS_FOR_RESERVATION = "Reservation"
            const val PAYMENTS_FOR_ORDER = "Order"

            const val POINTS_DEBIT_KEY = "debit"
            const val POINTS_CREDIT_KEY = "credit"

            const val POINTS_USED_KEY = "used"
            const val POINTS_EARNED_KEY = "earned"

            const val ACTIVATION_CODE_ACTIVE_KEY = "active"
            const val ACTIVATION_CODE_RESET_KEY = "reset"

            const val KEY_ACTIVATION_CODE_SENT_BY_MOBILE = "mobile"
            const val KEY_ACTIVATION_CODE_SENT_BY_EMAIL = "email"

            const val HOME_FOR_MEN = "men"
            const val HOME_FOR_WOMEN = "women"

            const val KEY_WORKS = "works"

            const val PROVIDERS = "providers"
            const val CATEGORIES = "categories"
            const val BRANDS = "brands"

            const val PRODUCTS_VIEW_TYPE_KEY = "products_view_type"
            const val PRODUCTS_VIEW_TYPE_SEARCH = 1
            const val PRODUCTS_VIEW_TYPE_BRANDS = 2
            const val PRODUCTS_VIEW_TYPE_CATEGORIES = 3
            const val PRODUCTS_VIEW_TYPE_FILTER = 4


            const val COMPLETED = "completed"
            const val UNCOMPLETED = "uncompleted"

        }
    }

    class Endpoints {
        companion object {
            //end points
            const val client = "client/"
            const val provider = "provider"

            const val all = "/all"

            const val getAll = "getAllObjects"

            const val login = "login"
            const val register = "register"
            const val countries = "country"
            const val category = "category"
            const val city = "city"

            const val askResetPassword = "auth/ask_reset"
            const val askActivate = "auth/ask_activate"
            const val verifyCode = "auth/verify_code"
            const val resetPassword = "auth/do_reset"
            const val changePassword = "change_password"
            const val resendCode = "auth/resend_code"

            const val main = "main/"
            const val images = "images"
            const val home = "home"
            const val chosen = "chosen"
            const val exclusive = "exclusive"
            const val brands = "brands"
            const val announcement = "announcement"
            const val sections = "sections"

            const val allOffers = "offers"
            const val allTutorials = "tutorial"
            const val providers = "providers"

            const val offer = "offer"
            const val works = "works"
            const val work = "/work/"
            const val tutorial = "/tutorial/"
            const val product = "product"
            const val categories = "categories"
            const val getCategories = "getCategories"
            const val getCategory = "getCategory"
            const val type = "type"
            const val times = "times"
            const val services = "services"
            const val tutorials = "tutorials"
            const val toggleFavProduct = "toggle_fav_product"
            const val toggleFavProvider = "toggle_fav_provider"
            const val favorite = "favorite/"
            const val products = "products"
            const val filter = "filter"

            const val add = "add/"
            const val bag = "bag"
            const val my = "my/"
            const val update = "update"
            const val delete = "delete"
            const val delivery = "delivery"
            const val cart = "cart/"
            const val addresses = "addresses"
            const val address = "address"
            const val pending = "pending"
            const val reservations = "reservations/"
            const val reservations2 = "reservations"
            const val reservation = "reservation"
            const val create = "create"
            const val contact = "contact"

            const val settings = "settings/"
            const val setting = "setting/"
            const val social = "social"
            const val about = "about"
            const val terms = "terms"
            const val policy = "policy"
            const val profile = "profile"
            const val toggleNotification = "toggle/notification"
            const val questions = "questions"

            const val orders = "orders/"
            const val orders2 = "orders"
            const val done = "done"
            const val order = "order"
            const val accept = "accept"
            const val service = "/service/"
            const val store = "store"
            const val notification = "notification"
            const val calender = "/calender/"
            const val count = "count"
            const val rate = "rate/"
            const val search = "search"

            const val wallet = "wallet/"
            const val history = "history/"

            const val points = "points/"

            const val payments = "payments/"
            const val paymentss = "payments"

            const val show_details="/show"
            const val show="show"
            const val obj="/object"

            const val apply="apply/"
            const val coupon="coupon"
            const val confirm="confirm"
            const val list="list"
            const val cancel="cancel"
        }
    }
}