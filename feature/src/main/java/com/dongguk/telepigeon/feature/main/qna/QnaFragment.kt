package com.dongguk.telepigeon.feature.main.qna

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.dongguk.telepigeon.design.system.mapper.toQnaType
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.QnaType
import com.dongguk.telepigeon.design.system.type.TelePigeonQnaEditTextType
import com.dongguk.telepigeon.feature.databinding.FragmentQnaBinding
import com.dongguk.telepigeon.feature.main.main.MainFragment.Companion.QNA_TYPE
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class QnaFragment : BindingFragment<FragmentQnaBinding>({ FragmentQnaBinding.inflate(it) }) {
    private val qnaViewModel by viewModels<QnaViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setAppBar()
        requireArguments().getString(QNA_TYPE)?.toQnaType()?.let { setQnaType(it) }
        collectGetQuestionState()
    }

    private fun initLayout() {
        with(binding) {
            etQnaQuestion.initLayout(qnaEditTextType = TelePigeonQnaEditTextType.Q)
            etQnaQuestion.editText.isEnabled = false
            etQnaAnswer.initLayout(qnaEditTextType = TelePigeonQnaEditTextType.A)
        }
    }

    private fun setAppBar() {
        binding.appbarQna.initLayout(appBarType = AppBarType.X)
        binding.appbarQna.binding.ivAppBarTelepigeonX.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setQnaType(qnaType: QnaType) {
        with(binding) {
            tvQnaTitle.text = stringOf(qnaType.title)
            tvQnaDescription.text = stringOf(qnaType.description)
            btnQna.text = stringOf(qnaType.btnText)
        }

        when (qnaType) {
            QnaType.SURVIVAL -> {
                with(binding) {
                    qnaViewModel.getQuestion()
                    ivQnaPicture.visibility = View.INVISIBLE
                }
            }

            QnaType.CHECK_ANSWER -> {
                with(binding) {
                    etQnaAnswer.editText.isEnabled = false
                    layoutQnaAddPicture.visibility = View.GONE
                    etQnaQuestion.editText.setText(qnaViewModel.dummyQuestionAnswerModel.questionContent)
                    etQnaAnswer.editText.setText(qnaViewModel.dummyQuestionAnswerModel.answerContent)
                    ivQnaPicture.load(qnaViewModel.dummyQuestionAnswerModel.answerImage)
                    ivQnaWarning.visibility = View.INVISIBLE
                    tvQnaWarning.visibility = View.INVISIBLE
                }
            }
        }

        setBtnQnaClickListener(qnaType = qnaType)
    }

    private fun collectGetQuestionState() {
        qnaViewModel.getQuestionState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getQuestionState ->
            when(getQuestionState) {
                is UiState.Success -> {
                    with(getQuestionState.data) {
                        binding.etQnaQuestion.editText.setText(content)
                        binding.ivQnaWarning.visibility = if (isPenalty) View.VISIBLE else View.INVISIBLE
                        binding.tvQnaWarning.visibility = if (isPenalty) View.VISIBLE else View.INVISIBLE
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBtnQnaClickListener(qnaType: QnaType) {
        binding.btnQna.setOnClickListener {
            when (qnaType) {
                QnaType.CHECK_ANSWER -> findNavController().popBackStack()
                QnaType.SURVIVAL -> Unit
            }
        }
    }
}
