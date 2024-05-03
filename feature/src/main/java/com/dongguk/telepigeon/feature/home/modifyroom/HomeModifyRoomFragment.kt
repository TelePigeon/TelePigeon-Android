package com.dongguk.telepigeon.feature.home.modifyroom

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithTwoBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithTwoBtnType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeModifyRoomBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf

class HomeModifyRoomFragment : BindingFragment<FragmentHomeModifyRoomBinding>({ FragmentHomeModifyRoomBinding.inflate(it) }) {
    private val homeModifyRoomViewModel by viewModels<HomeModifyRoomViewModel>()
    private lateinit var homeModifyRoomAdapter: HomeModifyRoomAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        initAdapter()
        setBtnHomeModifyRoomClickListener()
    }

    override fun onDestroyView() {
        binding.rvHomeModifyRoom.adapter = null
        super.onDestroyView()
    }

    private fun initAppBar() {
        binding.appbarHomeModifyRoom.initLayout(appBarType = AppBarType.TITLE, title = stringOf(com.dongguk.telepigeon.core.design.system.R.string.home_modify))
        binding.appbarHomeModifyRoom.binding.ivAppBarTelepigeonArrowLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        homeModifyRoomAdapter = HomeModifyRoomAdapter()
        binding.rvHomeModifyRoom.adapter = homeModifyRoomAdapter

        homeModifyRoomAdapter.submitList(homeModifyRoomViewModel.dummyHomeModifyRoom)
    }

    private fun setBtnHomeModifyRoomClickListener() {
        binding.btnHomeModifyRoom.setOnClickListener {
            showDeleteRoomBottomSheetDialogFragment()
        }
    }

    private fun showDeleteRoomBottomSheetDialogFragment() {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.DELETE_ROOM,
            clickLeftBtn = { findNavController().popBackStack(R.id.menu_home, false) },
        ).show(childFragmentManager, DELETE_ROOM_BOTTOM_SHEET)
    }

    companion object {
        private const val DELETE_ROOM_BOTTOM_SHEET = "deleteRoomBottomSheet"
    }
}
