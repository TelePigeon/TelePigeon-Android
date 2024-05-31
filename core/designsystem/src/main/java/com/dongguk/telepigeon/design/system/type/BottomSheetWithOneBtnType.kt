package com.dongguk.telepigeon.design.system.type

import androidx.annotation.StringRes
import com.dongguk.telepigeon.core.design.system.R

enum class BottomSheetWithOneBtnType(
    @StringRes val interjection: Int,
    @StringRes val sentence: Int,
    @StringRes val btnText: Int,
) {
    WRONG_CODE(
        interjection = R.string.dialog_interjection_whoops,
        sentence = R.string.dialog_sentence_wrong_code,
        btnText = R.string.dialog_check,
    ),
    ENTERED_ROOM(
        interjection = R.string.dialog_interjection_whoops,
        sentence = R.string.dialog_sentence_entered_room,
        btnText = R.string.dialog_check,
    ),
    MATCHED_ROOM(
        interjection = R.string.dialog_interjection_whoops,
        sentence = R.string.dialog_sentence_matched_room,
        btnText = R.string.dialog_check,
    ),
    CREATE_ROOM(
        interjection = R.string.dialog_interjection_ooh,
        sentence = R.string.dialog_sentence_create_room,
        btnText = R.string.dialog_check,
    ),
    COPY_CODE(
        interjection = R.string.dialog_interjection_ooh,
        sentence = R.string.dialog_sentence_copy_code,
        btnText = R.string.dialog_check,
    ),
}
