package com.deeppowercrew.bebetternow.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.databinding.DaysListItemBinding

class DaysAdapter(var listener: Listener) :
    ListAdapter<DayModel, DaysAdapter.DayHolder>(MyComparator()) {

    class DayHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = DaysListItemBinding.bind(view)

        fun setData(day: DayModel, listener: Listener) = with(binding) {
            val dayName = root.context.getString(R.string.day) + " ${adapterPosition + 1}"
            daysListItemName.text = dayName
            val trueCounter = ((day.exercises.split(",").size) / 2) + 1
            val exCounter = day.exercises.split(",").size.toString()
            checkBoxDay.isChecked = day.isDone
            // daysListItemCounter.text = exCounter
            daysListItemCounter.text =
                trueCounter.toString() + "  " + root.context.getString(R.string.workouts)
            itemView.setOnClickListener { listener.onClickDay(day.copy(dayNumber = adapterPosition + 1))}    //itemView - прослушиваем весть элемент
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.days_list_item, parent, false)
        return DayHolder(view)
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class MyComparator : DiffUtil.ItemCallback<DayModel>() {
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

    }

    interface Listener {
        fun onClickDay(day: DayModel)
    }
}