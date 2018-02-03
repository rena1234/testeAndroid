package com.example.renatinho.braden

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import java.text.FieldPosition
import android.graphics.Typeface
import android.widget.ExpandableListView


/**
 * Created by renatinho on 06/01/18.
 */

class ExpandableListAdapter(val context: Context, val listOfHeaderData: List<String>
                            , val listOfChildData: HashMap<String, List<String>>
                            , val coloredParents: HashSet<Int>)
                            : BaseExpandableListAdapter()
{


    override fun getGroup(position: Int): Any {
        return listOfHeaderData[position]
    }

    override fun isChildSelectable(headerPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup?): View
    {
        val headerTitle = getGroup(groupPosition) as String

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_header,parent
                ,false)

        val listHeaderText = view.findViewById<AppCompatTextView>(R.id.list_header_text) as AppCompatTextView

        listHeaderText.setTypeface(null, Typeface.BOLD)
        listHeaderText.text = headerTitle

        if(coloredParents.contains(groupPosition)){
            listHeaderText.setTextColor(0xFF8363FF.toInt())
        }

        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listOfChildData[listOfHeaderData[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listOfChildData[listOfHeaderData[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean
                              , convertView: View?, parent: ViewGroup?): View {
        val childText = getChild(groupPosition,childPosition) as String
        
        val view: View =  LayoutInflater.from(context).inflate(R.layout.list_item,parent
                ,false)

        val listItemText = view.findViewById<AppCompatTextView>(R.id.list_item_text) as AppCompatTextView
        listItemText.text = childText

        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return listOfHeaderData.size
    }
}
