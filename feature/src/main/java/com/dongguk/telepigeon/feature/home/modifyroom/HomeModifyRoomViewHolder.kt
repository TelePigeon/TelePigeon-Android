package com.dongguk.telepigeon.feature.home.modifyroom

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.domain.model.HomeModifyRoomEntity
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding

class HomeModifyRoomViewHolder(private val binding: ItemHomeRoomBinding, private val context: Context): RecyclerView.ViewHolder(binding.root){
    fun onBind(homeModifyRoomEntity: HomeModifyRoomEntity) {
        with(binding) {
            ivHomeRoomEmotion.setImageDrawable(context.getDrawable(homeModifyRoomEntity.emotion))
            tvHomeRoomName.text = homeModifyRoomEntity.name
            tvHomeRoomRelation.text = homeModifyRoomEntity.relation
            tvHomeRoomAnswerDescription.text = context.getString(homeModifyRoomEntity.answerDescription)
        }
    }
}