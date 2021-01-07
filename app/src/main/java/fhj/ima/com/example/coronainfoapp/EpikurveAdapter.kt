package fhj.ima.com.example.coronainfoapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.coronainfoapp.R
import kotlinx.android.synthetic.main.item_ampelstufen.view.*
import kotlinx.android.synthetic.main.item_ampelstufen.view.MaterialCardView
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
}

class EpikurveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(epikurve: epikurve) {
        itemView.Datum.text = epikurve.Datum
        itemView.Faelle.text = epikurve.Fälle_gesamt.toString()
    }
}