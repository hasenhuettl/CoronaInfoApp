package fhj.ima.com.example.coronainfoapp

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import at.fh.swengb.coronainfoapp.Ampelfarben
import at.fh.swengb.coronainfoapp.MainActivity
import at.fh.swengb.coronainfoapp.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EpikurveIntentTest {
    @get:Rule
    val rule = ActivityScenarioRule(AktuelleZahlen::class.java)

    @Before
    fun setup() {
        Intents.init()
    }
    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun clickingAmpelfarbenBottomNavigation_shouldLaunchAmpelfarbenActivity() {
        onView(withId(R.id.bottom_navigation_item_ampelfarben )).perform(click())
        intended(hasComponent(Ampelfarben::class.java.name))
    }

    @Test
    fun clickingBeschraenkungenBottomNavigation_shouldLaunchBeschraenkungenActivity() {
        onView(withId(R.id.bottom_navigation_item_beschraenkungen )).perform(click())
        intended(hasComponent(Beschraenkungen::class.java.name))
    }

    @Test
    fun clickingMainBottomNavigation_shouldLaunchMainActivity() {
        onView(withId(R.id.bottom_navigation_item_main )).perform(click())
        intended(hasComponent(MainActivity::class.java.name))
    }

}
