package com.dongguk.telepigeon.feature.setting.worrysetting

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorrySettingViewModel
    @Inject
    constructor() : ViewModel() {
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
