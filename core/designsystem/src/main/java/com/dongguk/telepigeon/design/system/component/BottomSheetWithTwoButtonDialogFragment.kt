package com.dongguk.telepigeon.design.system.component

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.dongguk.telepigeon.core.design.system.databinding.DialogBottomSheetWithTwoBtnBinding
import com.dongguk.telpigeon.core.ui.base.BindingBottomSheetDialogFragment

class BottomSheetWithTwoButtonDialogFragment(
    private val interjection: String,
    private val sentence: String,
    private val leftBtnText: String,
    private val rightBtnText: String,
    private val clickLeftBtn: () -> Unit = {},
    private val clickRightBtn: () -> Unit = {},
    private val onDialogClosed: () -> Unit = {}
) : BindingBottomSheetDialogFragment<DialogBottomSheetWithTwoBtnBinding>({ DialogBottomSheetWithTwoBtnBinding.inflate(it) }) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        addListeners()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogClosed()
    }

    private fun initLayout() {
        with(binding) {
            tvBottomSheetWithTwoButtonInterjection.text = interjection
            tvBottomSheetWithTwoButtonSentence.text = sentence
            btnBottomSheetWithTwoButtonLeft.text = leftBtnText
            btnBottomSheetWithTwoButtonRight.text = rightBtnText
        }
    }

    private fun addListeners() {
        with(binding) {
            btnBottomSheetWithTwoButtonLeft.setOnClickListener {
                clickLeftBtn()
                dismiss()
            }

            btnBottomSheetWithTwoButtonRight.setOnClickListener {
                clickRightBtn()
                dismiss()
            }
        }
    }
}