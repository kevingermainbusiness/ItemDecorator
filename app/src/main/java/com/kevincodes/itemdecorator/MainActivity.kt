package com.kevincodes.itemdecorator

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kevincodes.itemdecorator.databinding.ActivityMainBinding
import com.kevincodes.recyclerview.ItemDecorator

class MainActivity : AppCompatActivity() {
    private lateinit var myAdapter: MyAdapter
    private lateinit var displayedList: MutableList<Fruit>
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        myAdapter = MyAdapter()
        displayedList = arrayListOf()
        displayedList.addAll(fruits)
        myAdapter.submitData(displayedList)
        binding.myRecyclerView.adapter = myAdapter
        initializeLogic()
    }

    private fun initializeLogic() {
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val purple200 =
                    ContextCompat.getColor(this@MainActivity, R.color.purple_200)
                val teal200 =
                    ContextCompat.getColor(this@MainActivity, R.color.teal_200)
                val defaultWhiteColor =
                    ContextCompat.getColor(this@MainActivity, R.color.white)

                ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState)
                    .setFromStartToEndIcon(R.drawable.ic_baseline_delete_24)
                    .setFromEndToStartIcon(R.drawable.ic_baseline_save_alt_24)
                    .setFromStartToEndText("Delete")
                    .setFromEndToStartText("Save")
                    .setFromStartToEndBgColor(purple200)
                    .setFromEndToStartBgColor(teal200)
                    .setDefaultIconTintColor(defaultWhiteColor)
                    .setDefaultTextColor(defaultWhiteColor)
                    .create().decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val id = displayedList[position].id
                val fruitName = displayedList[position].fruitName
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        displayedList.removeAt(position)
                        binding.myRecyclerView.adapter?.notifyItemRemoved(position)
                        Log.d("MainActivity", "Removed fruit of id:$id with name:$fruitName")
                    }
                    ItemTouchHelper.RIGHT -> {
                        displayedList.removeAt(position)
                        binding.myRecyclerView.adapter?.notifyItemRemoved(position)
                        Log.d("MainActivity", "Removed fruit of id:$id with name:$fruitName")
                    }
                }
                // Once you have swept all the items, it will re-add them to the RecyclerView again
                if (displayedList.isNullOrEmpty()) {
                    displayedList.addAll(fruits)
                    binding.myRecyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
        itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.myRecyclerView)
    }
}