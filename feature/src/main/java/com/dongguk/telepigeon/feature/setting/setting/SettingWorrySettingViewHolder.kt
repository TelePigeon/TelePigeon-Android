package com.dongguk.telepigeon.feature.setting.setting

import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.feature.databinding.ItemSettingWorrySettingBinding

class SettingWorrySettingViewHolder(private val binding: ItemSettingWorrySettingBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(roomWorryModel: RoomWorryModel) {
        with(binding) {
            tvSettingWorrySettingNameContent.text = roomWorryModel.name
            tvSettingWorrySettingContentContent.text = roomWorryModel.content
            tvSettingWorrySettingTimeContent.text = roomWorryModel.times
        }
    }
}