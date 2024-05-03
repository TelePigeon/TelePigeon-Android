package com.dongguk.telepigeon.design.system.component

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.dongguk.telepigeon.core.design.system.databinding.DialogBottomSheetWithOneBtnBinding
import com.dongguk.telepigeon.design.system.type.BottomSheetWithOneBtnType
import com.dongguk.telpigeon.core.ui.base.BindingBottomSheetDialogFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf

class BottomSheetWithOneBtnDialogFragment(
    private val bottomSheetWithOneBtnType: BottomSheetWithOneBtnType,
    private val clickBtn: () -> Unit = {},
    private val onDialogClosed: () -> Unit = {},
) : BindingBottomSheetDialogFragment<DialogBottomSheetWithOneBtnBinding>({ DialogBottomSheetWithOneBtnBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setBtnBottomSheetWithOneBtnClickListeners()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogClosed()
    }

    private fun initLayout() {
        with(binding) {
            tvBottomSheetWithOneBtnInterjection.text = stringOf(bottomSheetWithOneBtnType.interjection)
            tvBottomSheetWithOneBtnSentence.text = stringOf(bottomSheetWithOneBtnType.sentence)
            btnBottomSheetWithOneBtn.text = stringOf(bottomSheetWithOneBtnType.btnText)
        }
    }

    private fun setBtnBottomSheetWithOneBtnClickListeners() {
        binding.btnBottomSheetWithOneBtn.setOnClickListener {
            clickBtn()
            dismiss()
        }
    }
}
