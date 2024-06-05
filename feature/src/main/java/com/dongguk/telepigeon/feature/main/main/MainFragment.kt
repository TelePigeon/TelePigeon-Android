package com.dongguk.telepigeon.feature.main.main

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.type.MainType
import com.dongguk.telepigeon.design.system.type.QnaType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentMainBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.dongguk.telpigeon.core.ui.util.view.UiState
import kotlinx.coroutines.flow.onEach

class MainFragment : BindingFragment<FragmentMainBinding>({ FragmentMainBinding.inflate(it) }) {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        collectGetLatestRoomInfoState()
    }

    private fun setHomeType(mainType: MainType, number: Int) {
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
                            2 -> MainType.ARRIVE_SURVIVAL
                            3 -> MainType.WAIT_SURVIVAL
                            4 -> MainType.GOT_SURVIVAL
                            5 -> MainType.GOT_QUESTION
                            else -> {
                                days?.let { days ->
                                    if (days >= DAYS_THRESHOLD) {
                                        MainType.NOT_SEND_SURVIVAL
                                    } else {
                                        MainType.GOT_QUESTION
                                    }
                                }
                            }
                        }?.let { mainType ->
                            setHomeType(mainType = mainType, number = number)
                        }
                    }
                }

                else -> Unit
            }
        }
    }

    private fun setTvMainButtonClickListener(
        @StringRes btnText: Int,
    ) {
        binding.tvMainButton.setOnClickListener {
            when (btnText) {
                com.dongguk.telepigeon.core.design.system.R.string.main_btn_text_check_answer -> navigateToQna(qnaType = QnaType.CHECK_ANSWER)
                com.dongguk.telepigeon.core.design.system.R.string.main_btn_text_answer_question -> navigateToQna(qnaType = QnaType.SURVIVAL)
            }
        }
    }

    private fun navigateToQna(qnaType: QnaType) {
        findNavController().navigate(R.id.action_main_to_main_qna, bundleOf(QNA_TYPE to qnaType.name))
    }

    companion object {
        private const val DAYS_THRESHOLD = 2
        const val QNA_TYPE = "qnaType"
    }
}
