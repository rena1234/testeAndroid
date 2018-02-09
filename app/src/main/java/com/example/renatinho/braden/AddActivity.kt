package com.example.renatinho.braden

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class AddActivity : AppCompatActivity() {
    private var againButton: Button? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var riscoTextView: TextView? = null
    private var resultTextView: TextView? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Create the next level button, which tries to show an interstitial when clicked.
        againButton = findViewById<View>(R.id.next_level_button) as Button
        againButton!!.isEnabled = false
        againButton!!.setOnClickListener { showInterstitial() }

        resultTextView = findViewById<View>(R.id.contagem) as TextView
        riscoTextView = findViewById<View>(R.id.risco) as TextView

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd()
        loadInterstitial()

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()
    }

    private fun carregaContagem(): Int{
        val bundle = intent.extras
        val contagem = bundle.getString("contagem")
        return contagem
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


    private fun escreveTexto(contagem: Int){
        val riskMsgs = res.getStringArray(R.array.riscoArray)
        riscoTextView!!.text = riskMsgs[retornaGrauRisco(contagem)]
        resultTextView!!.text = contagem
    }

    private fun newInterstitialAd(): InterstitialAd {
        val interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = getString(R.string.interstitial_ad_unit_id)
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                againButton!!.isEnabled = true
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                againButton!!.isEnabled = true
            }

            override fun onAdClosed() {
                // COMPLETA ESSA MERDAA
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return interstitialAd
    }

    private fun showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd!!.isLoaded) {
            mInterstitialAd!!.show()
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadInterstitial() {
        // Disable the next level button and load the ad.
        againButton!!.isEnabled = false
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build()
        mInterstitialAd!!.loadAd(adRequest)
    }

    companion object {
        // Remove the below line after defining your own ad unit ID.
        private val TOAST_TEXT = "Test ads are being shown. " + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID."
    }
}
