package fhj.ima.com.example.coronainfoapp

object AmpelRepository {
    private val ampelstufen: List<ampelfarben>

    init{
        ampelstufen = listOf(
                ampelfarben("Rot",
                listOf("Sehr hohe kumulative 7-Tages-Inzidenz relativ zur Bevölkerungsgröße der betrachteten Region",
                        "Hohe Anzahl an neu identifizierten Fällen ungeklärter Quelle",
                        "Unkontrollierte Ausbreitung von Clustern",
                        "Überwiegendes Auftreten von lokalen Clustern mit weitreichender regionaler Verbreitung",
                        "Sehr hohe Auslastung von Intensivbetten",
                        "Testkapazitäten am Limit",
                        "Sehr hohe Anzahl positiver Tests"),
                "#F99090"
        ),
                ampelfarben("Orange",
                listOf("Hohe kumulative 7-Tages-Inzidenz relativ zur Bevölkerungsgröße der betrachteten Region",
                        "Geringer Anteil an Fällen mit geklärter Quelle",
                        "Erhöhtes Auftreten von Fällen ungeklärter Quelle (absolut)",
                        "Gehäuftes Auftreten von Clustern",
                        "Anteil lokaler Cluster überwiegt den Anteil von Clustern mit einem Reise-Zusammenhang",
                        "Hohe Auslastung von Intensivbetten",
                        "Geringe Testaktivität",
                        "Erhöhte Anzahl positiver Tests"),
                        "#FEC49C"
                ),
                ampelfarben("Gelb",
                listOf("Moderate kumulative 7-Tages-Inzidenz relativ zur Bevölkerungsgröße der betrachteten Region",
                        "Moderate Reduktion des Anteils von Fällen mit geklärter Quelle",
                        "Moderate Zunahme der Anzahl von Fällen ungeklärter Quelle (absolut)",
                        "Moderat gesteigertes Auftreten von Clustern; diese sind Großteils mittels behördlichen Maßnahmen unter Kontrolle",
                        "Anteil von Clustern mit einem Reise-Zusammenhang überwiegt den Anteil lokaler Cluster",
                        "Moderate Auslastung von Intensivbetten",
                        "Moderat sinkende Testaktivität", "" +
                        "Moderat steigende Anzahl positiver Tests"),
                        "#FAF2A9"
                ),
                ampelfarben("Grün",
                listOf("Geringe kumulative 7-Tages-Inzidenz relativ zur Bevölkerungsgröße der betrachteten Region",
                        "Hauptsächlich sporadisch aus dem Ausland importierte Fälle",
                        "Hoher Anteil an Fällen mit geklärter Quelle",
                        "Vereinzeltes Auftreten von Fällen ungeklärter Quelle (absolut)",
                        "Vereinzeltes Auftreten von Clustern; diese sind Großteils mittels behördlichen Maßnahmen unter Kontrolle",
                        "Hauptsächlich Cluster mit Reise-Assoziation",
                        "Geringe Auslastung von Intensivbetten",
                        "Ausreichende Testaktivität",
                        "Niedrige Anzahl positiver Tests"),
                        "#BFFEBC"
                )
        )
    }
    fun ampelstufenList(): List<ampelfarben> {
        return ampelstufen
    }
}