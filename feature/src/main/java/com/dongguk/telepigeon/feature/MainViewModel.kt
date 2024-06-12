package com.dongguk.telepigeon.feature

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.usecase.SetRoomIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val setRoomIdUseCase: SetRoomIdUseCase,
    ) : ViewModel() {
        fun setRoomId(roomId: Int) {
            setRoomIdUseCase(roomId = roomId)
        }
    }
