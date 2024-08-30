package com.dongguk.telepigeon.feature.main.main

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.type.MainType
import com.dongguk.telepigeon.design.system.type.QnaType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentMainBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>({ FragmentMainBinding.inflate(it) }) {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getLatestRoomInfo()
        collectGetLatestRoomInfoState()
        collectPostHurryState()
    }

    private fun setHomeType(
        mainType: MainType,
        number: Int,
    ) {
        with(binding) {
            tvMainSpeechBubble.text = if (mainType == MainType.NOT_SEND_SURVIVAL) getString(mainType.speechBubbleText, number) else stringOf(mainType.speechBubbleText)
            ivMainCharacter.setImageResource(mainType.character)
            mainType.btnText?.let { btnText ->
                tvMainButton.text = stringOf(btnText)
                tvMainButton.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                setTvMainButtonClickListener(btnText)
            }
        }
    }

    private fun collectGetLatestRoomInfoState() {
        mainViewModel.getLatestRoomInfoState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getLatestRoomInfoState ->
            when (getLatestRoomInfoState) {
                is UiState.Success -> {
                    with(getLatestRoomInfoState.data) {
                        binding.tvMainRoomName.text = name

                        when (number) {
                            1 -> MainType.SENT_HURRY
                            2 -> MainType.GOT_SURVIVAL
                            3 -> MainType.ARRIVE_SURVIVAL
                            4 -> MainType.COMPLETED
                            5 -> MainType.WAIT_SURVIVAL
                            6 -> {
                                days?.let { days ->
                                    if (days >= DAYS_THRESHOLD) {
                                        MainType.NOT_SEND_SURVIVAL
                                    } else {
                                        MainType.GOT_QUESTION
                                    }
                                }
                            }
                            else -> MainType.WAITING
                        }?.let { mainType ->
                            days?.let { days ->
                                setHomeType(mainType = mainType, number = days) }
                        }
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectPostHurryState() {
        mainViewModel.postHurryState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { postHurryState ->
            when (postHurryState) {
                is UiState.Success -> {
                    when (postHurryState.data) {
                        SUCCESS -> mainViewModel.getLatestRoomInfo()
                        CONFLICT -> Unit
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setTvMainButtonClickListener(
        @StringRes btnText: Int,
    ) {
        binding.tvMainButton.setOnClickListener {
            when (btnText) {
                com.dongguk.telepigeon.core.design.system.R.string.main_btn_text_check_answer -> navigateToQna(qnaType = QnaType.CHECK_ANSWER)
                com.dongguk.telepigeon.core.design.system.R.string.main_btn_text_answer_question -> navigateToQna(qnaType = QnaType.SURVIVAL)
                com.dongguk.telepigeon.core.design.system.R.string.main_btn_text_hurry -> mainViewModel.postHurry()
            }
        }
    }

    private fun navigateToQna(qnaType: QnaType) {
        findNavController().navigate(R.id.action_main_to_main_qna, bundleOf(QNA_TYPE to qnaType.name))
    }

    companion object {
        private const val DAYS_THRESHOLD = 2
        const val QNA_TYPE = "qnaType"
        private const val SUCCESS = "success"
        private const val CONFLICT = "conflict"
    }
}
