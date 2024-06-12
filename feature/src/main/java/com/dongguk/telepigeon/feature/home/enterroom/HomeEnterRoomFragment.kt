package com.dongguk.telepigeon.feature.home.enterroom

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.component.BottomSheetWithOneBtnDialogFragment
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.design.system.type.BottomSheetWithOneBtnType
import com.dongguk.telepigeon.feature.databinding.FragmentHomeEnterRoomBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeEnterRoomFragment : BindingFragment<FragmentHomeEnterRoomBinding>({ FragmentHomeEnterRoomBinding.inflate(it) }) {
    private val homeEnterRoomViewModel by viewModels<HomeEnterRoomViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        collectPostEntranceState()
        setBtnHomeEnterRoomClickListener()
        setEtHomeEnterRoomCodeTextChangedListener()
    }

    private fun initAppBar() {
        binding.appbarHomeEnterRoom.initLayout(appBarType = AppBarType.X)
        binding.appbarHomeEnterRoom.binding.ivAppBarTelepigeonX.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun collectPostEntranceState() {
        homeEnterRoomViewModel.postEntranceState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { postEntranceState ->
            when (postEntranceState) {
                is UiState.Success -> {
                    when (postEntranceState.data) {
                        SUCCESS -> findNavController().popBackStack()
                        CONFLICT -> showEnteredRoomBottomSheerDialogFragment()
                    }
                }

                is UiState.Error -> {
                    when (postEntranceState.code) {
                        403 -> showMatchedRoomBottomSheerDialogFragment()
                        404 -> showWrongCodeBottomSheerDialogFragment()
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBtnHomeEnterRoomClickListener() {
        binding.btnHomeEnterRoom.setOnClickListener {
            homeEnterRoomViewModel.postEntranceRoom(binding.etHomeEnterRoomCode.editText.text.toString())
        }
    }

    private fun setEtHomeEnterRoomCodeTextChangedListener() {
        binding.etHomeEnterRoomCode.setOnTextChangedListener { enterRoomCode ->
            binding.btnHomeEnterRoom.isEnabled = enterRoomCode.isNotBlank()
        }
    }

    private fun showWrongCodeBottomSheerDialogFragment() {
        BottomSheetWithOneBtnDialogFragment(
            bottomSheetWithOneBtnType = BottomSheetWithOneBtnType.WRONG_CODE,
        ).show(childFragmentManager, WRONG_CODE_BOTTOM_SHEET)
    }

    private fun showEnteredRoomBottomSheerDialogFragment() {
        BottomSheetWithOneBtnDialogFragment(
            bottomSheetWithOneBtnType = BottomSheetWithOneBtnType.ENTERED_ROOM,
        ).show(childFragmentManager, ENTERED_ROOM_BOTTOM_SHEET)
    }

    private fun showMatchedRoomBottomSheerDialogFragment() {
        BottomSheetWithOneBtnDialogFragment(
            bottomSheetWithOneBtnType = BottomSheetWithOneBtnType.MATCHED_ROOM,
            clickBtn = { findNavController().popBackStack() },
        ).show(childFragmentManager, MATCHED_ROOM_BOTTOM_SHEET)
    }

    companion object {
        private const val WRONG_CODE_BOTTOM_SHEET = "wrongCodeBottomSheet"
        private const val ENTERED_ROOM_BOTTOM_SHEET = "enteredRoomBottomSheet"
        private const val MATCHED_ROOM_BOTTOM_SHEET = "matchedRoomBottomSheer"
        private const val SUCCESS = "success"
        private const val CONFLICT = "conflict"
    }
}
