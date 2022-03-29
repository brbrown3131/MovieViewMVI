package com.connriverlines.movieviewmvi

import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var activityScenario: ActivityScenario<DetailActivity>

    @Before
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun detailTest() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java)
        intent.putExtra("name", "Sample Movie Name")
        intent.putExtra("desc", "Sample Description")
        intent.putExtra("category", "Sample Category")

        activityScenario = ActivityScenario.launch(intent)

        Espresso.onView(withId(R.id.tvName)).check(ViewAssertions.matches(withText("Sample Movie Name")))
        Espresso.onView(withId(R.id.tvDesc)).check(ViewAssertions.matches(withText("Sample Description")))
        Espresso.onView(withId(R.id.tvCategory)).check(ViewAssertions.matches(withText("Sample Category")))
    }

    @Test
    fun updateButtonDisplayed() {
        Espresso.onView(withText("Load List")).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun spinnerNotDisplayed() {
        Espresso.onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(not(isDisplayed())))
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.connriverlines.movieviewmvi", appContext.packageName)
    }

}