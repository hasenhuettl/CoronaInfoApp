package fhj.ima.com.example.coronainfoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeschraenkungenIntentTest {
    @get:Rule
    val rule = ActivityScenarioRule(Beschraenkungen::class.java)

    @Before
    fun setup() {
        Intents.init()
    }
    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun clickingAktuelleZahlenBottomNavigation_shouldLaunchEpikurveActivity() {
        onView(withId(R.id.bottom_navigation_item_zahlen )).perform(click())
        intended(hasComponent(AktuelleZahlen::class.java.name))
    }

    @Test
    fun clickingAmpelfarbenBottomNavigation_shouldLaunchAmpelfarbenActivity() {
        onView(withId(R.id.bottom_navigation_item_ampelfarben )).perform(click())
        intended(hasComponent(AmpelfarbenActivity::class.java.name))
    }

    @Test
    fun clickingMainBottomNavigation_shouldLaunchMainActivity() {
        onView(withId(R.id.bottom_navigation_item_main )).perform(click())
        intended(hasComponent(MainActivity::class.java.name))
    }

}
