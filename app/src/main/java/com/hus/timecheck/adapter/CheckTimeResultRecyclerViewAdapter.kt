package com.hus.timecheck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hus.timecheck.databinding.ListItemBinding
import com.hus.timecheck.models.TimeCheckResult

class CheckTimeResultRecyclerViewAdapter() : RecyclerView.Adapter<MyViewHolder>() {


    private val resultsList = ArrayList<TimeCheckResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = ListItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(resultsList[position])
    }


    fun setList(results: List<TimeCheckResult>) {
        resultsList.clear()
        resultsList.addAll(results)
    }

}


class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(result: TimeCheckResult) {
        binding.descriptionText.text = buildString {
            append(result.checkTime)
            append(if (result.checkResult) " IN " else " OUTSIDE ")
            append(result.startTime)
            append("~")
            append(result.endTime)
        }
    }
}