package com.dongguk.telepigeon.design.system.component

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.core.design.system.databinding.ItemDialogBottomSheetWithSelectionBinding

class BottomSheetWithSelectionViewHolder(private val binding: ItemDialogBottomSheetWithSelectionBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onSelectedItemBind(title: String) {
        with(binding) {
            tvDialogBottomSheetWithSelection.text = title
            ivDialogBottomSheetWithSelectionCheck.visibility = View.VISIBLE
        }
    }

    fun onUnselectedItemBind(title: String) {
        with(binding) {
            tvDialogBottomSheetWithSelection.text = title
            ivDialogBottomSheetWithSelectionCheck.visibility = View.INVISIBLE
        }
    }
}
