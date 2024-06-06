package com.dongguk.telepigeon.feature.setting.addworry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetTimeDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.domain.model.WorryModel
import com.dongguk.telepigeon.feature.databinding.FragmentAddWorryBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.view.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddWorryFragment : BindingFragment<FragmentAddWorryBinding>({ FragmentAddWorryBinding.inflate(it) }) {
    private val addWorryViewModel by viewModels<AddWorryViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        collectPostWorryState()
        setBtnAddWorryAddClickListener()
        setEtAddWorryTimeClickListener()
    }

    private fun collectPostWorryState() {
        addWorryViewModel.postWorryState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { postWorryState ->
            when (postWorryState) {
                is UiState.Success -> {
                    findNavController().popBackStack()
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initLayout() {
        with(binding) {
            appbarAddWorry.initLayout(appBarType = AppBarType.X)
            appbarAddWorry.binding.ivAppBarTelepigeonX.setOnClickListener {
                findNavController().popBackStack()
            }
            etAddWorryTime.editText.isEnabled = false
        }
    }


    private fun setBtnAddWorryAddClickListener() {
        binding.btnAddWorryAdd.setOnClickListener {
            addWorryViewModel.postWorry(worryModel = WorryModel(
                name = binding.etAddWorryName.editText.text.toString(),
                content = binding.etAddWorryContent.editText.text.toString(),
                times = binding.etAddWorryTime.editText.text.toString()
            ))
        }
    }

    private fun setEtAddWorryTimeClickListener() {
        binding.etAddWorryTime.setOnClickListener {
            showTimeBottomSheetDialogFragment()
        }
    }

    private fun showTimeBottomSheetDialogFragment() {
        BottomSheetTimeDialogFragment(
            onDialogClosed = { time -> setEtAddWorryTimeText(time = time) },
        ).show(childFragmentManager, TIME_BOTTOM_SHEET)
    }

    private fun setEtAddWorryTimeText(time: String) {
        binding.etAddWorryTime.editText.setText(time)
    }

    companion object {
        private const val TIME_BOTTOM_SHEET = "timeBottomSheet"
    }
}
