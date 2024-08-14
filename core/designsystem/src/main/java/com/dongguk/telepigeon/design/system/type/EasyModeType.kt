package com.dongguk.telepigeon.design.system.type

import androidx.annotation.StringRes
import com.dongguk.telepigeon.core.design.system.R

enum class EasyModeType(
    @StringRes val title: Int,
    val value: Boolean
) {
    TRUE(
        title = R.string.setting_key_word_extra_easy_mode_true,
        value = true
    ),
    FALSE(
        title = R.string.setting_key_word_extra_easy_mode_false,
        value = false
    );

    companion object {
        fun getTitleByValue(value: Boolean): Int {
            return entries.first { it.value == value }.title
        }
    }
}