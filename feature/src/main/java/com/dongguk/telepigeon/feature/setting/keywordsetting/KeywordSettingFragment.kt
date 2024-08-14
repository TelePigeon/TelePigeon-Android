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
import com.dongguk.telepigeon.design.system.type.EasyModeType
import com.dongguk.telepigeon.design.system.type.EasyModeType.entries
import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel
import com.dongguk.telepigeon.feature.databinding.FragmentKeywordSettingBinding
import com.dongguk.telepigeon.feature.setting.setting.SettingFragment.Companion.KEYWORDS
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
    private val genderBottomSheetWithSelectionAdapter = BottomSheetWithSelectionAdapter()
    private val ageRangeBottomSheetWithSelectionAdapter = BottomSheetWithSelectionAdapter()
    private val relationBottomSheetWithSelectionAdapter = BottomSheetWithSelectionAdapter()
    private val easyModeBottomSheetWithSelectionAdapter = BottomSheetWithSelectionAdapter()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        keywordSettingViewModel.getRoomKeywordExtra()
        keywordSettingViewModel.getKeywords()
        keywordSettingViewModel.getGenders()
        keywordSettingViewModel.getAgeRanges()
        keywordSettingViewModel.getRelations()

        intAppBar()
        initLayout()
        collectPutRoomKeywordExtraState()
        collectGetRoomKeywordExtraState()
        collectGetKeywordsState()
        collectGetGendersState()
        collectGetAgeRangesState()
        collectGetRelationsState()
        setEtKeywordSettingEasyModeClickListener()
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
        }
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

    private fun collectGetRoomKeywordExtraState() {
        keywordSettingViewModel.getRoomKeywordExtraState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getRoomKeywordExtraState ->
            when (getRoomKeywordExtraState) {
                is UiState.Success -> {
                    with(getRoomKeywordExtraState.data) {
                        binding.etKeywordSettingGender.editText.setText(gender)
                        binding.etKeywordSettingAgeRange.editText.setText(ageRange)
                        binding.etKeywordSettingRelation.editText.setText(relation)
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectGetKeywordsState() {
        keywordSettingViewModel.getKeywordsState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getKeywordsState ->
            when (getKeywordsState) {
                is UiState.Success -> requireArguments().getString(KEYWORDS)?.split(", ")?.let { setKeywordChip(getKeywordsState.data, it.toList()) }
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectGetGendersState() {
        keywordSettingViewModel.getGendersState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getGendersState ->
            when (getGendersState) {
                is UiState.Success -> setEtKeywordSettingGenderClickListener(genders = getGendersState.data)
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectGetAgeRangesState() {
        keywordSettingViewModel.getAgeRangesState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getAgeRangesState ->
            when (getAgeRangesState) {
                is UiState.Success -> setEtKeywordSettingAgeRangeClickListener(ageRanges = getAgeRangesState.data)
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectGetRelationsState() {
        keywordSettingViewModel.getRelationsState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getRelationsState ->
            when (getRelationsState) {
                is UiState.Success -> setEtKeywordSettingRelationClickListener(relations = getRelationsState.data)
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

    private fun setEtKeywordSettingGenderClickListener(genders: List<String>) {
        binding.etKeywordSettingGender.setOnClickListener {
            showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType = BottomSheetWithSelectionType.GENDER, selectionList = genders, bottomSheetWithSelectionAdapter = genderBottomSheetWithSelectionAdapter, onDialogClosed = ::setEtKeywordSettingGenderText)
        }
    }

    private fun setEtKeywordSettingAgeRangeClickListener(ageRanges: List<String>) {
        binding.etKeywordSettingAgeRange.setOnClickListener {
            showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType = BottomSheetWithSelectionType.AGE_RANGE, selectionList = ageRanges, bottomSheetWithSelectionAdapter = ageRangeBottomSheetWithSelectionAdapter, onDialogClosed = ::setEtKeywordSettingAgeRangeText)
        }
    }

    private fun setEtKeywordSettingRelationClickListener(relations: List<String>) {
        binding.etKeywordSettingRelation.setOnClickListener {
            showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType = BottomSheetWithSelectionType.RELATION, selectionList = relations, bottomSheetWithSelectionAdapter = relationBottomSheetWithSelectionAdapter, onDialogClosed = ::setEtKeywordSettingRelationText)
        }
    }

    private fun setEtKeywordSettingEasyModeClickListener() {
        binding.etKeywordSettingEasyMode.setOnClickListener {
            showSelectionBottomSheetDialogFragment(bottomSheetWithSelectionType = BottomSheetWithSelectionType.EASY_MODE, selectionList = EasyModeType.entries.map { getString(it.title) }, bottomSheetWithSelectionAdapter = relationBottomSheetWithSelectionAdapter, onDialogClosed = ::setEtKeywordSettingEasyModeText)
        }
    }

    private fun setBtnKeywordSettingCompleteClickListener() {
        binding.btnKeywordSettingComplete.setOnClickListener {
            keywordSettingViewModel.putRoomKeywordExtra(
                roomKeywordsExtraModel =
                    with(binding) {
                        RoomKeywordsExtraModel(
                            keywords = getKeywords(),
                            gender = etKeywordSettingGender.editText.text.toString(),
                            ageRange = etKeywordSettingAgeRange.editText.text.toString(),
                            relation = etKeywordSettingRelation.editText.text.toString(),
                            easyMode = entries.first { getString(it.title) == etKeywordSettingEasyMode.editText.text.toString() }.value,
                        )
                    },
            )
        }
    }

    private fun showSelectionBottomSheetDialogFragment(
        bottomSheetWithSelectionType: BottomSheetWithSelectionType,
        selectionList: List<String>,
        bottomSheetWithSelectionAdapter: BottomSheetWithSelectionAdapter,
        onDialogClosed: (String) -> Unit = {},
    ) {
        BottomSheetWithSelectionDialogFragment(
            bottomSheetWithSelectionType = bottomSheetWithSelectionType,
            selectionList = selectionList,
            bottomSheetWithSelectionAdapter = bottomSheetWithSelectionAdapter,
            onDialogClosed = onDialogClosed,
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

    private fun setEtKeywordSettingGenderText(gender: String) {
        binding.etKeywordSettingGender.editText.setText(gender)
    }

    private fun setEtKeywordSettingAgeRangeText(ageRange: String) {
        binding.etKeywordSettingAgeRange.editText.setText(ageRange)
    }

    private fun setEtKeywordSettingRelationText(relation: String) {
        binding.etKeywordSettingRelation.editText.setText(relation)
    }

    private fun setEtKeywordSettingEasyModeText(easyMode: String) {
        binding.etKeywordSettingEasyMode.editText.setText(easyMode)
    }

    companion object {
        const val SELECTION_BOTTOM_SHEET = "selectionBottomSheet"
    }
}
