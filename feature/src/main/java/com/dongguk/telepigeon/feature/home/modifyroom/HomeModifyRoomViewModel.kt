package com.dongguk.telepigeon.feature.home.modifyroom

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.domain.model.HomeRoomEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeModifyRoomViewModel
    @Inject
    constructor() : ViewModel() {
        val dummyHomeModifyRoom =
            listOf(
                HomeRoomEntity(
                    id = 0,
                    emotion = R.drawable.ic_character_angry,
                    name = "디스이즈방이름",
                    relation = "김둘기(관계없음/관계없음)",
                    answerDescription = R.string.answer_description_done,
                ),
                HomeRoomEntity(
                    id = 1,
                    emotion = R.drawable.ic_character_letter,
                    name = "구구네 집",
                    relation = "구구구(부모/자식)",
                    answerDescription = R.string.answer_description_check_answer,
                ),
                HomeRoomEntity(
                    id = 2,
                    emotion = R.drawable.ic_character_oh,
                    name = "살아있냐",
                    relation = "꼬꼬꼬우(친구/친구)",
                    answerDescription = R.string.answer_description_not_yet,
                ),
            )
    }
