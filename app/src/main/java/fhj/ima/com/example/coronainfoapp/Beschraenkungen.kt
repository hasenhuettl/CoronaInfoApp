package fhj.ima.com.example.coronainfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import at.fh.swengb.coronainfoapp.R
import kotlinx.android.synthetic.main.activity_beschraenkungen.view.*
import okhttp3.*
import java.io.IOException
import android.util.Log
import android.webkit.WebView


class Beschraenkungen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beschraenkungen)

        Log.i("beschraenkungen", "1")

        //loadHtml()

        val WebView = findViewById<WebView>(R.id.beschraenkungenHtml)
        WebView.loadUrl("https://www.sozialministerium.at/Informationen-zum-Coronavirus/Coronavirus---Aktuelle-Ma%C3%9Fnahmen.html")

        Log.i("beschraenkungen", "2")

    }

    /*private fun loadHtml(){
        val url = "https://www.sozialministerium.at/Informationen-zum-Coronavirus/Coronavirus---Aktuelle-Ma%C3%9Fnahmen.html";
        val textView = findViewById<TextView>(R.id.beschraenkungenHtml)
        val okClient by lazy { OkHttpClient() }
        val okRequest by lazy {
            Request.Builder()
                    .url(url)
                    .build();
        }

        okClient.newCall(okRequest).enqueue(object:Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val input = response?.body()?.string() ?:""
                runOnUiThread{
                    textView.beschraenkungenHtml.text = input
                }
            }
        })
    }*/



}