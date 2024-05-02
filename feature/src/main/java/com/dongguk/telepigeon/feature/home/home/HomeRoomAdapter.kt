package com.dongguk.telepigeon.feature.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.HomeRoomEntity
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class HomeRoomAdapter : ListAdapter<HomeRoomEntity, HomeRoomViewHolder>(
    ItemDiffCallback<HomeRoomEntity>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeRoomViewHolder =
        HomeRoomViewHolder(
            binding = ItemHomeRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context = parent.context,
        )

    override fun onBindViewHolder(
        holder: HomeRoomViewHolder,
        position: Int,
    ) {
        holder.onBind(homeRoomEntity = currentList[position])
    }
}
