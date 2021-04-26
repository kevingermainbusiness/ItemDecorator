package com.kevincodes.itemdecorator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevincodes.itemdecorator.databinding.FruitRowBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var mData: List<Fruit> = arrayListOf()

    fun submitData(mList: List<Fruit>) {
        mData = mList
    }

    class ViewHolder(private val binding: FruitRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fruit: Fruit) {
            binding.fruitName.text = fruit.fruitName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FruitRowBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val getItem = mData[position]
        holder.bind(getItem)
    }

    override fun getItemCount(): Int = mData.size
}