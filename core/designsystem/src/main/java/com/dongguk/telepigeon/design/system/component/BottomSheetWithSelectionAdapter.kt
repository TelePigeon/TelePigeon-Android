package com.dongguk.telepigeon.design.system.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.core.design.system.databinding.ItemDialogBottomSheetWithSelectionBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class BottomSheetWithSelectionAdapter : ListAdapter<String, BottomSheetWithSelectionViewHolder>(
    ItemDiffCallback<String>(
        onItemsTheSame = { old, new -> old.length == new.length },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    private var selectedItemPosition: Int = DEFAULT_OLD_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BottomSheetWithSelectionViewHolder =
        BottomSheetWithSelectionViewHolder(
            binding = ItemDialogBottomSheetWithSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )

    override fun onBindViewHolder(
        holder: BottomSheetWithSelectionViewHolder,
        position: Int,
    ) {
        if (position == selectedItemPosition) {
            holder.onSelectedItemBind(title = currentList[position])
        } else {
            holder.onUnselectedItemBind(title = currentList[position])
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedItemPosition)
            selectedItemPosition = holder.adapterPosition
            notifyItemChanged(selectedItemPosition)
        }
    }

    companion object {
        private const val DEFAULT_OLD_POSITION = -1
    }
}
