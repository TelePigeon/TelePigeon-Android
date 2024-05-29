package com.dongguk.telepigeon.feature.main.main

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.RoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor() : ViewModel() {
        val dummyRoom =
            RoomModel(
                name = "디스이즈방이름",
                number = 2,
                days = 1,
            )
    }
