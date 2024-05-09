package com.dongguk.telepigeon.design.system.type

import androidx.annotation.StringRes
import com.dongguk.telepigeon.core.design.system.R

enum class QnaType(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @StringRes val btnText: Int
) {
    SURVIVAL(
        title = R.string.qna_title_survival,
        description = R.string.qna_description_survival,
        btnText = R.string.qna_btn_text_survival
    ),
    CHECK_ANSWER(
        title = R.string.qna_title_check_answer,
        description = R.string.answer_description_check_answer,
        btnText = R.string.qna_btn_text_check_answer
    )
}