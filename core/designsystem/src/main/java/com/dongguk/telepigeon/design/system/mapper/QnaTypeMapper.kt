package com.dongguk.telepigeon.design.system.mapper

import com.dongguk.telepigeon.design.system.type.QnaType

fun String.toQnaType(): QnaType = QnaType.valueOf(this)
