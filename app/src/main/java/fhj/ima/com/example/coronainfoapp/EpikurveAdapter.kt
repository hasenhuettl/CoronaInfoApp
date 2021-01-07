package fhj.ima.com.example.coronainfoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.coronainfoapp.R
import kotlinx.android.synthetic.main.item_zahlen.view.*
class EpikurveAdapter: RecyclerView.Adapter<EpikurveViewHolder>() {
    private var epikurveList = listOf<epikurve>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpikurveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val epikurveItemView = inflater.inflate(R.layout.item_zahlen, parent, false)
        return EpikurveViewHolder(epikurveItemView)
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
        return if (epikurveList[position].Fälle_Zuwachs != "N")
            return epikurveList[position].Fälle_Zuwachs.toInt()
        else if (position == (epikurveList.size - 1))
            return epikurveList[position].Fälle_gesamt
        else
            return (epikurveList[position-1].Fälle_gesamt - epikurveList[position].Fälle_gesamt)
    }
}

class EpikurveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(epikurve: epikurve) {
        itemView.Datum.text = epikurve.Datum
        itemView.zahlen_gesamt_Fälle.text = epikurve.Fälle_gesamt.toString()
        if (epikurve.Fälle_Zuwachs != "N") { // ToDo: falls zulässige Zahl
            itemView.zahlen_neue_Fälle.text = epikurve.Fälle_Zuwachs // ToDo als Header
        }
        else {
            itemView.zahlen_neue_Fälle.text = "keine Angabe"
            itemView.zahlen_text2.text = ""
        }
    }
}