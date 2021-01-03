package at.fh.swengb.coronainfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fhj.ima.com.example.coronainfoapp.AmpelRepository
import kotlinx.android.synthetic.main.activity_ampelfarben.*

class Ampelfarben : AppCompatActivity() {

    val AmpelfarbenAdapter = fhj.ima.com.example.coronainfoapp.AmpelfarbenAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ampelfarben)

        AmpelfarbenAdapter.updateList(AmpelRepository.ampelstufenList())
        ampelfarben_recycler_view.layoutManager = LinearLayoutManager(this)
        ampelfarben_recycler_view.adapter = AmpelfarbenAdapter
    }
}