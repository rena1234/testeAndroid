package com.example.renatinho.braden

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.content.Intent

class ResultActivity : AppCompatActivity() {

    private var againButton: Button? = null
    private var riscoTextView: TextView? = null
    private var resultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Create the next level button, which tries to show an interstitial when clicked.
        againButton = findViewById<View>(R.id.again_btn) as Button
        againButton!!.isEnabled = false
        againButton!!.setOnClickListener { reinicia() }

        resultTextView = findViewById<View>(R.id.contagem) as TextView
        riscoTextView = findViewById<View>(R.id.risco) as TextView

        val contagem = carregaContagem()
        val grauRisco = retornaGrauRisco(contagem)
        val textoRisco = retornaTextoRisco(grauRisco)

        escreveTexto(contagem, textoRisco)
        againButton!!.setText(resources.getString(R.string.btn_reinicia))
        againButton!!.isEnabled = true

    }
    private fun carregaContagem(): Int{
        /*
        val bundle = intent.extras
        val contagem = bundle.getString("contagem")
        return contagem.toInt()
        */
        return intent.getIntExtra("contagem",0)
    }

    private fun retornaGrauRisco(contagem: Int): Int{
        val risco =
                if(contagem in 10..12){
                    1
                }else if(contagem in 13..14) {
                    2
                }else if(contagem in 15..18){
                    3
                }else{
                    0
                }

        return risco
    }

    private fun retornaTextoRisco(grauRisco: Int): String{
        val riskMsgs = getResources().getStringArray(R.array.riscoArray)
        return riskMsgs[grauRisco]
    }
    private fun escreveTexto(contagem: Int, riskMsg:  String){
        riscoTextView!!.text = riskMsg
        resultTextView!!.text = contagem.toString()
    }

    private fun reinicia(){
       val intent = Intent(this, MainActivity::class.java)
       startActivity(intent)
    }
}
