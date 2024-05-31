package com.dongguk.telepigeon.design.system.type

import androidx.annotation.StringRes
import com.dongguk.telepigeon.core.design.system.R

enum class BottomSheetWithSelectionType(
    @StringRes val title: Int,
    @StringRes val btnText: Int,
) {
    GENDER(
        title = R.string.setting_key_word_extra_gender,
        btnText = R.string.button_apply,
    ),
    AGE_RANGE(
        title = R.string.setting_key_word_extra_age_range,
        btnText = R.string.button_apply,
    ),
    RELATION(
        title = R.string.setting_key_word_extra_relation,
        btnText = R.string.button_apply,
    ),
}
