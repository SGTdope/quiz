package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        full_list.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        full_list.adapter = questionAdapter

        // Populate the places list and notify the data set has changed.
        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i]))
        }
        questionAdapter.notifyDataSetChanged()
        createItemTouchHelper().attachToRecyclerView(full_list)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val AnsArrayCorr = arrayOf("False", "False", "True", "True")
                val AnsArrayFal = arrayOf("True", "True", "False", "False")
                var theElem1 = AnsArrayCorr[position]
                var theElem2 = AnsArrayFal[position]
                if((direction==8)){
                    if (theElem1 == "True"){
                        Toast.makeText(this@MainActivity, R.string.correct, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@MainActivity, R.string.incorrect, Toast.LENGTH_SHORT).show()
                    }
                }
                if((direction==4)){
                    if (theElem2 == "True"){
                        Toast.makeText(this@MainActivity, R.string.correct, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@MainActivity, R.string.incorrect, Toast.LENGTH_SHORT).show()
                    }
                }
                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}
