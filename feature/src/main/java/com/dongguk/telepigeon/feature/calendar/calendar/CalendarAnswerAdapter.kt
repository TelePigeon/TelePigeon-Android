package com.dongguk.telepigeon.feature.calendar.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import com.dongguk.telepigeon.feature.databinding.ItemCalendarAnswerBinding
import com.dongguk.telpigeon.core.ui.util.view.ItemDiffCallback

class CalendarAnswerAdapter : ListAdapter<QuestionAnswerModel, CalendarAnswerViewHolder>(
    ItemDiffCallback<QuestionAnswerModel>(
        onItemsTheSame = { old, new -> old.answerImage == new.answerImage },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAnswerViewHolder = CalendarAnswerViewHolder(binding = ItemCalendarAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CalendarAnswerViewHolder, position: Int) {
        holder.onBind(questionAnswerModel = currentList[position])
    }
}