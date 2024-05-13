package com.dongguk.telepigeon.feature.home.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var homeRoomAdapter: HomeRoomAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
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

        // TODO 서버통신 구현 후 collectData 함수로 해당 로직 이동
        (homeViewModel.dummyHomeRoom.isNotEmpty()).let { homeRoomVisibility ->
            with(binding) {
                ivHomeRoomEmpty.visibility = if (homeRoomVisibility) View.GONE else View.VISIBLE
                tvHomeRoomEmpty.visibility = if (homeRoomVisibility) View.GONE else View.VISIBLE
            }
            homeRoomAdapter.submitList(homeViewModel.dummyHomeRoom)
        }
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
