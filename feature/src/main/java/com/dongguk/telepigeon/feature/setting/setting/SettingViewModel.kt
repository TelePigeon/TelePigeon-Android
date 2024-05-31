package com.dongguk.telepigeon.feature.setting.setting

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.RoomInfoModel
import com.dongguk.telepigeon.domain.model.RoomKeywordExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordModel
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel
    @Inject
    constructor() : ViewModel() {
        val dummyRoomInfoModel =
            RoomInfoModel(
                code = "1233972abdce",
                name = "디스이즈방이름",
            )

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
