package com.dongguk.telepigeon.feature.setting.keywordsetting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithSelectionAdapter
import com.dongguk.telepigeon.design.system.component.BottomSheetWithSelectionDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithSelectionType
import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel
import com.dongguk.telepigeon.feature.databinding.FragmentKeywordSettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.dongguk.telpigeon.core.ui.util.view.UiState
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class KeywordSettingFragment : BindingFragment<FragmentKeywordSettingBinding>({ FragmentKeywordSettingBinding.inflate(it) }) {
    private val keywordSettingViewModel by viewModels<KeywordSettingViewModel>()
    private val bottomSheetWithSelectionAdapter = BottomSheetWithSelectionAdapter()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        intAppBar()
        initLayout()
        collectPutRoomKeywordExtraState()
        setEtKeywordSettingGenderClickListener()
        setEtKeywordSettingAgeRangeClickListener()
        setEtKeywordSettingRelationClickListener()
        setBtnKeywordSettingCompleteClickListener()
    }

    private fun intAppBar() {
        with(binding) {
            appbarKeywordSetting.initLayout(appBarType = AppBarType.TITLE, title = stringOf(com.dongguk.telepigeon.core.design.system.R.string.setting_key_word_title))
            appbarKeywordSetting.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initLayout() {
        with(binding) {
            etKeywordSettingGender.editText.isEnabled = false
            etKeywordSettingAgeRange.editText.isEnabled = false
            etKeywordSettingRelation.editText.isEnabled = false

            etKeywordSettingGender.editText.setText(keywordSettingViewModel.dummyKeywordExtraModel.gender)
            etKeywordSettingAgeRange.editText.setText(keywordSettingViewModel.dummyKeywordExtraModel.ageRange)
            etKeywordSettingRelation.editText.setText(keywordSettingViewModel.dummyKeywordExtraModel.relation)
        }

        setKeywordChip(keywordSettingViewModel.dummyKeywords, keywordSettingViewModel.dummySelectedKeywords)
    }

    private fun collectPutRoomKeywordExtraState() {
        keywordSettingViewModel.putRoomKeywordExtraState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { putRoomKeywordExtraState ->
            when (putRoomKeywordExtraState) {
                is UiState.Success -> {
                    findNavController().popBackStack()
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setKeywordChip(
        keywords: List<String>,
        selectedKeywords: List<String>,
    ) {
        binding.cgKeywordSettingTotalKeyword.removeAllViews()

        for (item in keywords) {
            (layoutInflater.inflate(com.dongguk.telepigeon.core.design.system.R.layout.view_keyword_chip, null, false) as Chip).run {
                text = item
                binding.cgKeywordSettingTotalKeyword.addView(this)
                isChecked = item in selectedKeywords
            }
        }
    }

    private fun setEtKeywordSettingGenderClickListener() {
        binding.etKeywordSettingGender.setOnClickListener {
            showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType = BottomSheetWithSelectionType.GENDER, selectionList = keywordSettingViewModel.dummyGenders)
        }
    }

    private fun setEtKeywordSettingAgeRangeClickListener() {
        binding.etKeywordSettingAgeRange.setOnClickListener {
            showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType = BottomSheetWithSelectionType.AGE_RANGE, selectionList = keywordSettingViewModel.dummyAgeRanges)
        }
    }

    private fun setEtKeywordSettingRelationClickListener() {
        binding.etKeywordSettingRelation.setOnClickListener {
            showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType = BottomSheetWithSelectionType.RELATION, selectionList = keywordSettingViewModel.dummyRelations)
        }
    }

    private fun setBtnKeywordSettingCompleteClickListener() {
        binding.btnKeywordSettingComplete.setOnClickListener {
            keywordSettingViewModel.putRoomKeywordExtra(
                roomKeywordsExtraModel =
                    RoomKeywordsExtraModel(
                        keywords = getKeywords(),
                        gender = binding.etKeywordSettingGender.editText.text.toString(),
                        ageRange = binding.etKeywordSettingAgeRange.editText.text.toString(),
                        relation = binding.etKeywordSettingRelation.editText.text.toString(),
                    ),
            )
        }
    }

    private fun showSelectionBottomSheetDialogFragment(
        bottomSheetWithSelectionType: BottomSheetWithSelectionType,
        selectionList: List<String>,
    ) {
        BottomSheetWithSelectionDialogFragment(
            bottomSheetWithSelectionType = bottomSheetWithSelectionType,
            selectionList = selectionList,
            bottomSheetWithSelectionAdapter = bottomSheetWithSelectionAdapter,
        ).show(childFragmentManager, SELECTION_BOTTOM_SHEET)
    }

    private fun getKeywords(): List<String> {
        val keywords = mutableListOf<String>()
        with(binding.cgKeywordSettingTotalKeyword) {
            for (index in 0..childCount) {
                ((getChildAt(index)) as? Chip)?.let { chip ->
                    if (chip.isChecked) {
                        keywords.add(chip.text.toString())
                    }
                }
            }
        }
        return keywords
    }

    companion object {
        const val MAX_SELECTION = 3
        const val SELECTION_BOTTOM_SHEET = "selectionBottomSheet"
    }
}
