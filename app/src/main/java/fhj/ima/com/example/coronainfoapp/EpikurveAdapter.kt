package fhj.ima.com.example.coronainfoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_zahlen.view.*
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class EpikurveAdapter (val clickListener: (epikurve: epikurve) -> Unit): RecyclerView.Adapter<EpikurveViewHolder>() {
    public var epikurveList = listOf<epikurve>()
    public val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpikurveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val epikurveItemView = inflater.inflate(R.layout.item_zahlen, parent, false)
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"))
        return EpikurveViewHolder(epikurveItemView, clickListener)
    }

    override fun getItemCount(): Int {
        return epikurveList.size
    }


    override fun onBindViewHolder(holder: EpikurveViewHolder, position: Int) {
        holder.bindItem(epikurveList[position])
    }

    fun updateListEpikurve(newList: List<epikurve>) {
        epikurveList = newList
        notifyDataSetChanged()
    }

    fun getNewValue(position: Int): Int{
        if (epikurveList.size == 0)
            return 0
        else if (epikurveList[position].Fälle_Zuwachs != "N")
            return epikurveList[position].Fälle_Zuwachs.toInt()
        else if (position == (epikurveList.size - 1))
            return epikurveList[position].Fälle_gesamt
        else
            return epikurveList[position].Fälle_gesamt - epikurveList[position + 1].Fälle_gesamt
    }

    fun getDateAsFloat (position: Int): Float {
        val tmp1 = dateFormat.parse(epikurveList[position].Datum)
        val tmp2 = tmp1.time
        val tmp3 = tmp2.toFloat()
        return tmp3
    }
}

class EpikurveViewHolder(itemView: View, val clickListener: (lesson: epikurve) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(epikurve: epikurve) {
        val df = SimpleDateFormat("yyyy-MM-dd")
        var date = df.parse(epikurve.Datum)
        val format = SimpleDateFormat("dd.MM.yyyy")
        if(date != null){
            itemView.Datum.text = format.format(date)
        }
        else{
            itemView.Datum.text = "Invalid Date"
        }

        // alle Fälle
        itemView.zahlen_gesamt_Fälle.text = epikurve.Fälle_gesamt.toString()


        // neue Fälle
        if (epikurve.Fälle_Zuwachs != "N" && epikurve.Fälle_Zuwachs != "") {
            itemView.zahlen_neue_Fälle.text = epikurve.Fälle_Zuwachs
        }
        else {
            itemView.zahlen_neue_Fälle.text = "keine Angabe"
        }

        itemView.setOnClickListener {

            clickListener(epikurve)
        }
    }
}