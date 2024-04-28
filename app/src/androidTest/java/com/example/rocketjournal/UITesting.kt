package com.example.rocketjournal

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.rocketjournal.view.MainActivity
import org.junit.Rule
import org.junit.Test

class UITesting {

    @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()
    @Test
    fun navTest(){

        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.onNodeWithText("Log In").assertExists()
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Email").performTextInput("test@ggc.edu")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Password").performTextInput("123456!")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Log In").performClick()
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Welcome!").assertExists()
        composeTestRule.waitUntil {
            // Wait until the LinearProgressIndicator is present
            composeTestRule.onNodeWithTag("progress").isNotDisplayed()

        }

        composeTestRule.onNodeWithTag("left").performClick()
        composeTestRule.waitUntil {
            // Wait until the LinearProgressIndicator is present
            composeTestRule.onNodeWithTag("progress").isNotDisplayed()

        }
        composeTestRule.onNodeWithTag("right").performClick()

        composeTestRule.onNodeWithTag("right").performClick()
        composeTestRule.waitUntil {
            // Wait until the LinearProgressIndicator is present
            composeTestRule.onNodeWithTag("progress").isNotDisplayed()

        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription("journal").performClick()
        composeTestRule.waitUntil {
            // Wait until the LinearProgressIndicator is present
            composeTestRule.onNodeWithTag("progress").isNotDisplayed()

        }
        composeTestRule.onNodeWithTag("journalColumn").assertExists()
        composeTestRule.onNodeWithContentDescription("list").performClick()
        composeTestRule.waitUntil {
            // Wait until the LinearProgressIndicator is present
            composeTestRule.onNodeWithTag("progress").isNotDisplayed()

        }
        composeTestRule.onNodeWithTag("listColumn").assertExists()
        composeTestRule.onNodeWithContentDescription("calendar").performClick()
        composeTestRule.onNodeWithText("Add Journal Entry").assertExists()
        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithContentDescription("calendar").performClick()
        composeTestRule.onNodeWithText("Weekly").performClick()
        composeTestRule.onNodeWithContentDescription("event").performClick()
        composeTestRule.waitUntil {
            // Wait until the LinearProgressIndicator is present
            composeTestRule.onNodeWithTag("progress").isNotDisplayed()

        }
        composeTestRule.onNodeWithTag("eventColumn").assertExists()

    }

}