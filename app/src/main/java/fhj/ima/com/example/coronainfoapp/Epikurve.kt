package fhj.ima.com.example.coronainfoapp

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
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
    }



}
private fun parseJson(){
    val json = """
            {
                "Datum": "2021-01-05",
                "Fälle_gesamt": "371657",
                "Fälle_Zuwachs": "2877",
                "Tote_v1": "6457",
                "Tote_v1_Zuwachs": "100",
                "Tote_v2": "6454",
                "Tote_v2_Zuwachs": "51",
                "Genesene": "334711",
                "Genesene_Zuwachs": "1218",
                "Hospitalisiert": "2033",
                "Intensivstation": "371",
                "IBett_frei": "630",
                "IBett_Kapazität": "1001",
                "Testungen": "3808540",
                "Testungen_Zuwachs": "15450",
                "Burgenland": "9741",
                "Burgenland_Tote": "173",
                "Burgenland_Genesene": "8846",
                "Burgenland_Spital": "39",
                "Burgenland_Intensiv": "5",
                "Burgenland_Tests": "116831",
                "Kärnten": "22656",
                "Kärnten_Tote": "509",
                "Kärnten_Genesene": "19438",
                "Kärnten_Spital": "123",
                "Kärnten_Intensiv": "13",
                "Kärnten_Tests": "175979",
                "Niederösterreich": "55890",
                "Niederösterreich_Tote": "915",
                "Niederösterreich_Genesene": "50143",
                "Niederösterreich_Spital": "397",
                "Niederösterreich_Intensiv": "49",
                "Niederösterreich_Tests": "724823",
                "Oberösterreich": "74271",
                "Oberösterreich_Tote": "1181",
                "Oberösterreich_Genesene": "67939",
                "Oberösterreich_Spital": "438",
                "Oberösterreich_Intensiv": "68",
                "Oberösterreich_Tests": "476011",
                "Salzburg": "30016",
                "Salzburg_Tote": "357",
                "Salzburg_Genesene": "26596",
                "Salzburg_Spital": "125",
                "Salzburg_Intensiv": "21",
                "Salzburg_Tests": "221884",
                "Steiermark": "43834",
                "Steiermark_Tote": "1406",
                "Steiermark_Genesene": "39143",
                "Steiermark_Spital": "333",
                "Steiermark_Intensiv": "62",
                "Steiermark_Tests": "401663",
                "Tirol": "41329",
                "Tirol_Tote": "528",
                "Tirol_Genesene": "38910",
                "Tirol_Spital": "122",
                "Tirol_Intensiv": "33",
                "Tirol_Tests": "464568",
                "Vorarlberg": "19839",
                "Vorarlberg_Tote": "226",
                "Vorarlberg_Genesene": "18581",
                "Vorarlberg_Spital": "58",
                "Vorarlberg_Intensiv": "11",
                "Vorarlberg_Tests": "243080",
                "Wien": "74081",
                "Wien_Tote": "1159",
                "Wien_Genesene": "65115",
                "Wien_Spital": "398",
                "Wien_Intensiv": "109",
                "Wien_Tests": "983701"
            }
        """
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter<epikurve>(epikurve::class.java)
    val result = jsonAdapter.fromJson(json)
}
