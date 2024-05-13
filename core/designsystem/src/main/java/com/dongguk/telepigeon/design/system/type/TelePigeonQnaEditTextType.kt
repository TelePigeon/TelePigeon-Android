package com.dongguk.telepigeon.design.system.type

import androidx.annotation.DrawableRes
import com.dongguk.telepigeon.core.design.system.R

enum class TelePigeonQnaEditTextType(
    @DrawableRes val icon: Int,
) {
    Q(
        icon = R.drawable.ic_all_question_30,
    ),
    A(
        icon = R.drawable.ic_all_answer_30,
    ),
}
