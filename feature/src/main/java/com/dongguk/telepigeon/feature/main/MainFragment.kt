package com.dongguk.telepigeon.feature.main

import android.os.Bundle
import android.view.View
import com.dongguk.telepigeon.feature.databinding.FragmentMainBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment

class MainFragment : BindingFragment<FragmentMainBinding>({ FragmentMainBinding.inflate(it) }) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
    }
}
