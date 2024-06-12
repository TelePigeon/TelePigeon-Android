package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomsDto.ResponseGetRoomDto
import com.dongguk.telepigeon.domain.model.HomeRoomModel

fun ResponseGetRoomDto.toHomeRoomModel() =
    HomeRoomModel(
        id = this.id,
        emotion = this.toHomeRoomModelEmotion(),
        name = this.name,
        relation = this.toHomeRoomModelRelation(),
        sentence = this.toHomeRoomModelSentence(),
    )

fun ResponseGetRoomDto.toHomeRoomModelRelation(): String = this.opponentNickname + "(" + this.myRelation + "/" + this.opponentRelation + ")"

fun ResponseGetRoomDto.toHomeRoomModelEmotion(): Int =
    when (this.emotion) {
        0 -> R.drawable.ic_character_happy_bold
        1 -> R.drawable.ic_character_oh_bold
        2 -> R.drawable.ic_character_angry_bold
        else -> R.drawable.ic_character_sad_bold
    }

fun ResponseGetRoomDto.toHomeRoomModelSentence(): Int =
    when (this.sentence) {
        0 -> R.string.answer_description_check_answer
        1 -> R.string.answer_description_done
        2 -> R.string.answer_description_not_yet
        3 -> R.string.answer_description_no_one_else
        else -> R.string.answer_description_other_person_left_room
    }
