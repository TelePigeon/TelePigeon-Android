package com.dongguk.telepigeon.feature.home.modifyroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class HomeModifyRoomAdapter : ListAdapter<HomeRoomModel, HomeModifyRoomViewHolder>(
    ItemDiffCallback<HomeRoomModel>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    private var _selectedItemPosition: Int = DEFAULT_OLD_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeModifyRoomViewHolder = HomeModifyRoomViewHolder(binding = ItemHomeRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false), context = parent.context)

    override fun onBindViewHolder(
        holder: HomeModifyRoomViewHolder,
        position: Int,
    ) {
        if (position == _selectedItemPosition) {
            holder.onSelectedItemBind(homeRoomModel = currentList[position])
        } else {
            holder.onUnselectedItemBind(homeRoomModel = currentList[position])
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(_selectedItemPosition)
            _selectedItemPosition = holder.adapterPosition
            notifyItemChanged(_selectedItemPosition)
        }
    }

    companion object {
        private const val DEFAULT_OLD_POSITION = -1
    }
}
