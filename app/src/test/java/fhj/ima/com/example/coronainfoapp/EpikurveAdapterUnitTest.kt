import com.google.common.truth.Truth.assertThat
import fhj.ima.com.example.coronainfoapp.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EpikurveAdapterUnitTest {

    private val epikurven = mutableListOf(epikurve(
        "2021-01-05",
        72000,
        "N"
        ),
        epikurve(
        "2021-01-04",
        71800,
        "40"
        ),
        epikurve(
            "2021-01-03",
            71700,
            "N"
        ),
        epikurve(
            "2021-01-02",
            71600,
            "10"
        ),
        epikurve(
            "2021-01-01",
            71000,
            "N"
        )
    )

    @Test
    fun itemCount_isCorrect() {
        /*
        * 1. test the itemCount() for an empty list
        * 2. test the itemCount() for a non-empty list
        */
        val adapter = EpikurveAdapter({ print(it)})
        // using google's truth library
        assertThat(adapter.itemCount).isEqualTo(0)

        // or using JUnit's assertEquals
        Assert.assertEquals(0, adapter.itemCount)
        ///////////////////////////////////////////
        adapter.epikurveList = epikurven
        assertThat(adapter.itemCount).isEqualTo(5)

        // or using JUnit's assertEquals
        Assert.assertEquals(5, adapter.itemCount)
    }

    @Test
    fun binding_isCorrect() {
        // make sure that onBindViewHolder(...) works as expected
        val adapter = EpikurveAdapter({ print(it)})
        adapter.epikurveList = epikurven
        val mockHolder = Mockito.mock(EpikurveViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder, 2)
        verify(mockHolder,times(1)).bindItem(epikurven[2])
    }

    @Test
    fun getNewvalue_isCorrect() {
        val adapter = EpikurveAdapter({ print(it)})
        assertThat(adapter.getNewValue(0)).isEqualTo(0)


        adapter.epikurveList = epikurven
        assertThat(adapter.getNewValue(4)).isEqualTo(71000)
        assertThat(adapter.getNewValue(3)).isEqualTo(10)
        assertThat(adapter.getNewValue(2)).isEqualTo(100)
        assertThat(adapter.getNewValue(0)).isEqualTo(200)

    }

    @Test
    fun getDateAsFloat_isCorrect() {
        val adapter = EpikurveAdapter({ print(it)})
        adapter.epikurveList = epikurven
        assertThat(adapter.getDateAsFloat(0)).isEqualTo(1.60980114E12f)
        assertThat(adapter.getDateAsFloat(1)).isEqualTo(1.60971476E12f)
    }

    @Test
    fun updateList_isCorrect() {
        // make sure that updateList(...) works as expected
        val adapter = EpikurveAdapter { print(it) }
        adapter.epikurveList = epikurven
        val mockHolder = Mockito.mock(EpikurveViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder, 3)
        verify(mockHolder,times(1)).bindItem(epikurven[3])


        try {
            val epikurveUpdated = (1..20).map{
                epikurve(
                    "2021-01-05",
                    72000,
                    "N"
                )
            }
            adapter.updateListEpikurve(epikurveUpdated)
        }
        catch (e : NullPointerException)
        {
            Assert.assertEquals(e.message, null)
        }
        Assert.assertEquals(20, adapter.itemCount)
    }
}