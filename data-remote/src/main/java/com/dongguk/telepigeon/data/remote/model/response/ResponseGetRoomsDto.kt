package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseGetRoomsDto(
    @SerialName("rooms")
    val rooms: List<ResponseGetRoomDto>,
) {
    @Serializable
    class ResponseGetRoomDto(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("opponentNickname")
        val opponentNickname: String,
        @SerialName("myRelation")
        val myRelation: String,
        @SerialName("opponentRelation")
        val opponentRelation: String,
        @SerialName("sentence")
        val sentence: Int,
        @SerialName("emotion")
        val emotion: Int,
    )
}
