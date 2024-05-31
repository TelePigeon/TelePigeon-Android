package com.dongguk.telepigeon.design.system.component

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.core.design.system.databinding.DialogBottomSheetTimeBinding
import com.dongguk.telepigeon.design.system.type.TimeType
import com.dongguk.telpigeon.core.ui.base.BindingBottomSheetDialogFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.google.android.material.chip.Chip

class BottomSheetTimeDialogFragment(
    private val clickBtn: () -> Unit = {},
    private val onDialogClosed: (String) -> Unit = {},
) :BindingBottomSheetDialogFragment<DialogBottomSheetTimeBinding>({ DialogBottomSheetTimeBinding.inflate(it)}) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setBtnBottomSheetWithOneBtnClickListeners()
        setTimeChip()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogClosed(getSelectedTimeChip())
    }

    private fun setBtnBottomSheetWithOneBtnClickListeners() {
        binding.btnBottomSheetTimeBtn.setOnClickListener {
            clickBtn()
            dismiss()
        }
    }

    private fun setTimeChip() {
        binding.cgBottomSheetTime.removeAllViews()

        for (time in TimeType.entries) {
            (layoutInflater.inflate(R.layout.view_keyword_chip, binding.cgBottomSheetTime, false) as Chip).run {
                text = stringOf(time.timeRes)
                binding.cgBottomSheetTime.addView(this)
                isChecked = false
            }
        }
    }

    private fun getSelectedTimeChip(): String {
        var times = TIME

        with(binding.cgBottomSheetTime) {
            for (index in 0..childCount) {
                ((getChildAt(index)) as? Chip)?.let { chip ->
                    if (chip.isChecked) {
                        times += (if (times.length > TIME.length) TIME_SPACE else "") + chip.text.toString()
                    }
                }
            }
        }

        return times
    }

    companion object {
        const val TIME = "매일 "
        const val TIME_SPACE = ", "
    }
}