package com.dongguk.telepigeon.feature.setting.keywordsetting

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.KeywordExtraModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KeywordSettingViewModel @Inject constructor() : ViewModel() {
    val dummyKeyword = listOf("운동", "수업", "산책", "영양제", "약", "밥", "일", "회사생활", "공부", "청소", "간식", "꿈")
    val dummySelectedKeyword = listOf("운동", "영양제", "밥")
    val dummyKeywordExtraModel = KeywordExtraModel(
        gender = "-",
        ageRange = "-",
        relation = "-"
    )
}