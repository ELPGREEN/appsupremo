package Supremo.Tribunal.Popula

import androidx.test.espresso.platform.ui.UiController
import androidx.test.ext.junit.rules.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import Supremo.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import Supremo.Tribunal.Popula.R
import Supremo.Tribunal.Popula.ui.*
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testIntroScreen() {
        Espresso.onView(withId(R.id.nameInput)).perform(typeText("Teste Juiz"))
        Espresso.onView(withText("Iniciar")).perform(click())
        Espresso.onView(withText("Escolha a Dificuldade")).check(matches(isDisplayed()))
    }
}