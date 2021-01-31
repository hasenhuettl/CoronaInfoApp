package fhj.ima.com.example.coronainfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_ampelfarben.*

class AmpelfarbenActivity : AppCompatActivity() {

    val AmpelfarbenAdapter = AmpelfarbenAdapter(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ampelfarben)

        AmpelfarbenAdapter.updateList(AmpelRepository.ampelstufenList())
        ampelfarben_recycler_view.layoutManager = LinearLayoutManager(this)
        ampelfarben_recycler_view.adapter = AmpelfarbenAdapter

        ampelfarben_bottom_navigation?.selectedItemId = R.id.bottom_navigation_item_ampelfarben

        ampelfarben_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_navigation_item_main -> {val intent = Intent(this, MainActivity::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_zahlen -> { val intent = Intent(this, AktuelleZahlen::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_beschraenkungen -> { val intent = Intent(this, Beschraenkungen::class.java); startActivity(intent) }
                // R.id.bottom_navigation_item_ampelfarben -> { val intent = Intent(this, Ampelfarben::class.java); startActivity(intent) }
                else -> print("hi")
            }
            true
        }

    }



}