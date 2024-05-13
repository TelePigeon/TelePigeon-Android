package com.dongguk.telepigeon.feature.home.modifyroom

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.domain.model.HomeRoomEntity
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding
import com.dongguk.telpigeon.core.ui.util.context.colorOf
import com.dongguk.telpigeon.core.ui.util.view.setBackgroundTint

class HomeModifyRoomViewHolder(private val binding: ItemHomeRoomBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun onSelectedItemBind(homeRoomEntity: HomeRoomEntity) {
        with(binding) {
            ivHomeRoomEmotion.setImageResource(homeRoomEntity.emotion)
            tvHomeRoomName.text = homeRoomEntity.name
            tvHomeRoomRelation.text = homeRoomEntity.relation
            tvHomeRoomAnswerDescription.text = context.getString(homeRoomEntity.answerDescription)
            tvHomeRoomName.setTextColor(context.colorOf(R.color.white))
            tvHomeRoomRelation.setTextColor(context.colorOf(R.color.g_02))
            tvHomeRoomAnswerDescription.setTextColor(context.colorOf(R.color.g_02))
            layoutHomeRoom.setBackgroundTint(R.color.monstera)
        }
    }

    fun onUnselectedItemBind(homeRoomEntity: HomeRoomEntity) {
        with(binding) {
            ivHomeRoomEmotion.setImageResource(homeRoomEntity.emotion)
            tvHomeRoomName.text = homeRoomEntity.name
            tvHomeRoomRelation.text = homeRoomEntity.relation
            tvHomeRoomAnswerDescription.text = context.getString(homeRoomEntity.answerDescription)
            tvHomeRoomName.setTextColor(context.colorOf(R.color.g_10))
            tvHomeRoomRelation.setTextColor(context.colorOf(R.color.g_06))
            tvHomeRoomAnswerDescription.setTextColor(context.colorOf(R.color.g_09))
            layoutHomeRoom.setBackgroundTint(R.color.marble)
        }
    }
}
