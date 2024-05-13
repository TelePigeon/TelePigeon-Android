package com.dongguk.telepigeon.feature.main.qna

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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
                    ivQnaPicture.visibility = View.INVISIBLE
                    etQnaQuestion.editText.setText(qnaViewModel.dummyCheckQuestionEntity.content)
                    ivQnaWarning.visibility = if (qnaViewModel.dummyCheckQuestionEntity.isPenalty) View.VISIBLE else View.INVISIBLE
                    tvQnaWarning.visibility = if (qnaViewModel.dummyCheckQuestionEntity.isPenalty) View.VISIBLE else View.INVISIBLE
                }
            }

            QnaType.CHECK_ANSWER -> {
                with(binding) {
                    etQnaAnswer.editText.isEnabled = false
                    layoutQnaAddPicture.visibility = View.GONE
                    etQnaQuestion.editText.setText(qnaViewModel.dummyQuestionAnswerEntity.questionContent)
                    etQnaAnswer.editText.setText(qnaViewModel.dummyQuestionAnswerEntity.answerContent)
                    ivQnaPicture.load(qnaViewModel.dummyQuestionAnswerEntity.answerImage)
                    ivQnaWarning.visibility = View.INVISIBLE
                    tvQnaWarning.visibility = View.INVISIBLE
                }
            }
        }

        setBtnQnaClickListener(qnaType = qnaType)
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
