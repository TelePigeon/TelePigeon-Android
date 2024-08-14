package com.dongguk.telepigeon.domain.model

data class RoomKeywordsExtraModel(
    val keywords: List<String>,
    val gender: String,
    val ageRange: String,
    val relation: String,
    val easyMode: Boolean,
)
