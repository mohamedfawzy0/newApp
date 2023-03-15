package com.app.torch.utils.extensions.arraylist

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun <T> ArrayList<T>.toStringTypedArray(): Array<String> {
    return this.map { it.toString() }.toTypedArray()
}