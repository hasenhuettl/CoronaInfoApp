package fhj.ima.com.example.coronainfoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.coronainfoapp.R
import kotlinx.android.synthetic.main.item_zahlen.view.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.io.IOException
import java.lang.Exception
import java.util.*

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
            return (epikurveList[position - 1].Fälle_gesamt - epikurveList[position].Fälle_gesamt)
    }
}

class EpikurveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(epikurve: epikurve) {

        // Datum - java.time.* wird nicht von API level 21 unterstützt, deswegen ThreeTenABP import
        try {
            val language: String = "tr"
            val localDateTime = LocalDateTime.parse(epikurve.Datum+"T09:55:00")
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(Locale.forLanguageTag(language))
            val output: String? = formatter.format(localDateTime)
            itemView.Datum.text = output?.take(10) ?: throw IOException("konnte Datum nicht konvertieren!")
        }
        catch (e: IOException ) {
            itemView.Datum.text = "konnte Datum nicht konvertieren!"
        }
        catch (e: Exception){
            itemView.Datum.text = "etwas lief schief"
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
    }
}