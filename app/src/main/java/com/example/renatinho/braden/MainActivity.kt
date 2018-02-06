package com.example.renatinho.braden

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.widget.Button

class MainActivity : AppCompatActivity() {
    
    private val coloredParents = HashSet<Int>()
    private var unmarkedParents = HashSet<Int>()
    private val listChild = HashMap<String,List<String>>()
    private val escolhas =  IntArray(6, {_ -> -1})
    private var fimButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listHeader = listOf("Percepção Sensorial"
                ,"Umidade")

        val numberList = listOf("Ocasionalmente Molhada", "two")
        val fruitList = listOf("apple", "orange")

        listChild[listHeader[0]] = numberList
        listChild[listHeader[1]] = fruitList

        val expandableListAdapter = ExpandableListAdapter(this,
                listHeader,listChild,coloredParents, unmarkedParents)

        expandable_list_view.setAdapter(expandableListAdapter)
	

        val clickListener = ExpandableListView.OnChildClickListener{
            listView: ExpandableListView?, _: View?
            , groupPosition: Int, childPosition: Int, _: Long
            ->
            listView?.collapseGroup(groupPosition)
            if(coloredParents.contains(groupPosition) 
	            && escolhas[groupPosition] == childPosition )
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
	    if(unmarkedParents.contains(groupPosition)){
		unmarkedParents.remove(groupPosition)
	    }
            true
        }
        expandable_list_view.setOnChildClickListener(clickListener)
        fimButton = findViewById<View>(R.id.fim_button) as Button
        fimButton!!.isEnabled = true
        fimButton!!.setOnClickListener{completaActivity()}
    }

    private fun retornaElementosDesmarcados(): HashSet<Int>{
        val desmarcados= HashSet<Int>()
        escolhas.forEachIndexed { i, escolha ->
           if( escolha == -1){
               desmarcados.add(i)
           }
        }
        return desmarcados
    }

    private fun completaActivity(){
        val desmarcadosSet = retornaElementosDesmarcados()
        if(!desmarcadosSet.isEmpty()){
            val text = "Complete this shit out"
            val toast = Toast.makeText (this, text, Toast.LENGTH_LONG)
            toast.show()
            unmarkedParents = desmarcadosSet
            /*
             * 4 linhas abaixo apenas para testes
             */
            val intent = Intent(this, AddActivity::class.java)
            val contagem = escolhas.sum() + escolhas.size
            intent.putExtra("contagem",contagem)
            startActivity(intent)
        }
        else{
           val intent = Intent(this, AddActivity::class.java)
           val contagem = escolhas.sum() + escolhas.size
           intent.putExtra("contagem",contagem)
           startActivity(intent)
        }
    }
}
