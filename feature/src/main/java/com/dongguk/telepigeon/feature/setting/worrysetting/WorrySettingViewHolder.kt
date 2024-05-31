package com.dongguk.telepigeon.feature.setting.worrysetting

import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.feature.databinding.ItemWorrySettingBinding

class WorrySettingViewHolder(
    private val binding: ItemWorrySettingBinding,
    private val showDeleteWorryBottomSheetDialogFragment: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var roomWorryModel: RoomWorryModel

    init {
        binding.ivWorrySettingDelete.setOnClickListener {
            showDeleteWorryBottomSheetDialogFragment(roomWorryModel.id)
        }
    }

    fun onBind(roomWorryModel: RoomWorryModel) {
        this.roomWorryModel = roomWorryModel

        with(binding) {
            tvWorrySettingNameContent.text = roomWorryModel.name
            tvWorrySettingContentContent.text = roomWorryModel.content
            tvWorrySettingTimeContent.text = roomWorryModel.times
        }
    }
}
