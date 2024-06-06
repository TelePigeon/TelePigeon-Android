package com.dongguk.telepigeon.feature.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class HomeRoomAdapter(
    private val navigateToMain: () -> Unit,
    private val setRoomId: (Int) -> Unit
) : ListAdapter<HomeRoomModel, HomeRoomViewHolder>(
        ItemDiffCallback<HomeRoomModel>(
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
            navigateToMain = navigateToMain,
            setRoomId = setRoomId
        )

    override fun onBindViewHolder(
        holder: HomeRoomViewHolder,
        position: Int,
    ) {
        holder.onBind(homeRoomModel = currentList[position])
    }
}
