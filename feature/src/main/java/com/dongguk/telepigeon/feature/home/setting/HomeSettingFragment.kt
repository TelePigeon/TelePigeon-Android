package com.dongguk.telepigeon.feature.home.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.design.system.component.BottomSheetWithTwoBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithTwoBtnType
import com.dongguk.telepigeon.feature.databinding.FragmentHomeSettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeSettingFragment : BindingFragment<FragmentHomeSettingBinding>({ FragmentHomeSettingBinding.inflate(it) }) {
    private val homeSettingViewModel by viewModels<HomeSettingViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        collectDeleteWithdrawalState()
        collectDeleteLogoutState()
        setTvHomeSettingWithdrawalClickListeners()
        setTvHomeSettingLogoutClickListeners()
    }

    private fun initAppBar() {
        binding.appbarHomeSetting.initLayout(appBarType = AppBarType.TITLE, title = stringOf(R.string.home_setting_title))
        binding.appbarHomeSetting.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun collectDeleteWithdrawalState() {
        homeSettingViewModel.deleteWithdrawalState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { deleteWithdrawalState ->
            when (deleteWithdrawalState) {
                is UiState.Success -> {
                    navigateToLogin()
                }
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectDeleteLogoutState() {
        homeSettingViewModel.deleteLogoutState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { deleteLogoutState ->
            when (deleteLogoutState) {
                is UiState.Success -> {
                    navigateToLogin()
                }
                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setTvHomeSettingWithdrawalClickListeners() {
        binding.tvHomeSettingWithdrawal.setOnClickListener {
            showWithdrawalBottomSheetDialogFragment()
        }
    }

    private fun setTvHomeSettingLogoutClickListeners() {
        binding.tvHomeSettingLogout.setOnClickListener {
            showLogoutBottomSheetDialogFragment()
        }
    }

    private fun showWithdrawalBottomSheetDialogFragment() {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.WITHDRAWAL,
            clickLeftBtn = homeSettingViewModel::deleteWithdrawal,
        ).show(childFragmentManager, WITHDRAWAL_BOTTOM_SHEET)
    }

    private fun showLogoutBottomSheetDialogFragment() {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.LOGOUT,
            clickLeftBtn = homeSettingViewModel::deleteLogout,
        ).show(childFragmentManager, LOGOUT_BOTTOM_SHEET)
    }

    private fun navigateToLogin() {
        findNavController().navigate(com.dongguk.telepigeon.feature.R.id.action_home_setting_to_login)
    }

    companion object {
        private const val WITHDRAWAL_BOTTOM_SHEET = "withdrawalBottomSheet"
        private const val LOGOUT_BOTTOM_SHEET = "logoutBottomSheer"
    }
}
