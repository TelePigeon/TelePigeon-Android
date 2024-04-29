package com.dongguk.telepigeon.feature.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.feature.R
import com.dongguk.telepigeon.feature.databinding.FragmentHomeBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHomeHome.setOnClickListener {
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        findNavController().navigate(R.id.action_home_to_main)
    }
}
