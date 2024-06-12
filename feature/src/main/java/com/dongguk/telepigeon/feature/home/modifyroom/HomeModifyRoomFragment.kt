package com.dongguk.telepigeon.feature.home.modifyroom

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithTwoBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithTwoBtnType
import com.dongguk.telepigeon.feature.databinding.FragmentHomeModifyRoomBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeModifyRoomFragment : BindingFragment<FragmentHomeModifyRoomBinding>({ FragmentHomeModifyRoomBinding.inflate(it) }) {
    private val homeModifyRoomViewModel by viewModels<HomeModifyRoomViewModel>()
    private lateinit var homeModifyRoomAdapter: HomeModifyRoomAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        homeModifyRoomViewModel.getRooms()
        initAppBar()
        initAdapter()
        collectGetRoomsState()
        collectDeleteRoomState()
        collectSelectedItemPosition()
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
    }

    private fun collectGetRoomsState() {
        homeModifyRoomViewModel.getRoomsState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getRoomState ->
            when (getRoomState) {
                is UiState.Success -> {
                    homeModifyRoomAdapter.submitList(getRoomState.data)
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectDeleteRoomState() {
        homeModifyRoomViewModel.deleteRoomState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { deleteRoomState ->
            when (deleteRoomState) {
                is UiState.Success -> {
                    findNavController().popBackStack()
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectSelectedItemPosition() {
        homeModifyRoomAdapter.selectedItemPosition.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { selectedItemPosition ->
            binding.btnHomeModifyRoom.isEnabled = selectedItemPosition != DEFAULT_OLD_POSITION
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBtnHomeModifyRoomClickListener() {
        binding.btnHomeModifyRoom.setOnClickListener {
            showDeleteRoomBottomSheetDialogFragment()
        }
    }

    private fun showDeleteRoomBottomSheetDialogFragment() {
        BottomSheetWithTwoBtnDialogFragment(
            bottomSheetWithTwoBtnType = BottomSheetWithTwoBtnType.DELETE_ROOM,
            clickRightBtn = {
                homeModifyRoomViewModel.deleteRoom(
                    roomId =
                        with(homeModifyRoomAdapter) {
                            currentList[selectedItemPosition.value].id
                        },
                )
            },
        ).show(childFragmentManager, DELETE_ROOM_BOTTOM_SHEET)
    }

    companion object {
        private const val DELETE_ROOM_BOTTOM_SHEET = "deleteRoomBottomSheet"
        private const val DEFAULT_OLD_POSITION = -1
    }
}
