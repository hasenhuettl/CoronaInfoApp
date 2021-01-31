package fhj.ima.com.example.coronainfoapp

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AmpelfarbenRepositoryUnitTest {

    @Test
    fun ampelstufenList_isCorrect() {
        val ampelstufenList = AmpelRepository.ampelstufenList()
        Assert.assertEquals(4, ampelstufenList.size)
    }

}