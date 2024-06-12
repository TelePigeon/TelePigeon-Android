package com.dongguk.telepigeon.feature

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dongguk.telepigeon.feature.databinding.ActivityMainBinding
import com.dongguk.telpigeon.core.ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.setRoomId(-1)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.fcv_main_all_navi) as NavHostFragment).navController
        binding.bnvMainAllNavi.setupWithNavController(navController)

        setBottomNavigationVisibility(navController = navController)
        handleFcmNavigation(navController = navController)
    }

    private fun setBottomNavigationVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnvMainAllNavi.visibility =
                if (destination.id in
                    listOf(
                        R.id.menu_all_navi_main,
                        R.id.menu_all_navi_calender,
                        R.id.menu_all_navi_setting,
                    )
                ) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }
    }

    private fun handleFcmNavigation(navController: NavController) {
        intent.getStringExtra(ROOM_ID)?.let { id ->
            mainViewModel.setRoomId(roomId = id.toInt())
            navController.navigate(R.id.menu_all_navi_main)
        }
    }

    companion object {
        const val ROOM_ID = "id"
    }
}
