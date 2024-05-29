package com.dongguk.telepigeon.feature.setting.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.feature.databinding.ItemSettingWorrySettingBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class SettingWorrySettingAdapter : ListAdapter<RoomWorryModel, SettingWorrySettingViewHolder>(
    ItemDiffCallback<RoomWorryModel>(
        onItemsTheSame = { old, new -> old.name == new.name },
        onContentsTheSame = { old, new -> old == new },
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingWorrySettingViewHolder = SettingWorrySettingViewHolder(
        binding = ItemSettingWorrySettingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun onBindViewHolder(holder: SettingWorrySettingViewHolder, position: Int) {
        holder.onBind(roomWorryModel = currentList[position])
    }
}