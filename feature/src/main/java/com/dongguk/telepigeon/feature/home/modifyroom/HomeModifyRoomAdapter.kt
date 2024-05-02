package com.dongguk.telepigeon.feature.home.modifyroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.HomeModifyRoomEntity
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class HomeModifyRoomAdapter : ListAdapter<HomeModifyRoomEntity, HomeModifyRoomViewHolder>(
    ItemDiffCallback<HomeModifyRoomEntity>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeModifyRoomViewHolder = HomeModifyRoomViewHolder(binding = ItemHomeRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false), context = parent.context)

    override fun onBindViewHolder(holder: HomeModifyRoomViewHolder, position: Int) {
        holder.onBind(homeModifyRoomEntity = currentList[position])
    }
}