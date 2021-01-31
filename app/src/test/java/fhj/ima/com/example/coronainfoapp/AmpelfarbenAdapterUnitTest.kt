import com.google.common.truth.Truth.assertThat
import fhj.ima.com.example.coronainfoapp.AmpelViewHolder
import fhj.ima.com.example.coronainfoapp.AmpelfarbenAdapter
import fhj.ima.com.example.coronainfoapp.EpikurveAdapter
import fhj.ima.com.example.coronainfoapp.ampelfarben
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AmpelfarbenAdapterUnitTest {

    private val ampelfarben = (1..15).map{fhj.ima.com.example.coronainfoapp.ampelfarben(
        "Rot",
        listOf("Sehr hohe kumulative 7-Tages-Inzidenz relativ zur Bevölkerungsgröße der betrachteten Region",
            "Hohe Anzahl an neu identifizierten Fällen ungeklärter Quelle",
            "Unkontrollierte Ausbreitung von Clustern",
            "Überwiegendes Auftreten von lokalen Clustern mit weitreichender regionaler Verbreitung",
            "Sehr hohe Auslastung von Intensivbetten",
            "Testkapazitäten am Limit",
            "Sehr hohe Anzahl positiver Tests"),
        "#F99090"
    )
    }

    @Test
    fun itemCount_isCorrect() {
        /*
        * 1. test the itemCount() for an empty list
        * 2. test the itemCount() for a non-empty list
        */
        val adapter = AmpelfarbenAdapter({ print(it)})
        // using google's truth library
        assertThat(adapter.itemCount).isEqualTo(0)

        // or using JUnit's assertEquals
        Assert.assertEquals(0, adapter.itemCount)
        ///////////////////////////////////////////
        adapter.ampelfarbenList = ampelfarben
        assertThat(adapter.itemCount).isEqualTo(15)

        // or using JUnit's assertEquals
        Assert.assertEquals(15, adapter.itemCount)
    }

    @Test
    fun binding_isCorrect() {
        // make sure that onBindViewHolder(...) works as expected
        val adapter = AmpelfarbenAdapter({ print(it)})
        adapter.ampelfarbenList = ampelfarben
        val mockHolder = Mockito.mock(AmpelViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder, 9)
        verify(mockHolder,times(1)).bindItem(ampelfarben[9])
    }

    @Test
    fun updateList_isCorrect() {
        // make sure that updateList(...) works as expected
        val adapter = AmpelfarbenAdapter { print(it) }
        adapter.ampelfarbenList = ampelfarben
        val mockHolder = Mockito.mock(AmpelViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder, 9)
        verify(mockHolder,times(1)).bindItem(ampelfarben[9])


        try {
            val ampelfarbenUpdated = (1..5).map{ampelfarben(
                "Rot",
                listOf("Sehr hohe kumulative 7-Tages-Inzidenz relativ zur Bevölkerungsgröße der betrachteten Region",
                    "Hohe Anzahl an neu identifizierten Fällen ungeklärter Quelle",
                    "Unkontrollierte Ausbreitung von Clustern",
                    "Überwiegendes Auftreten von lokalen Clustern mit weitreichender regionaler Verbreitung",
                    "Sehr hohe Auslastung von Intensivbetten",
                    "Testkapazitäten am Limit",
                    "Sehr hohe Anzahl positiver Tests"),
                "#F99090"
            )}
            adapter.updateList(ampelfarbenUpdated)
        }
        catch (e : NullPointerException)
        {
            Assert.assertEquals(e.message, null)
        }
        Assert.assertEquals(5, adapter.itemCount)
    }

    @Test
    fun ampelfarbe_changeProperties() {
        val ampelfarbe = ampelfarben(
            "Rot",
            listOf("Sehr hohe kumulative 7-Tages-Inzidenz relativ zur Bevölkerungsgröße der betrachteten Region",
                "Hohe Anzahl an neu identifizierten Fällen ungeklärter Quelle",
                "Unkontrollierte Ausbreitung von Clustern",
                "Überwiegendes Auftreten von lokalen Clustern mit weitreichender regionaler Verbreitung",
                "Sehr hohe Auslastung von Intensivbetten",
                "Testkapazitäten am Limit",
                "Sehr hohe Anzahl positiver Tests"),
            "#F99090"
        )

        Assert.assertEquals(ampelfarbe.stufe, "Rot")
        Assert.assertEquals(ampelfarbe.beschränkungen.size, 7)
        Assert.assertEquals(ampelfarbe.color, "#F99090")

        ampelfarbe.stufe = "Gelb"
        ampelfarbe.color = "#A99090"
        ampelfarbe.beschränkungen = listOf("Hohe Anzahl positiver Tests")

        Assert.assertEquals(ampelfarbe.stufe, "Gelb")
        Assert.assertEquals(ampelfarbe.beschränkungen.size, 1)
        Assert.assertArrayEquals(arrayOf(ampelfarbe.beschränkungen), arrayOf(listOf("Hohe Anzahl positiver Tests")))
        Assert.assertEquals(ampelfarbe.color, "#A99090")
    }
}