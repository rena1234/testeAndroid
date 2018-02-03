package com.example.renatinho.braden

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listHeader = listOf("Percepção Sensorial"
                ,"Umidade")

        val numberList = listOf("Ocasionalmente Molhada", "two")
        val fruitList = listOf("apple", "orange")

        val listChild = HashMap<String,List<String>>()

        listChild[listHeader[0]] = numberList
        listChild[listHeader[1]] = fruitList

        val coloredParents = HashSet<Int>()

        val expandableListAdapter = ExpandableListAdapter(this,
                listHeader,listChild,coloredParents)

        expandable_list_view.setAdapter(expandableListAdapter)
	
	val escolhas =  IntArray(6, {i -> -1})

        val clickListener = ExpandableListView.OnChildClickListener{
            listView: ExpandableListView?, _: View?
            , groupPosition: Int, childPosition: Int, _: Long
            ->
            listView?.collapseGroup(groupPosition)
            if(coloredParents.contains(groupPosition) 
	            && escolha[groupPosition] == childPosition )
	    {
	        
		coloredParents.remove(groupPosition)
		escolhas[groupPosition] = -1

	    }
	    else{
	        escolhas[groupPosition] = childPosition;
                if(!coloredParents.contains(groupPosition)){
	            coloredParents.add(groupPosition)
		}
	    }
            true
        }
        expandable_list_view.setOnChildClickListener(clickListener)

    }
}
