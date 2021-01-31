package fhj.ima.com.example.coronainfoapp

import com.squareup.moshi.JsonClass

class ampelfarben (
    var stufe: String,
    var beschränkungen: List<String>,
    var color: String
)

@JsonClass(generateAdapter = true)
class epikurve(
    val Datum: String,
    val Fälle_gesamt: Int,
    val Fälle_Zuwachs: String
)