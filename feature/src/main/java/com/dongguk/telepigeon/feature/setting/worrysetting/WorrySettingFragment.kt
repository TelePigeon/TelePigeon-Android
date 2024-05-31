package com.dongguk.telepigeon.feature.setting.worrysetting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.design.system.component.BottomSheetWithTwoBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithTwoBtnType
import com.dongguk.telepigeon.feature.databinding.FragmentWorrySettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf

class WorrySettingFragment : BindingFragment<FragmentWorrySettingBinding>({ FragmentWorrySettingBinding.inflate(it) }) {
    private val worrySettingViewModel by viewModels<WorrySettingViewModel>()
    private lateinit var worrySettingAdapter: WorrySettingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initLayout()
        setBtnWorrySettingAddClickListener()
    }

    private fun initAdapter() {
        worrySettingAdapter = WorrySettingAdapter(showDeleteWorryBottomSheetDialogFragment = ::showDeleteWorryBottomSheetDialogFragment)
        binding.rvWorrySetting.adapter = worrySettingAdapter

        worrySettingAdapter.submitList(worrySettingViewModel.dummyRoomWorryModel)
    }

    private fun initLayout() {
        with(binding) {
            appbarWorrySetting.initLayout(appBarType = AppBarType.TITLE, title = stringOf(R.string.setting_worry_setting_title))
            appbarWorrySetting.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setBtnWorrySettingAddClickListener() {
        binding.btnWorrySettingAdd.setOnClickListener {
            navigateToAddWorry()
        }
    }

    private fun showDeleteWorryBottomSheetDialogFragment(worryId: Int) {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.DELETE_WORRY
        ).show(childFragmentManager, DELETE_WORRY_BOTTOM_SHEET)
    }

    private fun navigateToAddWorry() {
        findNavController().navigate(com.dongguk.telepigeon.feature.R.id.action_worry_setting_to_add_worry)
    }

    companion object {
        private const val DELETE_WORRY_BOTTOM_SHEET = "deleteWorryBottomSheet"
    }
}