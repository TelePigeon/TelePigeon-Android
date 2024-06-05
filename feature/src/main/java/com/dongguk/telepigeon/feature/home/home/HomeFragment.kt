package com.dongguk.telepigeon.feature.home.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var homeRoomAdapter: HomeRoomAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getRooms()
        initAdapter()
        collectGetRoomsState()
        setIvHomeSettingClickListener()
        setTvHomeAddRoomClickListener()
        setTvHomeModifyClickListener()
    }

    override fun onDestroyView() {
        binding.rvHomeRoom.adapter = null
        super.onDestroyView()
    }

    private fun initAdapter() {
        homeRoomAdapter = HomeRoomAdapter(navigateToMain = ::navigateToMain)
        binding.rvHomeRoom.adapter = homeRoomAdapter
    }

    private fun collectGetRoomsState() {
        homeViewModel.getRoomsState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getRoomState ->
            when (getRoomState) {
                is UiState.Success -> {
                    (getRoomState.data.isNotEmpty()).let { homeRoomVisibility ->
                        with(binding) {
                            ivHomeRoomEmpty.visibility = if (homeRoomVisibility) View.GONE else View.VISIBLE
                            tvHomeRoomEmpty.visibility = if (homeRoomVisibility) View.GONE else View.VISIBLE
                        }
                    }
                    homeRoomAdapter.submitList(getRoomState.data)
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setIvHomeSettingClickListener() {
        binding.ivHomeSetting.setOnClickListener {
            navigateToHomeSetting()
        }
    }

    private fun setTvHomeAddRoomClickListener() {
        binding.tvHomeAddRoom.setOnClickListener {
            navigateToHomeAddRoom()
        }
    }

    private fun setTvHomeModifyClickListener() {
        binding.tvHomeModify.setOnClickListener {
            navigateToHomeModifyRoom()
        }
    }

    private fun navigateToMain(roomId: Int) {
        findNavController().navigate(R.id.action_home_to_main, bundleOf(ROOM_ID to roomId))
    }

    private fun navigateToHomeSetting() {
        findNavController().navigate(R.id.action_home_to_home_setting)
    }

    private fun navigateToHomeAddRoom() {
        findNavController().navigate(R.id.action_home_to_home_add_room)
    }

    private fun navigateToHomeModifyRoom() {
        findNavController().navigate(R.id.action_home_to_home_modify_room)
    }

    companion object {
        const val ROOM_ID = "roomId"
    }
}
