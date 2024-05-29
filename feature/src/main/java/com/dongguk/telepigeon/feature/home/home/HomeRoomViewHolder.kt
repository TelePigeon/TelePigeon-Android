package com.dongguk.telepigeon.feature.home.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding

class HomeRoomViewHolder(private val binding: ItemHomeRoomBinding, private val context: Context, private val navigateToMain: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var homeRoomModel: HomeRoomModel

    init {
        binding.root.setOnClickListener {
            navigateToMain(homeRoomModel.id)
        }
    }

    fun onBind(homeRoomModel: HomeRoomModel) {
        this.homeRoomModel = homeRoomModel
        with(binding) {
            ivHomeRoomEmotion.setImageResource(homeRoomModel.emotion)
            tvHomeRoomName.text = homeRoomModel.name
            tvHomeRoomRelation.text = homeRoomModel.relation
            tvHomeRoomAnswerDescription.text = context.getString(homeRoomModel.answerDescription)
        }
    }
}
