package at.fh.swengb.coronainfoapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fhj.ima.com.example.coronainfoapp.Beschraenkungen
import fhj.ima.com.example.coronainfoapp.AktuelleZahlen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        call_button.setOnClickListener{
            val call = Intent(Intent.ACTION_DIAL)
            call.setData(Uri.parse("tel:" +"1450"))
            startActivity(call)
        }

        aktuelleZahlen_button.setOnClickListener {
            val intent = Intent(this, AktuelleZahlen::class.java)
            startActivity(intent)
        }

        beschränkungen_button.setOnClickListener {
            val intent = Intent(this, Beschraenkungen::class.java)
            startActivity(intent)
        }

        open_erklärung.setOnClickListener {
            val intent = Intent(this, Ampelfarben::class.java)
            startActivity(intent)
        }
    }
}