package com.dongguk.telepigeon.design.system.type

import androidx.annotation.StringRes
import com.dongguk.telepigeon.core.design.system.R

enum class BottomSheetWithTwoBtnType(
    @StringRes val interjection: Int,
    @StringRes val sentence: Int,
    @StringRes val leftBtnText: Int,
    @StringRes val rightBtnText: Int,
) {
    WITHDRAWAL(
        interjection = R.string.dialog_interjection_whoops,
        sentence = R.string.dialog_sentence_withdrawal,
        leftBtnText = R.string.dialog_check,
        rightBtnText = R.string.dialog_cancellation,
    ),
    LOGOUT(
        interjection = R.string.dialog_interjection_whoops,
        sentence = R.string.dialog_sentence_logout,
        leftBtnText = R.string.dialog_check,
        rightBtnText = R.string.dialog_cancellation,
    ),
    DELETE_ROOM(
        interjection = R.string.dialog_interjection_whoops,
        sentence = R.string.dialog_sententce_delete_room,
        leftBtnText = R.string.dialog_check,
        rightBtnText = R.string.dialog_cancellation,
    ),
}
