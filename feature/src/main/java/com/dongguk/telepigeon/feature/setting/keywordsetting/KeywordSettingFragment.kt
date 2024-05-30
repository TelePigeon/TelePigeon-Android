package com.dongguk.telepigeon.feature.setting.keywordsetting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithSelectionAdapter
import com.dongguk.telepigeon.design.system.component.BottomSheetWithSelectionDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithSelectionType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentKeywordSettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.google.android.material.chip.Chip

class KeywordSettingFragment : BindingFragment<FragmentKeywordSettingBinding>({ FragmentKeywordSettingBinding.inflate(it) }) {
    private val keywordSettingViewModel by viewModels<KeywordSettingViewModel>()
    private val bottomSheetWithSelectionAdapter = BottomSheetWithSelectionAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setEtKeywordSettingGenderClickListener()
        setEtKeywordSettingAgeRangeClickListener()
        setEtKeywordSettingRelationClickListener()
    }

    private fun initLayout() {
        with(binding) {
            etKeywordSettingGender.editText.isEnabled = false
            etKeywordSettingAgeRange.editText.isEnabled = false
            etKeywordSettingRelation.editText.isEnabled = false

            etKeywordSettingGender.editText.setText(keywordSettingViewModel.dummyKeywordExtraModel.gender)
            etKeywordSettingAgeRange.editText.setText(keywordSettingViewModel.dummyKeywordExtraModel.ageRange)
            etKeywordSettingRelation.editText.setText(keywordSettingViewModel.dummyKeywordExtraModel.relation)

            binding.appbarKeywordSetting.initLayout(appBarType = AppBarType.TITLE, title = stringOf(com.dongguk.telepigeon.core.design.system.R.string.setting_key_word_title))
            binding.appbarKeywordSetting.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        setKeywordChip(keywordSettingViewModel.dummyKeywords, keywordSettingViewModel.dummySelectedKeywords)
    }

    private fun setKeywordChip(keywords: List<String>, selectedKeywords: List<String>) {
        binding.cgKeywordSettingTotalKeyword.removeAllViews()

        for (item in keywords) {
            (layoutInflater.inflate(R.layout.view_keyword_chip, null, false) as Chip).run {
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

    private fun showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType: BottomSheetWithSelectionType, selectionList: List<String>) {
        BottomSheetWithSelectionDialogFragment(
            bottomSheetWithSelectionType = bottomSheetWithSelectionType,
            selectionList = selectionList,
            bottomSheetWithSelectionAdapter = bottomSheetWithSelectionAdapter
        ).show(childFragmentManager, SELECTION_BOTTOM_SHEET)
    }

    companion object {
        const val MAX_SELECTION = 3
        const val SELECTION_BOTTOM_SHEET = "selectionBottomSheet"
    }
}