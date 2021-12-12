package com.kevincodes.itemdecorator

import android.graphics.Canvas
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kevincodes.itemdecorator.databinding.ActivityMainBinding
import com.kevincodes.recyclerview.ItemDecorator

/**
 * This is a sample app to make a sample implementation of the [ItemDecorator] class.
 *
 * This project displays a list of [History] data in a [RecyclerView].
 *
 * After each [ItemTouchHelper.SimpleCallback.onChildDraw] calls,
 * We'll be calling the [ItemDecorator.decorate] method on each items in the [RecyclerView].
 * @author Kevin Germain
 * */
class MainActivity : AppCompatActivity() {
    // The adapter of the recyclerView
    private lateinit var historyAdapter: HistoryAdapter

    // The list to populate in the adapter
    private lateinit var displayedList: MutableList<History>

    // The class to detect swipe so we can draw the ItemDecoration
    private lateinit var itemTouchHelper: ItemTouchHelper

    // Our activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        historyAdapter = HistoryAdapter()
        displayedList = arrayListOf()
        displayedList.addAll(historyList)
        historyAdapter.submitList(displayedList)
        binding.myRecyclerView.adapter = historyAdapter
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

                val colorAlert =
                    ContextCompat.getColor(this@MainActivity, R.color.colorAlert)
                val teal200 =
                    ContextCompat.getColor(this@MainActivity, R.color.teal_200)
                val defaultWhiteColor =
                    ContextCompat.getColor(this@MainActivity, R.color.white)

                // This is where to start decorating
                ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState).set(
                    bgColorFromStartToEnd = colorAlert,
                    bgColorFromEndToStart = teal200,
                    textFromStartToEnd = getString(R.string.action_delete),
                    textFromEndToStart = getString(R.string.action_add_to_fav),
                    textColorFromStartToEnd = defaultWhiteColor,
                    textColorFromEndToStart = defaultWhiteColor,
                    iconTintColorFromStartToEnd = defaultWhiteColor,
                    iconTintColorFromEndToStart = defaultWhiteColor,
                    iconResIdFromStartToEnd = R.drawable.ic_baseline_delete_24,
                    iconResIdFromEndToStart = R.drawable.ic_baseline_done_24
                )

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
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        displayedList.removeAt(position)
                        binding.myRecyclerView.adapter?.notifyItemRemoved(position)
                        Log.d("MainActivity", "Removed history of id:$id")
                    }
                    ItemTouchHelper.RIGHT -> {
                        displayedList.removeAt(position)
                        binding.myRecyclerView.adapter?.notifyItemRemoved(position)
                        Log.d("MainActivity", "Removed history of id:$id")
                    }
                }
                // Once you have swept all the items, it will re-add them to the RecyclerView again
                if (displayedList.isNullOrEmpty()) {
                    displayedList.addAll(historyList)
                    historyAdapter.submitList(displayedList)
                }
            }
        }
        itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.myRecyclerView)
    }
}