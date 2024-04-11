package com.dongguk.telepigeon.design.system.component

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.dongguk.telepigeon.core.design.system.databinding.DialogBottomSheetWithOneBtnBinding
import com.dongguk.telpigeon.core.ui.base.BindingBottomSheetDialogFragment

class BottomSheetWithOneBtnDialogFragment(
    private val interjection: String,
    private val sentence: String,
    private val btnText: String,
    private val clickBtn: () -> Unit = {},
    private val onDialogClosed: () -> Unit = {}
) : BindingBottomSheetDialogFragment<DialogBottomSheetWithOneBtnBinding>({ DialogBottomSheetWithOneBtnBinding.inflate(it) }) {
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
            tvBottomSheetWithOneBtnInterjection.text = interjection
            tvBottomSheetWithOneBtnSentence.text = sentence
            btnBottomSheetWithOneBtn.text = btnText
        }
    }

    private fun addListeners() {
        binding.btnBottomSheetWithOneBtn.setOnClickListener {
            clickBtn()
            dismiss()
        }
    }
}