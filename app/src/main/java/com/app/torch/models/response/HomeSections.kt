package com.app.torch.models.response

data class HomeSections(
    val `data`: List<SectionModel> = ArrayList(),
    val status: String = "",
    val message: String = ""
)

data class SectionModel(
    val image: String = "",
    val title: String = "",
    val type: String = "",
    val id: Int = 0
)

