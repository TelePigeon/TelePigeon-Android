package com.dongguk.telepigeon.feature.setting.addworry

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetTimeDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.feature.databinding.FragmentAddWorryBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class AddWorryFragment : BindingFragment<FragmentAddWorryBinding>({ FragmentAddWorryBinding.inflate(it) }) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setEtAddWorryTimeClickListener()
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

    private fun setEtAddWorryTimeClickListener() {
        binding.etAddWorryTime.setOnClickListener {
            showTimeBottomSheetDialogFragment()
        }
    }

    private fun showTimeBottomSheetDialogFragment() {
        BottomSheetTimeDialogFragment(
            onDialogClosed = {time -> setEtAddWorryTimeText(time = time)}
        ).show(childFragmentManager, TIME_BOTTOM_SHEET)
    }

    private fun setEtAddWorryTimeText(time: String) {
        binding.etAddWorryTime.editText.setText(time)
    }

    companion object {
        private const val TIME_BOTTOM_SHEET = "timeBottomSheet"
    }
}