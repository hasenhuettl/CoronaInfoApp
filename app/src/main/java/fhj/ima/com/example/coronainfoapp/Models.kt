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
    val Fälle_Zuwachs: Int,
    val Tote_v1: Int,
    val Tote_v1_Zuwachs: Int,
    val Tote_v2: Int,
    val Tote_v2_Zuwachs: Int,
    val Genesene: Int,
    val Genesene_Zuwachs: Int,
    val Hospitalisiert: Int,
    val Intensivstation: Int,
    val IBett_frei: Int,
    val IBett_Kapazität: Int,
    val Testungen: Int,
    val Testungen_Zuwachs: Int,
    val Burgenland: Int,
    val Burgenland_Tote: Int,
    val Burgenland_Genesene: Int,
    val Burgenland_Spital: Int,
    val Burgenland_Intensiv: Int,
    val Burgenland_Tests: Int,
    val Kärnten: Int,
    val Kärnten_Tote: Int,
    val Kärnten_Genesene: Int,
    val Kärnten_Spital: Int,
    val Kärnten_Intensiv: Int,
    val Kärnten_Tests: Int,
    val Niederösterreich: Int,
    val Niederösterreich_Tote: Int,
    val Niederösterreich_Genesene: Int,
    val Niederösterreich_Spital: Int,
    val Niederösterreich_Intensiv: Int,
    val Niederösterreich_Tests: Int,
    val Oberösterreich: Int,
    val Oberösterreich_Tote: Int,
    val Oberösterreich_Genesene: Int,
    val Oberösterreich_Spital: Int,
    val Oberösterreich_Intensiv: Int,
    val Oberösterreich_Tests: Int,
    val Salzburg: Int,
    val Salzburg_Tote: Int,
    val Salzburg_Genesene: Int,
    val Salzburg_Spital: Int,
    val Salzburg_Intensiv: Int,
    val Salzburg_Tests: Int,
    val Steiermark: Int,
    val Steiermark_Tote: Int,
    val Steiermark_Genesene: Int,
    val Steiermark_Spital: Int,
    val Steiermark_Intensiv: Int,
    val Steiermark_Tests: Int,
    val Tirol: Int,
    val Tirol_Tote: Int,
    val Tirol_Genesene: Int,
    val Tirol_Spital: Int,
    val Tirol_Intensiv: Int,
    val Tirol_Tests: Int,
    val Vorarlberg: Int,
    val Vorarlberg_Tote: Int,
    val Vorarlberg_Genesene: Int,
    val Vorarlberg_Spital: Int,
    val Vorarlberg_Intensiv: Int,
    val Vorarlberg_Tests: Int,
    val Wien: Int,
    val Wien_Tote: Int,
    val Wien_Genesene: Int,
    val Wien_Spital: Int,
    val Wien_Intensiv: Int,
    val Wien_Tests: Int

)