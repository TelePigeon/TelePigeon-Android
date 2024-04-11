package com.dongguk.telepigeon.feature.dummy

import android.os.Bundle
import androidx.activity.viewModels
import com.dongguk.telepigeon.design.system.component.BottomSheetWithOneBtnDialogFragment
import com.dongguk.telepigeon.design.system.component.BottomSheetWithTwoBtnDialogFragment
import com.dongguk.telepigeon.feature.databinding.ActivityDummyBinding
import com.dongguk.telepigeon.type.AppBarType
import com.dongguk.telpigeon.core.ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DummyActivity : BindingActivity<ActivityDummyBinding>({ ActivityDummyBinding.inflate(it) }) {
    private val dummyViewModel by viewModels<DummyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        dummyViewModel.getDummyUserList()

        binding.telepigeonAppbar.initLayout(AppBarType.TITLE, "제목")
        binding.telepigeonAppbar2.initLayout(AppBarType.X)

        binding.materialButton.setOnClickListener {
            BottomSheetWithTwoBtnDialogFragment(
                interjection = "감탄사,",
                sentence = "문장",
                leftBtnText = "leftBtn",
                rightBtnText = "rightBtn"
            ).show(supportFragmentManager, "TEST")
        }

        binding.materialButton2.setOnClickListener {
            BottomSheetWithOneBtnDialogFragment(
                interjection = "감탄사,",
                sentence = "문장",
                btnText = "button"
            ).show(supportFragmentManager, "TEST")
        }
    }
}
