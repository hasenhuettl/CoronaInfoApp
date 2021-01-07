package fhj.ima.com.example.coronainfoapp

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
import okhttp3.*
import java.io.IOException


class AktuelleZahlen : AppCompatActivity() {
    val epikurvenAdapter = EpikurveAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_epikurve)

        //Part1
        val entries = ArrayList<Entry>();

        //Part2
        val arra = arrayOf(1,2,2,5);
        entries.add(Entry(arra[0].toFloat(), arra[1].toFloat()))
        entries.add(Entry(arra[2].toFloat(), arra[3].toFloat()))


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

        EpikurveRepository.epikurveList(
                success = {
                    epikurvenAdapter.updateListEpikurve(it)
                },
                error = {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
        )
        parseJson()

        epikurve_recycler_view.layoutManager = LinearLayoutManager(this)
        epikurve_recycler_view.adapter = epikurvenAdapter
    }



private fun parseJson(){
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter<epikurve>(epikurve::class.java)
    val result = jsonAdapter.fromJson("""
            {"Datum": "2021-01-03",
        "Fälle_gesamt": 366525        
            }
        """.trimIndent())
    }
}
