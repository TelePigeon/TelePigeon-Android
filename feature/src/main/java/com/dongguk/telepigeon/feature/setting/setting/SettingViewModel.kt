package com.dongguk.telepigeon.feature.setting.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.RoomInfoModel
import com.dongguk.telepigeon.domain.model.RoomKeywordExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordModel
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomInfoUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel
    @Inject
    constructor(
        private val getRoomIdUseCase: GetRoomIdUseCase,
        private val getRoomInfoUseCase: GetRoomInfoUseCase,
    ) : ViewModel() {
        private val _getRoomInfoState = MutableStateFlow<UiState<RoomInfoModel>>(UiState.Empty)
        val getRoomInfoState get() = _getRoomInfoState.asStateFlow()

        fun getRoomInfo() {
            viewModelScope.launch {
                _getRoomInfoState.value = UiState.Loading
                getRoomInfoUseCase(roomId = getRoomIdUseCase()).onSuccess { roomInfoModel ->
                    _getRoomInfoState.value = UiState.Success(roomInfoModel)
                }.onFailure { exception: Throwable ->
                    _getRoomInfoState.value = UiState.Error(exception.message)
                }
            }
        }

        val dummyRoomKeywordModel =
            RoomKeywordModel(
                keywords = "운동, 영양제, 밥",
            )

        val dummyRoomKeywordExtraModel =
            RoomKeywordExtraModel(
                gender = "남성",
                ageRange = "20대",
                relation = "자식",
            )

        val dummyRoomWorryModel =
            listOf(
                RoomWorryModel(
                    id = 1,
                    name = "영양제 챙겨먹기",
                    content = "오늘 영양제 챙겨먹었어?",
                    times = "매일 09시",
                ),
                RoomWorryModel(
                    id = 2,
                    name = "감기약 챙겨먹기",
                    content = "감기약 제때제때 먹어야 해!",
                    times = "매일 08시, 12시, 18시",
                ),
            )
    }
