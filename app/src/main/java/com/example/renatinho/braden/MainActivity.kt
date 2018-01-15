package com.example.renatinho.braden

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listHeader = listOf("numbers","fruits")

        val numberList = listOf("one", "two")
        val fruitList = listOf("apple", "orange")

        val listChild = HashMap<String,List<String>>()
        listChild.put(listHeader[0],numberList)
        listChild.put(listHeader[1],fruitList)


        val expandableListAdapter = ExpandableListAdapter(this,
                listHeader,listChild)

        expandable_list_view.setAdapter(expandableListAdapter)
        expandable_list_view.setOnChildClickListener()

    }
}
