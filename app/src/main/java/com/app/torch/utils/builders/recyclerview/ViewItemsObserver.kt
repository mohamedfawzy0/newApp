package com.app.torch.utils.builders.recyclerview


class ViewItemsObserver(
    var viewItemsArrayList: ViewItemArrayList = arrayListOf(),
    var clearsOnSet: Boolean = false,
    var appendToEnd: Boolean = true
)