package com.dongguk.telepigeon.feature.home.createroom

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithOneBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithOneBtnType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeCreateRoomBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class HomeCreateRoomFragment : BindingFragment<FragmentHomeCreateRoomBinding>({ FragmentHomeCreateRoomBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        setBtnHomeCreateRoomClickListener()
    }

    private fun initAppBar() {
        binding.appbarHomeCreateRoom.initLayout(appBarType = AppBarType.X)
        binding.appbarHomeCreateRoom.binding.ivAppBarTelepigeonX.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setBtnHomeCreateRoomClickListener() {
        binding.btnHomeCreateRoom.setOnClickListener {
            showCreateRoomBottomSheerDialogFragment()
        }
    }

    private fun showCreateRoomBottomSheerDialogFragment() {
        BottomSheetWithOneBtnDialogFragment(
            bottomSheetWithOneBtnType = BottomSheetWithOneBtnType.CREATE_ROOM,
            clickBtn = { findNavController().popBackStack(R.id.menu_home, false) },
        ).show(childFragmentManager, CREATE_ROOM_BOTTOM_SHEET)
    }

    companion object {
        private const val CREATE_ROOM_BOTTOM_SHEET = "createRoomBottomSheet"
    }
}
