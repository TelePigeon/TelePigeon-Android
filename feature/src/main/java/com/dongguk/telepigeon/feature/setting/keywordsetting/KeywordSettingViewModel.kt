package com.dongguk.telepigeon.feature.setting.keywordsetting

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.KeywordExtraModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KeywordSettingViewModel @Inject constructor() : ViewModel() {
    val dummyKeywords = listOf("운동", "수업", "산책", "영양제", "약", "밥", "일", "회사생활", "공부", "청소", "간식", "꿈")
    val dummySelectedKeywords = listOf("운동", "영양제", "밥")
    val dummyKeywordExtraModel = KeywordExtraModel(
        gender = "-",
        ageRange = "-",
        relation = "-"
    )
    val dummyGenders = listOf("남성", "여성")
    val dummyAgeRanges = listOf("10대", "20대", "30대")
    val dummyRelations = listOf("지인", "자식", "엄마")
}