package com.dongguk.telepigeon.feature.calendar.monthlyreport

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.design.system.type.AppBarType
import com.dongguk.telepigeon.feature.calendar.calendar.CalendarFragment.Companion.DATE
import com.dongguk.telepigeon.feature.databinding.FragmentMonthlyReportBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MonthlyReportFragment : BindingFragment<FragmentMonthlyReportBinding>({ FragmentMonthlyReportBinding.inflate(it) }) {
    private val monthlyReportViewModel by viewModels<MonthlyReportViewModel>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        requireArguments().getString(DATE)?.let { monthlyReportViewModel.getMonthlyReport(it) }
        initAppBar()
        initLayout()
        setBtnMonthlyReportCheckClickListener()
    }

    private fun initAppBar() {
        binding.appbarMonthlyReport.initLayout(appBarType = AppBarType.X)
        binding.appbarMonthlyReport.binding.ivAppBarTelepigeonX.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initLayout() {
        with(binding) {
            tvMonthlyReportTitle.text = getString(com.dongguk.telepigeon.core.design.system.R.string.monthly_report_title, requireArguments().getString(DATE)?.split("-")?.getOrNull(1)?.toIntOrNull())
        }
    }

    private fun collectState() {
        monthlyReportViewModel.getMonthlyReportState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { getMonthlyReportState ->
            when (getMonthlyReportState) {
                is UiState.Success -> {
                    with(getMonthlyReportState.data) {
                        binding.ivMonthlyReportEmpty.visibility = if (this == null) View.VISIBLE else View.INVISIBLE
                        binding.tvMonthlyReportEmpty.visibility = if (this == null) View.VISIBLE else View.INVISIBLE

                        this?.let {
                            binding.tvMonthlyReportPositiveKeywordRank1.text = positiveKeywords[0]
                            binding.tvMonthlyReportPositiveKeywordRank2.text = positiveKeywords[1]
                            binding.tvMonthlyReportPositiveKeywordRank3.text = positiveKeywords[2]
                            binding.tvMonthlyReportNegativeKeywordRank1.text = negativeKeywords[0]
                            binding.tvMonthlyReportNegativeKeywordRank2.text = positiveKeywords[1]
                            binding.tvMonthlyReportNegativeKeywordRank3.text = positiveKeywords[2]
                        }
                    }
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBtnMonthlyReportCheckClickListener() {
        binding.btnMonthlyReportCheck.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
