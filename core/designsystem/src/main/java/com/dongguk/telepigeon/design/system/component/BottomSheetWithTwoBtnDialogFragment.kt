package com.dongguk.telepigeon.design.system.component

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.dongguk.telepigeon.core.design.system.databinding.DialogBottomSheetWithTwoBtnBinding
import com.dongguk.telpigeon.core.ui.base.BindingBottomSheetDialogFragment

class BottomSheetWithTwoBtnDialogFragment(
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
            tvBottomSheetWithTwoBtnInterjection.text = interjection
            tvBottomSheetWithTwoBtnSentence.text = sentence
            btnBottomSheetWithTwoBtnLeft.text = leftBtnText
            btnBottomSheetWithTwoBtnRight.text = rightBtnText
        }
    }

    private fun addListeners() {
        with(binding) {
            btnBottomSheetWithTwoBtnLeft.setOnClickListener {
                clickLeftBtn()
                dismiss()
            }

            btnBottomSheetWithTwoBtnRight.setOnClickListener {
                clickRightBtn()
                dismiss()
            }
        }
    }
}
