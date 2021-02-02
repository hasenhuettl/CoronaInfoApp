package fhj.ima.com.example.coronainfoapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_ampelstufen.view.*

class AmpelfarbenAdapter(val clickListener: (ampelfarbe: ampelfarben) -> Unit): RecyclerView.Adapter<AmpelViewHolder>() {
    var ampelfarbenList = listOf<ampelfarben>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmpelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val ampelItemView = inflater.inflate(R.layout.item_ampelstufen, parent, false)
        return AmpelViewHolder(ampelItemView, clickListener)
    }

    override fun getItemCount(): Int {
        return ampelfarbenList.size
    }

    override fun onBindViewHolder(holder: AmpelViewHolder, position: Int) {
        holder.bindItem(ampelfarbenList[position])
    }

    fun updateList(newList: List<ampelfarben>) {
        ampelfarbenList = newList
        notifyDataSetChanged()
    }
}

    class AmpelViewHolder(itemView: View, val clickListener: (lesson: ampelfarben) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(ampelfarbe: ampelfarben) {
        itemView.ampelstufe.text = ampelfarbe.stufe

        itemView.beschränkungen.text = ampelfarbe.beschränkungen.joinToString( separator = "\n")
        itemView.MaterialCardView.setBackgroundColor(Color.parseColor(ampelfarbe.color))
        itemView.setOnClickListener {

            clickListener(ampelfarbe)
        }
    }
}