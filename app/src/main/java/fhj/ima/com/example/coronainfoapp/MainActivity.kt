package at.fh.swengb.coronainfoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import fhj.ima.com.example.coronainfoapp.Beschraenkungen
import fhj.ima.com.example.coronainfoapp.AktuelleZahlen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sharedPreferences: SharedPreferences = this.getSharedPreferences("LocalPersistence", Context.MODE_PRIVATE)
        sharedPreferences.getBoolean("KEY_DARKMODE", false).also { main_switch_darkmode.isChecked = it }
        if (main_switch_darkmode.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


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

        main_switch_darkmode.setOnClickListener{
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("KEY_DARKMODE", main_switch_darkmode.isChecked)
            editor.apply()
            if (main_switch_darkmode.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}