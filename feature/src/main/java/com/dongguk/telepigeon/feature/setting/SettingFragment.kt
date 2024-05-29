package com.dongguk.telepigeon.feature.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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
}
