package fhj.ima.com.example.coronainfoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.TypedValue
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_epikurve.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToLong


class AktuelleZahlen : AppCompatActivity() {
    val epikurvenAdapter = EpikurveAdapter() { }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_epikurve)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "LocalPersistence",
            Context.MODE_PRIVATE
        )

        epikurve_recycler_view.layoutManager = LinearLayoutManager(this)
        epikurve_recycler_view.adapter = epikurvenAdapter

        // Create an ArrayAdapter
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_numbers,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter

        EpikurveRepository.epikurveList(
            success = {
                epikurvenAdapter.updateListEpikurve(it)
                diagram()
                sharedPreferences.edit().putInt(
                    "MY_KEY_FOR_SPINNERSELECTION", adapter.getPosition(
                        spinner.selectedItem.toString()
                    )
                ).apply()
            },
            error = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )


        spinner.setSelection(sharedPreferences.getInt("MY_KEY_FOR_SPINNERSELECTION", 0))

        epikurve_button.setOnClickListener {
            diagram()
            sharedPreferences.edit().putInt(
                "MY_KEY_FOR_SPINNERSELECTION", adapter.getPosition(
                    spinner.selectedItem.toString()
                )
            ).apply()
        }

        epikurven_bottom_navigation?.selectedItemId = R.id.bottom_navigation_item_zahlen
        epikurven_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_navigation_item_main -> {
                    val intent = Intent(
                        this,
                        MainActivity::class.java
                    ); startActivity(intent)
                }
                // R.id.bottom_navigation_item_zahlen -> { val intent = Intent(this, AktuelleZahlen::class.java); startActivity(intent) }
                R.id.bottom_navigation_item_beschraenkungen -> {
                    val intent = Intent(
                        this,
                        Beschraenkungen::class.java
                    ); startActivity(intent)
                }
                R.id.bottom_navigation_item_ampelfarben -> {
                    val intent = Intent(
                        this,
                        AmpelfarbenActivity::class.java
                    ); startActivity(intent)
                }
                else -> print("hi")
            }
            true
        }


    }


    fun diagram() {
        //Part1
        val entries = ArrayList<Entry>()
        var iteration = epikurvenAdapter.itemCount
        if (spinner.selectedItem.toString() != "alle" && spinner.selectedItem.toString()
                .toInt() < epikurvenAdapter.itemCount
        ) {
            iteration = spinner.selectedItem.toString().toInt()
        }

        //Part2
        while (iteration > 0) {
            val wert = epikurvenAdapter.getNewValue(iteration - 1)
            val date = epikurvenAdapter.getDateAsFloat(iteration - 1)

            entries.add(Entry(date, wert.toFloat()))
            iteration--
        }

        //Part3
        val vl = LineDataSet(entries, "Anzahl der Fälle")

        //Part4
        val typedValue = TypedValue()
        theme.resolveAttribute(R.attr.colorOnPrimary, typedValue, true)
        val mycolor = typedValue.data

        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = mycolor
        vl.fillAlpha = mycolor
        lineChart.axisLeft.textColor = mycolor
        lineChart.axisRight.textColor = mycolor
        lineChart.xAxis.textColor = mycolor
        lineChart.legend.textColor = mycolor
        lineChart.description.textColor = mycolor

        lineChart.xAxis.valueFormatter = XAxisDateFormatter()

        //Part5
        lineChart.xAxis.labelRotationAngle = 0f

        //Part6
        lineChart.data = LineData(vl)

        //Part7
        lineChart.axisRight.isEnabled = false

        // val xAxis: XAxis = lineChart.xAxis
        val yAxis: YAxis = lineChart.axisLeft
        yAxis.setAxisMinimum(0f);
        lineChart.xAxis.setLabelCount(5,false)


        //Part8
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(false)

        //Part9
        lineChart.description.text = "Tage"
        lineChart.setNoDataText("No forex yet!")

        //Part10
        lineChart.animateX(1800, Easing.EaseInExpo)

        //Part11
        //val markerView = CustomMarker(this@ShowForexActivity, R.layout.marker_view)
        //lineChart.marker = markerView

    }

}


class XAxisDateFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val date = Date(value.roundToLong())
        val format = SimpleDateFormat("dd.MM.yyyy")
        return format.format(date)
    }
}




