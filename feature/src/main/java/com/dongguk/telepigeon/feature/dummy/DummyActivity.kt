package com.dongguk.telepigeon.feature.dummy

import android.os.Bundle
import androidx.activity.viewModels
import com.dongguk.telepigeon.feature.databinding.ActivityDummyBinding
import com.dongguk.telpigeon.core.ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DummyActivity : BindingActivity<ActivityDummyBinding>({ ActivityDummyBinding.inflate(it) }) {
    private val dummyViewModel by viewModels<DummyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}