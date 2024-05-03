package com.dongguk.telepigeon.feature.home.addroom

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeAddRoomBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf

class HomeAddRoomFragment : BindingFragment<FragmentHomeAddRoomBinding>({ FragmentHomeAddRoomBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        setTvHomeAddRoomEnterRoomClickListeners()
        setTvHomeAddRoomCreateRoomClickListeners()
    }

    private fun initAppBar() {
        binding.appbarHomeAddRoom.initLayout(appBarType = AppBarType.TITLE, title = stringOf(com.dongguk.telepigeon.core.design.system.R.string.home_add_room))
        binding.appbarHomeAddRoom.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setTvHomeAddRoomEnterRoomClickListeners() {
        binding.tvHomeAddRoomEnterRoom.setOnClickListener {
            navigateToHomeEnterRoom()
        }
    }

    private fun setTvHomeAddRoomCreateRoomClickListeners() {
        binding.tvHomeAddRoomCreateRoom.setOnClickListener {
            navigateToHomeCreateRoom()
        }
    }

    private fun navigateToHomeEnterRoom() {
        findNavController().navigate(R.id.action_home_add_room_to_home_enter_room)
    }

    private fun navigateToHomeCreateRoom() {
        findNavController().navigate(R.id.action_home_add_room_to_home_create_room)
    }
}
