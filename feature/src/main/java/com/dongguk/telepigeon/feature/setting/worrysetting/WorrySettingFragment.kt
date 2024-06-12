package com.dongguk.telepigeon.feature.setting.worrysetting

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
import com.dongguk.telepigeon.feature.databinding.FragmentWorrySettingBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class WorrySettingFragment : BindingFragment<FragmentWorrySettingBinding>({ FragmentWorrySettingBinding.inflate(it) }) {
    private val worrySettingViewModel by viewModels<WorrySettingViewModel>()
    private lateinit var worrySettingAdapter: WorrySettingAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        worrySettingViewModel.getWorries()
        initAdapter()
        initLayout()
        collectGetWorriesState()
        collectDeleteWorryState()
        setBtnWorrySettingAddClickListener()
    }

    private fun initAdapter() {
        worrySettingAdapter = WorrySettingAdapter(showDeleteWorryBottomSheetDialogFragment = ::showDeleteWorryBottomSheetDialogFragment)
        binding.rvWorrySetting.adapter = worrySettingAdapter
    }

    private fun initLayout() {
        with(binding) {
            appbarWorrySetting.initLayout(appBarType = AppBarType.TITLE, title = stringOf(R.string.setting_worry_setting_title))
            appbarWorrySetting.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun collectGetWorriesState() {
        worrySettingViewModel.getWorriesState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getWorriesState ->
            when (getWorriesState) {
                is UiState.Success -> {
                    worrySettingAdapter.submitList(getWorriesState.data)
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectDeleteWorryState() {
        worrySettingViewModel.deleteWorryState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { deleteWorryState ->
            when (deleteWorryState) {
                is UiState.Success -> {
                    worrySettingViewModel.getWorries()
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBtnWorrySettingAddClickListener() {
        binding.btnWorrySettingAdd.setOnClickListener {
            navigateToAddWorry()
        }
    }

    private fun showDeleteWorryBottomSheetDialogFragment(worryId: Int) {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.DELETE_WORRY,
            clickRightBtn = { worrySettingViewModel.deleteWorry(worryId = worryId) },
        ).show(childFragmentManager, DELETE_WORRY_BOTTOM_SHEET)
    }

    private fun navigateToAddWorry() {
        findNavController().navigate(com.dongguk.telepigeon.feature.R.id.action_worry_setting_to_add_worry)
    }

    companion object {
        private const val DELETE_WORRY_BOTTOM_SHEET = "deleteWorryBottomSheet"
    }
}
