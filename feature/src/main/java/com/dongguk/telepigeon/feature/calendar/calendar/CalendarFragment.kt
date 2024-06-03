package com.dongguk.telepigeon.feature.calendar.calendar

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.feature.databinding.FragmentCalendarBinding
import com.dongguk.telpigeon.core.ui.base.BindingFragment
import com.dongguk.telpigeon.core.ui.util.fragment.stringOf
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class CalendarFragment : BindingFragment<FragmentCalendarBinding>({ FragmentCalendarBinding.inflate(it) }) {
    private val calendarViewModel by viewModels<CalendarViewModel>()
    private lateinit var calendarAnswerAdapter: CalendarAnswerAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setCvCalendarDateChangeListener()
        setBtnCalendarMonthlyReportClickListener(LocalDate.now().year.toString() + "-" + LocalDate.now().monthValue)
    }

    private fun initAdapter() {
        calendarAnswerAdapter = CalendarAnswerAdapter()
        binding.rvCalendarAnswer.adapter = calendarAnswerAdapter

        // TODO 서버통신 구현 후 collectData 함수로 해당 로직 이동
        calendarAnswerAdapter.submitList(calendarViewModel.dummyQuestionAnswerModels)

        (calendarViewModel.dummyQuestionAnswerModels.isEmpty()).let { isEmpty ->
            with(binding) {
                ivCalendarEmpty.visibility = if (isEmpty) View.VISIBLE else View.INVISIBLE
                tvCalendarEmpty.visibility = if (isEmpty) View.VISIBLE else View.INVISIBLE
            }
        }
    }

    private fun setCvCalendarDateChangeListener() {
        binding.cvCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            binding.tvCalendarEmpty.text = if (LocalDate.now() == LocalDate.of(year, month + 1, dayOfMonth)) stringOf(R.string.calendar_today_answer_empty) else stringOf(R.string.calendar_future_answer_empty)

            setBtnCalendarMonthlyReportClickListener(year.toString() + "-" + (month + 1))
        }
    }

    private fun setBtnCalendarMonthlyReportClickListener(date: String) {
        binding.btnCalendarMonthlyReport.setOnClickListener {
            navigateToMonthlyReport(date)
        }
    }

    private fun navigateToMonthlyReport(date: String) {
        findNavController().navigate(com.dongguk.telepigeon.feature.R.id.action_all_navi_calender_to_monthly_report, bundleOf(DATE to date))
    }

    companion object {
        const val DATE = "date"
    }
}
