package com.dongguk.telepigeon.feature.main.qna

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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

    private var imageUri = Uri.EMPTY

    private lateinit var getGalleryLauncher: ActivityResultLauncher<String>
    private lateinit var getPhotoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initGalleryLauncher()
        initPhotoPickerLauncher()
        initLayout()
        setAppBar()
        requireArguments().getString(QNA_TYPE)?.toQnaType()?.let { setQnaType(it) }
        collectGetQuestionState()
        collectPostAnswerState()
        collectGetQuestionAnswerState()
        setLayoutQnaAddPictureClickListener()
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
                    qnaViewModel.getQuestionAnswer()
                    etQnaAnswer.editText.isEnabled = false
                    layoutQnaAddPicture.visibility = View.GONE
                    ivQnaWarning.visibility = View.INVISIBLE
                    tvQnaWarning.visibility = View.INVISIBLE

                    binding.btnQna.setOnClickListener {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun collectGetQuestionState() {
        qnaViewModel.getQuestionState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getQuestionState ->
            when (getQuestionState) {
                is UiState.Success -> {
                    with(getQuestionState.data) {
                        binding.etQnaQuestion.editText.setText(content)
                        binding.ivQnaWarning.visibility = if (isPenalty) View.VISIBLE else View.INVISIBLE
                        binding.tvQnaWarning.visibility = if (isPenalty) View.VISIBLE else View.INVISIBLE

                        binding.btnQna.setOnClickListener {
                            qnaViewModel.postAnswer(questionId = id, image = imageUri.toString(), content = binding.etQnaAnswer.editText.text.toString())
                        }
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectPostAnswerState() {
        qnaViewModel.postAnswerState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { postAnswerState ->
            when(postAnswerState) {
                is UiState.Success -> findNavController().popBackStack()
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectGetQuestionAnswerState() {
        qnaViewModel.getQuestionAnswerState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {getQuestionAnswerState ->
            when(getQuestionAnswerState) {
                is UiState.Success -> {
                    with(getQuestionAnswerState.data[0]) {
                        binding.etQnaQuestion.editText.setText(questionContent)
                        binding.etQnaAnswer.editText.setText(answerContent)
                        binding.ivQnaPicture.load(answerImage)
                    }
                }
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setLayoutQnaAddPictureClickListener() {
        binding.layoutQnaAddPicture.setOnClickListener {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                getGalleryLauncher.launch("image/*")
            } else {
                getPhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        }
    }

    private fun initPhotoPickerLauncher() {
        getPhotoPickerLauncher =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { imageUri ->
                imageUri?.let {
                    this.imageUri = it
                    binding.ivQnaPicture.load(it)
                }
            }
    }

    private fun initGalleryLauncher() {
        getGalleryLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
                imageUri?.let {
                    this.imageUri = it
                    binding.ivQnaPicture.load(it)
                }
            }
    }
}
