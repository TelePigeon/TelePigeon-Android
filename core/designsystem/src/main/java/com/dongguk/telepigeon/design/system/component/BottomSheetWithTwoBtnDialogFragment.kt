package com.dongguk.telepigeon.design.system.component

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.dongguk.telepigeon.core.design.system.databinding.DialogBottomSheetWithTwoBtnBinding
import com.dongguk.telepigeon.design.system.type.BottomSheetWithTwoBtnType
import com.dongguk.telpigeon.core.ui.base.BindingBottomSheetDialogFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf

class BottomSheetWithTwoBtnDialogFragment(
    private val bottomSheetWithTwoBtnType: BottomSheetWithTwoBtnType,
    private val clickLeftBtn: () -> Unit = {},
    private val clickRightBtn: () -> Unit = {},
    private val onDialogClosed: () -> Unit = {},
) : BindingBottomSheetDialogFragment<DialogBottomSheetWithTwoBtnBinding>({ DialogBottomSheetWithTwoBtnBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setBtnBottomSheetWithTwoBtnLeftClickListeners()
        setBtnBottomSheetWithTwoBtnRightClickListeners()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogClosed()
    }

    private fun initLayout() {
        with(binding) {
            tvBottomSheetWithTwoBtnInterjection.text = stringOf(bottomSheetWithTwoBtnType.interjection)
            tvBottomSheetWithTwoBtnSentence.text = stringOf(bottomSheetWithTwoBtnType.sentence)
            btnBottomSheetWithTwoBtnLeft.text = stringOf(bottomSheetWithTwoBtnType.leftBtnText)
            btnBottomSheetWithTwoBtnRight.text = stringOf(bottomSheetWithTwoBtnType.rightBtnText)
        }
    }

    private fun setBtnBottomSheetWithTwoBtnLeftClickListeners() {
        binding.btnBottomSheetWithTwoBtnLeft.setOnClickListener {
            clickLeftBtn()
            dismiss()
        }
    }

    private fun setBtnBottomSheetWithTwoBtnRightClickListeners() {
        binding.btnBottomSheetWithTwoBtnRight.setOnClickListener {
            clickRightBtn()
            dismiss()
        }
    }
}
