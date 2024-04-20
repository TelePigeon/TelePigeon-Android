package com.dongguk.telepigeon.feature

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dongguk.telepigeon.feature.databinding.ActivityMainBinding
import com.dongguk.telpigeon.core.ui.base.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.fcv_main_all_navi) as NavHostFragment).navController
        binding.bnvMainAllNavi.setupWithNavController(navController)

        binding.bnvMainAllNavi.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_all_navi_main -> {
                    navController.navigate(R.id.menu_all_navi_main)
                    true
                }
                R.id.menu_all_navi_calender -> {
                    navController.navigate(R.id.menu_all_navi_calender)
                    true
                }
                R.id.menu_all_navi_setting -> {
                    navController.navigate(R.id.menu_all_navi_setting)
                    true
                }
                else -> false
            }
        }
    }
}
