package com.dongguk.telepigeon.feature.setting.setting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithOneBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.BottomSheetWithOneBtnType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentSettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SettingFragment : BindingFragment<FragmentSettingBinding>({ FragmentSettingBinding.inflate(it) }) {
    private val settingViewModel by viewModels<SettingViewModel>()
    private lateinit var settingWorrySettingAdapter: SettingWorrySettingAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        settingViewModel.getRoomInfo()
        settingViewModel.getRoomKeywords()
        settingViewModel.getRoomKeywordExtra()
        initAdapter()
        initLayout()
        collectGetRoomInfoState()
        collectGetRoomKeywordsState()
        collectGetRoomKeywordExtraState()
        setBtnSettingKeywordModifyClickListener()
        setBtnSettingWorrySettingClickListener()
    }

    private fun initAdapter() {
        settingWorrySettingAdapter = SettingWorrySettingAdapter()
        binding.rvSettingWorrySetting.adapter = settingWorrySettingAdapter

        // TODO 서버통신 구현 후 collectData 함수로 해당 로직 이동
        settingWorrySettingAdapter.submitList(settingViewModel.dummyRoomWorryModel)
    }

    private fun initLayout() {
        with(binding) {
            etSettingRoomInfoCode.editText.isEnabled = false
            etSettingRoomInfoName.editText.isEnabled = false
            etSettingKeyWordCurrent.editText.isEnabled = false
        }
    }

    private fun collectGetRoomInfoState() {
        settingViewModel.getRoomInfoState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getRoomInfoState ->
            when (getRoomInfoState) {
                is UiState.Success -> {
                    with(getRoomInfoState.data) {
                        binding.etSettingRoomInfoCode.editText.setText(code)
                        binding.etSettingRoomInfoName.editText.setText(name)
                        setBtnSettingRoomInfoCopyClickListener(code = code)
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectGetRoomKeywordsState() {
        settingViewModel.getRoomKeywordsState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getRoomKeywordsState ->
            when (getRoomKeywordsState) {
                is UiState.Success -> {
                    with(getRoomKeywordsState.data) {
                        binding.etSettingKeyWordCurrent.editText.setText(keywords)
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectGetRoomKeywordExtraState() {
        settingViewModel.getRoomKeywordExtraState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getRoomKeywordExtraState ->
            when (getRoomKeywordExtraState) {
                is UiState.Success -> {
                    with(getRoomKeywordExtraState.data) {
                        binding.tvSettingKeyWordExtraGenderContent.text = gender
                        binding.tvSettingKeyWordAgeGroupContent.text = ageRange
                        binding.tvSettingKeyWordRelationshipContent.text = relation
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBtnSettingRoomInfoCopyClickListener(code: String) {
        binding.btnSettingRoomInfoCopy.setOnClickListener {
            copyCopyCode(code)
            showCopyCodeBottomSheetDialogFragment()
        }
    }

    private fun setBtnSettingKeywordModifyClickListener() {
        binding.btnSettingKeyWordModify.setOnClickListener {
            navigateToKeywordSetting()
        }
    }

    private fun setBtnSettingWorrySettingClickListener() {
        binding.btnSettingWorrySetting.setOnClickListener {
            navigateToWorrySetting()
        }
    }

    private fun showCopyCodeBottomSheetDialogFragment() {
        BottomSheetWithOneBtnDialogFragment(
            bottomSheetWithOneBtnType = BottomSheetWithOneBtnType.COPY_CODE,
        ).show(childFragmentManager, COPY_CODE_BOTTOM_SHEET)
    }

    private fun copyCopyCode(code: String) {
        (requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText(CODE, code))
    }

    private fun navigateToKeywordSetting() {
        findNavController().navigate(R.id.action_all_navi_setting_to_keyword_setting)
    }

    private fun navigateToWorrySetting() {
        findNavController().navigate(R.id.action_all_navi_setting_to_worry_setting)
    }

    companion object {
        private const val COPY_CODE_BOTTOM_SHEET = "copyCodeBottomSheet"
        private const val CODE = "code"
    }
}
