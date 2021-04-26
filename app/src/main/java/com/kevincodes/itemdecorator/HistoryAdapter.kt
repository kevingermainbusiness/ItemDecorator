package com.kevincodes.itemdecorator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kevincodes.itemdecorator.databinding.HistoryRowBinding

class HistoryAdapter :
    ListAdapter<History, HistoryAdapter.HistoryViewHolder>(History.diffCallback) {

    class HistoryViewHolder(private val itemBinding: HistoryRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(history: History) {
            itemBinding.bankName.text = history.bankName
            itemBinding.convertedValue.text = history.amountConverted
            itemBinding.conversionResult.text = history.conversionResult
            itemBinding.conversionType.text = history.conversionType
            itemBinding.conversionRateType.text = history.conversionRateType
            itemBinding.rateFromDate.text = history.rateFromDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryRowBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HistoryViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val historyItem = getItem(position)
        holder.bind(historyItem)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }
}