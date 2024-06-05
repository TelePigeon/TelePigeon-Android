package com.dongguk.telepigeon.feature.home.modifyroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.feature.databinding.ItemHomeRoomBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeModifyRoomAdapter : ListAdapter<HomeRoomModel, HomeModifyRoomViewHolder>(
    ItemDiffCallback<HomeRoomModel>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    private var _selectedItemPosition = MutableStateFlow(DEFAULT_OLD_POSITION)
    val selectedItemPosition get() = _selectedItemPosition.asStateFlow()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeModifyRoomViewHolder = HomeModifyRoomViewHolder(binding = ItemHomeRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false), context = parent.context)

    override fun onBindViewHolder(
        holder: HomeModifyRoomViewHolder,
        position: Int,
    ) {
        if (position == _selectedItemPosition.value) {
            holder.onSelectedItemBind(homeRoomModel = currentList[position])
        } else {
            holder.onUnselectedItemBind(homeRoomModel = currentList[position])
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(_selectedItemPosition.value)
            _selectedItemPosition.value = holder.adapterPosition
            notifyItemChanged(_selectedItemPosition.value)
        }
    }

    companion object {
        private const val DEFAULT_OLD_POSITION = -1
    }
}
