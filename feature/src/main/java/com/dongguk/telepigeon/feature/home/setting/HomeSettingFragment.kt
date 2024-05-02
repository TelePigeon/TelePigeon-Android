package com.dongguk.telepigeon.feature.home.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.design.system.component.BottomSheetWithTwoBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithTwoBtnType
import com.dongguk.telepigeon.feature.databinding.FragmentHomeSettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf

class HomeSettingFragment : BindingFragment<FragmentHomeSettingBinding>({ FragmentHomeSettingBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        setTvHomeSettingWithdrawalClickListeners()
        setTvHomeSettingLogoutClickListeners()
    }

    private fun initAppBar() {
        binding.appbarHomeSetting.initLayout(appBarType = AppBarType.TITLE, title = stringOf(R.string.home_setting_title))
        binding.appbarHomeSetting.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setTvHomeSettingWithdrawalClickListeners() {
        binding.tvHomeSettingWithdrawal.setOnClickListener {
            showWithdrawalBottomSheetDialogFragment()
        }
    }

    private fun setTvHomeSettingLogoutClickListeners() {
        binding.tvHomeSettingLogout.setOnClickListener {
            showLogoutBottomSheerDialogFragment()
        }
    }

    private fun showWithdrawalBottomSheetDialogFragment() {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.WITHDRAWAL,
        ).show(childFragmentManager, WITHDRAWAL_BOTTOM_SHEET)
    }

    private fun showLogoutBottomSheerDialogFragment() {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.LOGOUT,
        ).show(childFragmentManager, LOGOUT_BOTTOM_SHEET)
    }

    companion object {
        private const val WITHDRAWAL_BOTTOM_SHEET = "withdrawalBottomSheet"
        private const val LOGOUT_BOTTOM_SHEET = "logoutBottomSheer"
    }
}
