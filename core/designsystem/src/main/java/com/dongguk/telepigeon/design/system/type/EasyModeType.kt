package com.dongguk.telepigeon.design.system.type

import android.content.Context
import androidx.annotation.StringRes
import com.dongguk.telepigeon.core.design.system.R

enum class EasyModeType(
    @StringRes val title: Int,
    val value: Boolean,
) {
    TRUE(
        title = R.string.setting_key_word_extra_easy_mode_true,
        value = true,
    ),
    FALSE(
        title = R.string.setting_key_word_extra_easy_mode_false,
        value = false,
    ),
    ;

    companion object {
        fun getTitleByValue(value: Boolean): Int = entries.first { it.value == value }.title

        fun Context.getValueByTitle(title: String): Boolean = entries.first { getString(it.title) == title }.value
    }
}
