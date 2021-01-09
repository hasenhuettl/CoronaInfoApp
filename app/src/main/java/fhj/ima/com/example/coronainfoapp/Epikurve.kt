package fhj.ima.com.example.coronainfoapp

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import at.fh.swengb.coronainfoapp.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_ampelfarben.*
import kotlinx.android.synthetic.main.activity_epikurve.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.concurrent.thread


class AktuelleZahlen : AppCompatActivity() {
    val epikurvenAdapter = EpikurveAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_epikurve)

        EpikurveRepository.epikurveList(
                success = {
                    epikurvenAdapter.updateListEpikurve(it)
                },
                error = {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
        )
        //parseJson()

        epikurve_recycler_view.layoutManager = LinearLayoutManager(this)
        epikurve_recycler_view.adapter = epikurvenAdapter

        epikurve_button.setOnClickListener{
            diagram() // ToDO einstellen der angezeigten Tage
        }

        // Create an ArrayAdapter
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.city_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter

    }






fun diagram(){ // ToDO skalierung und graphenbeschriftungen
    //Part1
    val entries = ArrayList<Entry>()
    var iteration = 0
    if (spinner.selectedItem.toString() !="alle" && spinner.selectedItem.toString().toInt() < epikurvenAdapter.itemCount) {
        iteration = spinner.selectedItem.toString().toInt() // epikurvenAdapter.getItemCount()
    } else { iteration = epikurvenAdapter.itemCount }
    //Part2
    var x = 0
    var y = 0
    while ( iteration > x){
        val wert = epikurvenAdapter.getNewValue(iteration-1)
        entries.add(Entry(y.toFloat(), wert.toFloat()))
        iteration--
        y++
    }

    //Part3
    val vl = LineDataSet(entries, "My Type")

    //Part4
    vl.setDrawValues(false)
    vl.setDrawFilled(true)
    vl.lineWidth = 3f
    vl.fillColor = R.color.blue
    vl.fillAlpha = R.color.red

    //Part5
    lineChart.xAxis.labelRotationAngle = 0f

    //Part6
    lineChart.data = LineData(vl)

    //Part7
    //lineChart.axisRight.isEnabled = false
    //lineChart.xAxis.axisMaximum = j+0.1f

    //Part8
    lineChart.setTouchEnabled(true)
    lineChart.setPinchZoom(true)

    //Part9
    lineChart.description.text = "Days"
    lineChart.setNoDataText("No forex yet!")

    //Part10
    lineChart.animateX(1800, Easing.EaseInExpo)

    //Part11
    //val markerView = CustomMarker(this@ShowForexActivity, R.layout.marker_view)
    //lineChart.marker = markerView

}
private fun parseJson(){
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter<epikurve>(epikurve::class.java)
    val result = jsonAdapter.fromJson("""
            {"Datum": "2021-01-03",
        "Fälle_gesamt": 366525,
            "Fälle_Zuwachs": "10"
            }
        """.trimIndent())
    }
}


