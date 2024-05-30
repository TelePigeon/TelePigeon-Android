package com.dongguk.telepigeon.feature.setting.worrysetting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.feature.databinding.ItemWorrySettingBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class WorrySettingAdapter(
    private val showDeleteWorryBottomSheetDialogFragment: (Int) -> Unit,
) : ListAdapter<RoomWorryModel, WorrySettingViewHolder>(
    ItemDiffCallback<RoomWorryModel>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new },
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorrySettingViewHolder = WorrySettingViewHolder(
        binding = ItemWorrySettingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        showDeleteWorryBottomSheetDialogFragment = showDeleteWorryBottomSheetDialogFragment
    )

    override fun onBindViewHolder(holder: WorrySettingViewHolder, position: Int) {
        holder.onBind(roomWorryModel = currentList[position])
    }
}