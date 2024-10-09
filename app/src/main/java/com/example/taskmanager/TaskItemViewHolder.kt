package com.example.taskmanager

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root)
{
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    private val priorities = listOf("Low", "Medium", "High")
    fun bindTaskItem(taskItem: TaskItem)
    {
        binding.name.text = taskItem.name
        val randomPriority = priorities[Random.nextInt(priorities.size)]
        binding.prio.text = randomPriority
        // Bind the description
        binding.disk.text = taskItem.desc  // Add this line to display the description

        if (taskItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.disk.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG  // Add this to strike-through description when completed
        }

        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        binding.completeButton.setOnClickListener{
            clickListener.completeTaskItem(taskItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        if(taskItem.dueTime != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime)
        else
            binding.dueTime.text = ""
    }

}