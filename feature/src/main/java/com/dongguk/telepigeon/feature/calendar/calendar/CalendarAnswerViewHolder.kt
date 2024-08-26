package com.dongguk.telepigeon.feature.calendar.calendar

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import com.dongguk.telepigeon.core.design.system.R
import com.dongguk.telepigeon.feature.databinding.ItemCalendarAnswerBinding

class CalendarAnswerViewHolder(
    private val binding: ItemCalendarAnswerBinding,
    private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(questionAnswerModel: QuestionAnswerModel) {
        with(binding) {
            ivCalendarAnswerImage.load(questionAnswerModel.answerImage.takeIf { answerImage -> !answerImage.isNullOrEmpty() } ?: R.drawable.ic_character)
            tvCalendarAnswerQuestionName.text = questionAnswerModel.questionName
            tvCalendarAnswerQuestionContent.text = questionAnswerModel.questionContent
            tvCalendarAnswerAnswerName.text = questionAnswerModel.answerName
            tvCalendarAnswerAnswerContent.text = questionAnswerModel.answerContent
        }
    }
}
