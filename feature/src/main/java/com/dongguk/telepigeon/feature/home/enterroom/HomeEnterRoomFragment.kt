package com.dongguk.telepigeon.feature.home.enterroom

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeEnterRoomBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class HomeEnterRoomFragment : BindingFragment<FragmentHomeEnterRoomBinding>({ FragmentHomeEnterRoomBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAppBar()
        setBtnHomeEnterRoomClickListener()
    }

    private fun initAppBar() {
        binding.appbarHomeEnterRoom.initLayout(appBarType = AppBarType.X)
        binding.appbarHomeEnterRoom.binding.ivAppBarTelepigeonX.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setBtnHomeEnterRoomClickListener() {
        binding.btnHomeEnterRoom.setOnClickListener {
            findNavController().popBackStack(R.id.menu_home, false)
        }
    }
}
