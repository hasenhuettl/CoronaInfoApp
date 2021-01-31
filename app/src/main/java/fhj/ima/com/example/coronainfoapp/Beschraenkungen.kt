package fhj.ima.com.example.coronainfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_beschraenkungen.*


class Beschraenkungen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beschraenkungen)


        val WebView = findViewById<WebView>(R.id.beschraenkungenHtml)
        WebView.loadUrl("https://www.sozialministerium.at/Informationen-zum-Coronavirus/Coronavirus---Aktuelle-Ma%C3%9Fnahmen.html")


        beschraenkungenFind.setOnClickListener(){
            WebView.findAll(beschraenkungenSearchView.getQuery().toString())
        }

        beschraenkungen_bottom_navigation?.selectedItemId = R.id.bottom_navigation_item_beschraenkungen

        beschraenkungen_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_navigation_item_main -> {val intent = Intent(this, MainActivity::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_zahlen -> { val intent = Intent(this, AktuelleZahlen::class.java); startActivity(intent) }
                // R.id.bottom_navigation_item_beschraenkungen -> { val intent = Intent(this, Beschraenkungen::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_ampelfarben -> { val intent = Intent(this, AmpelfarbenActivity::class.java); startActivity(intent) }
                else -> print("hi")
            }
            true
        }

    }



}