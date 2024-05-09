package com.dongguk.telepigeon.feature.home.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.domain.model.HomeRoomEntity
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding

class HomeRoomViewHolder(private val binding: ItemHomeRoomBinding, private val context: Context, private val navigateToMain: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var homeRoomEntity: HomeRoomEntity

    init {
        binding.root.setOnClickListener {
            navigateToMain(homeRoomEntity.id)
        }
    }

    fun onBind(homeRoomEntity: HomeRoomEntity) {
        this.homeRoomEntity = homeRoomEntity
        with(binding) {
            ivHomeRoomEmotion.setImageDrawable(context.getDrawable(homeRoomEntity.emotion))
            tvHomeRoomName.text = homeRoomEntity.name
            tvHomeRoomRelation.text = homeRoomEntity.relation
            tvHomeRoomAnswerDescription.text = context.getString(homeRoomEntity.answerDescription)
        }
    }
}
