package com.dongguk.telepigeon.feature.home.home

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor() : ViewModel() {
        val dummyHomeRoom =
            listOf(
                HomeRoomModel(
                    id = 0,
                    emotion = R.drawable.ic_character_angry,
                    name = "디스이즈방이름",
                    relation = "김둘기(관계없음/관계없음)",
                    sentence = R.string.answer_description_done,
                ),
                HomeRoomModel(
                    id = 1,
                    emotion = R.drawable.ic_character_letter,
                    name = "구구네 집",
                    relation = "구구구(부모/자식)",
                    sentence = R.string.answer_description_check_answer,
                ),
                HomeRoomModel(
                    id = 2,
                    emotion = R.drawable.ic_character_oh,
                    name = "살아있냐",
                    relation = "꼬꼬꼬우(친구/친구)",
                    sentence = R.string.answer_description_not_yet,
                ),
            )
    }
