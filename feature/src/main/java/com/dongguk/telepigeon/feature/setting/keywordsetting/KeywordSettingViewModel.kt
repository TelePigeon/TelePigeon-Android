package com.dongguk.telepigeon.feature.setting.keywordsetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.KeywordExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.PutRoomKeywordExtraUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordSettingViewModel
    @Inject
    constructor(
        private val getRoomIdUseCase: GetRoomIdUseCase,
        private val putRoomKeywordExtraUseCase: PutRoomKeywordExtraUseCase,
    ) : ViewModel() {
        private val _putRoomKeywordExtraState = MutableSharedFlow<UiState<Unit>>()
        val putRoomKeywordExtraState get() = _putRoomKeywordExtraState.asSharedFlow()

        private val roomId = getRoomIdUseCase()

        fun putRoomKeywordExtra(roomKeywordsExtraModel: RoomKeywordsExtraModel) {
            viewModelScope.launch {
                _putRoomKeywordExtraState.emit(UiState.Empty)
                putRoomKeywordExtraUseCase(roomId = roomId, roomKeywordsExtraModel = roomKeywordsExtraModel).onSuccess {
                    _putRoomKeywordExtraState.emit(UiState.Success(Unit))
                }.onFailure { exception: Throwable ->
                    _putRoomKeywordExtraState.emit(UiState.Error(exception.message))
                }
            }
        }

        val dummyKeywords = listOf("운동", "수업", "산책", "영양제", "약", "밥", "일", "회사생활", "공부", "청소", "간식", "꿈")
        val dummySelectedKeywords = listOf("운동", "영양제", "밥")
        val dummyKeywordExtraModel =
            KeywordExtraModel(
                gender = "-",
                ageRange = "-",
                relation = "-",
            )
        val dummyGenders = listOf("남성", "여성")
        val dummyAgeRanges = listOf("10대", "20대", "30대")
        val dummyRelations = listOf("지인", "자식", "엄마")
    }
