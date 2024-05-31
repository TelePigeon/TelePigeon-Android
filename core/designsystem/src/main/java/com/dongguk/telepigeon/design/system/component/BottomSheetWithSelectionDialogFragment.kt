package com.dongguk.telepigeon.design.system.component

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.dongguk.telepigeon.core.design.system.databinding.DialogBottomSheetWithSelectionBinding
import com.dongguk.telepigeon.design.system.type.BottomSheetWithSelectionType
import com.dongguk.telpigeon.core.ui.base.BindingBottomSheetDialogFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf

class BottomSheetWithSelectionDialogFragment(
    private val bottomSheetWithSelectionType: BottomSheetWithSelectionType,
    private val selectionList: List<String>,
    private val bottomSheetWithSelectionAdapter: BottomSheetWithSelectionAdapter,
    private val clickBtn: () -> Unit = {},
    private val onDialogClosed: () -> Unit = {},
) : BindingBottomSheetDialogFragment<DialogBottomSheetWithSelectionBinding>({ DialogBottomSheetWithSelectionBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        setBtnBottomSheetWithSelectionBtnClickListeners()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogClosed()
    }

    private fun initLayout() {
        with(binding) {
            tvBottomSheetWithSelectionTitle.text = stringOf(bottomSheetWithSelectionType.title)
            btnBottomSheetWithSelectionBtn.text = stringOf(bottomSheetWithSelectionType.btnText)
            rvBottomSheetWithSelection.adapter = bottomSheetWithSelectionAdapter
            bottomSheetWithSelectionAdapter.submitList(selectionList)
        }
    }

    private fun setBtnBottomSheetWithSelectionBtnClickListeners() {
        binding.btnBottomSheetWithSelectionBtn.setOnClickListener {
            clickBtn()
            dismiss()
        }
    }
}
