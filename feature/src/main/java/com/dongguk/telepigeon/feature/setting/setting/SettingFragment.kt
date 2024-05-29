package com.dongguk.telepigeon.feature.setting.setting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithOneBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.BottomSheetWithOneBtnType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentSettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class SettingFragment : BindingFragment<FragmentSettingBinding>({ FragmentSettingBinding.inflate(it) }) {
    private val settingViewModel by viewModels<SettingViewModel>()
    private lateinit var settingWorrySettingAdapter: SettingWorrySettingAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initLayout()
        setBtnSettingRoomInfoCopyClickListener()
        setBtnSettingKeywordModifyClickListener()
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

            // TODO 서버통신 구현 후 collectData 함수로 해당 로직 이동
            etSettingRoomInfoCode.editText.setText(settingViewModel.dummyRoomInfoModel.code)
            etSettingRoomInfoName.editText.setText(settingViewModel.dummyRoomInfoModel.name)
            etSettingKeyWordCurrent.editText.setText(settingViewModel.dummyRoomKeywordModel.keywords)

            tvSettingKeyWordExtraGenderContent.text = settingViewModel.dummyRoomKeywordExtraModel.gender
            tvSettingKeyWordAgeGroupContent.text = settingViewModel.dummyRoomKeywordExtraModel.ageRange
            tvSettingKeyWordRelationshipContent.text = settingViewModel.dummyRoomKeywordExtraModel.relation
        }
    }

    private fun setBtnSettingRoomInfoCopyClickListener() {
        binding.btnSettingRoomInfoCopy.setOnClickListener {
            copyCopyCode(settingViewModel.dummyRoomInfoModel.code)
            showCopyCodeBottomSheetDialogFragment()
        }
    }

    private fun setBtnSettingKeywordModifyClickListener() {
        binding.btnSettingKeyWordModify.setOnClickListener {
            navigateToKeywordSetting()
        }
    }

    private fun showCopyCodeBottomSheetDialogFragment() {
        BottomSheetWithOneBtnDialogFragment(
            bottomSheetWithOneBtnType = BottomSheetWithOneBtnType.COPY_CODE
        ).show(childFragmentManager, COPY_CODE_BOTTOM_SHEET)
    }

    private fun copyCopyCode(code: String) {
        (requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText(CODE, code))
    }

    private fun navigateToKeywordSetting() {
        findNavController().navigate(R.id.action_all_navi_setting_to_keyword_setting)
    }

    companion object {
        private const val COPY_CODE_BOTTOM_SHEET = "copyCodeBottomSheet"
        private const val CODE = "code"
    }
}
