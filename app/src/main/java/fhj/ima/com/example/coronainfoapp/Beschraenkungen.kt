package fhj.ima.com.example.coronainfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import at.fh.swengb.coronainfoapp.R
import kotlinx.android.synthetic.main.activity_beschraenkungen.view.*
import okhttp3.*
import java.io.IOException
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_beschraenkungen.*


class Beschraenkungen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beschraenkungen)

        Log.i("beschraenkungen", "1")

        //loadHtml()

        val WebView = findViewById<WebView>(R.id.beschraenkungenHtml)
        WebView.loadUrl("https://www.sozialministerium.at/Informationen-zum-Coronavirus/Coronavirus---Aktuelle-Ma%C3%9Fnahmen.html")


        beschraenkungenFind.setOnClickListener(){
            WebView.findAll(beschraenkungenSearchView.getQuery().toString())
        }

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
    /*override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_favorite -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }*/



}