package com.dongguk.telepigeon.feature.home.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.domain.model.HomeRoomEntity
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding

class HomeRoomViewHolder(private val binding: ItemHomeRoomBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(homeRoomEntity: HomeRoomEntity) {
        with(binding) {
            ivHomeRoomEmotion.setImageDrawable(context.getDrawable(homeRoomEntity.emotion))
            tvHomeRoomName.text = homeRoomEntity.name
            tvHomeRoomRelation.text = homeRoomEntity.relation
            tvHomeRoomAnswerDescription.text = context.getString(homeRoomEntity.answerDescription)
        }
    }
}
