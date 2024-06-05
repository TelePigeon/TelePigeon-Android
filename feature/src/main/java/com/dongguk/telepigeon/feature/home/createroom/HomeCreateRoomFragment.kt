package com.dongguk.telepigeon.feature.home.createroom

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithOneBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithOneBtnType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeCreateRoomBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeCreateRoomFragment : BindingFragment<FragmentHomeCreateRoomBinding>({ FragmentHomeCreateRoomBinding.inflate(it) }) {
    private val homeCreateRoomViewModel by viewModels<HomeCreateRoomViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        collectPostRoomState()
        setBtnHomeCreateRoomClickListener()
        setEtHomeCreateRoomCodeTextChangedListener()
    }

    private fun initAppBar() {
        binding.appbarHomeCreateRoom.initLayout(appBarType = AppBarType.X)
        binding.appbarHomeCreateRoom.binding.ivAppBarTelepigeonX.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun collectPostRoomState() {
        homeCreateRoomViewModel.postRoomState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { postRoomState ->
            when (postRoomState) {
                is UiState.Success -> {
                    showCreateRoomBottomSheerDialogFragment()
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBtnHomeCreateRoomClickListener() {
        binding.btnHomeCreateRoom.setOnClickListener {
            homeCreateRoomViewModel.postRoom(name = binding.etHomeCreateRoomCode.editText.text.toString())
        }
    }

    private fun setEtHomeCreateRoomCodeTextChangedListener() {
        binding.etHomeCreateRoomCode.setOnTextChangedListener { createRoomCode ->
            binding.btnHomeCreateRoom.isEnabled = createRoomCode.isNotBlank()
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
