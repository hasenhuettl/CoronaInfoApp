package fhj.ima.com.example.coronainfoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
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
            val intent = Intent(this, AmpelfarbenActivity::class.java)
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

        main_bottom_navigation?.selectedItemId = R.id.bottom_navigation_item_main

        main_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                // R.id.bottom_navigation_item_main -> {val intent = Intent(this, MainActivity::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_zahlen -> { val intent = Intent(this, AktuelleZahlen::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_beschraenkungen -> { val intent = Intent(this, Beschraenkungen::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_ampelfarben -> { val intent = Intent(this, AmpelfarbenActivity::class.java); startActivity(intent) }
                else -> print("hi")
            }
            true
        }
    }
}


