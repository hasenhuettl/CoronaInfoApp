package fhj.ima.com.example.coronainfoapp

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import at.fh.swengb.coronainfoapp.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_aktuelle_zahlen.*
import okhttp3.*
import java.io.IOException


class AktuelleZahlen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aktuelle_zahlen)

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
private fun getRequest(){
    val url = "https://od.moi.gov.tw/adm/veh/query_veh?_m=query&vehType=B&vehNumber=AA-000"
    val request = DownloadManager
        .Request(Uri.parse(url))
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Open_data")
        .setAllowedOverMetered(true)
    val csv = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    var myDownLoadId = csv.enqueue(request)
}

private fun loadHtml(){
    val url = "https://www.sozialministerium.at/Informationen-zum-Coronavirus/Coronavirus---Aktuelle-Ma%C3%9Fnahmen.html";
    //val textView = findViewById<TextView>(R.id.beschraenkungenHtml)
    val okClient by lazy { OkHttpClient() }
    val okRequest by lazy {
        Request.Builder()
            .url(url)
            .build();
    }

    okClient.newCall(okRequest).enqueue(object: Callback {
        override fun onFailure(call: Call?, e: IOException?) {
            e?.printStackTrace()
        }

        override fun onResponse(call: Call?, response: Response?) {
            val input = response?.body()?.string() ?:""
            runOnUiThread{
                //textView.beschraenkungenHtml.text = input
            }
        }
    })
}