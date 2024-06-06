package com.dongguk.telepigeon.design.system.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dongguk.telepigeon.core.design.system.R

enum class MainType(
    @StringRes val speechBubbleText: Int,
    @DrawableRes val character: Int,
    @StringRes val btnText: Int?,
) {
    WAITING(
        speechBubbleText = R.string.main_speech_bubble_text_waiting,
        character = R.drawable.ic_character,
        btnText = null
    ),
    COMPLETED(
        speechBubbleText = R.string.main_speech_bubble_text_completed,
        character = R.drawable.ic_character,
        btnText = null
    ),
    SENT_HURRY(
        speechBubbleText = R.string.main_speech_bubble_text_sent_hurry,
        character = R.drawable.ic_character,
        btnText = null,
    ),
    ARRIVE_SURVIVAL(
        speechBubbleText = R.string.main_speech_bubble_text_arrive_survival,
        character = R.drawable.ic_character_letter,
        btnText = R.string.main_btn_text_check_answer,
    ),
    WAIT_SURVIVAL(
        speechBubbleText = R.string.main_speech_bubble_text_wait_survival,
        character = R.drawable.ic_character_oh,
        btnText = R.string.main_btn_text_hurry,
    ),
    GOT_SURVIVAL(
        speechBubbleText = R.string.main_speech_bubble_text_got_survival,
        character = R.drawable.ic_character_angry,
        btnText = R.string.main_btn_text_answer_question,
    ),
    GOT_QUESTION(
        speechBubbleText = R.string.main_speech_bubble_text_got_question,
        character = R.drawable.ic_character_letter,
        btnText = R.string.main_btn_text_answer_question,
    ),
    NOT_SEND_SURVIVAL(
        speechBubbleText = R.string.main_speech_bubble_text_not_send_survival,
        character = R.drawable.ic_character_letter,
        btnText = R.string.main_btn_text_answer_question,
    ),
}
