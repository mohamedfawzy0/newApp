package com.app.torch.utils.callbacks

interface OnUpdateDrawerNavigationCallback {
    fun onUpdateDrawerNavigationCallback(ordersCount: Int, cartTotal: Int, reservationsCount: Int)
}