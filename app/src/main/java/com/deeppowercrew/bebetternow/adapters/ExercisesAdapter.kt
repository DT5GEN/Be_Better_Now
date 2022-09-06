package com.deeppowercrew.bebetternow.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.databinding.ExersiseListItemBinding
import pl.droidsonroids.gif.GifDrawable

class ExercisesAdapter() :
    ListAdapter<ExerciseModel, ExercisesAdapter.ExerciseHolder>(MyComparator()) {

    class ExerciseHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ExersiseListItemBinding.bind(view)

        fun setData(exercise: ExerciseModel) = with(binding) {

            exerciseListItemTvName.text = exercise.name
            exerciseListItemTvReps.text = exercise.time
            exerciseListItemImage.setImageDrawable(GifDrawable(root.context.assets,exercise.image))
                exerciseListItemImageCheckBox

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exersise_list_item, parent, false)
        return ExerciseHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyComparator : DiffUtil.ItemCallback<ExerciseModel>() {
        override fun areItemsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            return oldItem == newItem
        }

    }

}