package fhj.ima.com.example.coronainfoapp

import com.squareup.moshi.JsonClass

class ampelfarben (
    val stufe: String,
    val beschränkungen: List<String>,
    val color: String
)

@JsonClass(generateAdapter = true)
class epikurve(
    val Datum: String,
    val Fälle_gesamt: Int,
    val Fälle_Zuwachs: String
)