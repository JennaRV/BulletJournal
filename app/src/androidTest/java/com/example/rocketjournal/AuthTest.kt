package com.example.rocketjournal

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest

import com.example.rocketjournal.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AuthTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun logInTest(){
        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.onNodeWithText("Log In").assertExists()
        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
    }

    @Test
    fun signUpTest(){
        composeTestRule.onNodeWithText("Sign Up").assertExists()
        composeTestRule.onNodeWithText("Sign Up").performClick()
        composeTestRule.onNodeWithText("First Name").assertExists()
        composeTestRule.onNodeWithText("Last Name").assertExists()
        composeTestRule.onNodeWithText("Username").assertExists()
        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Confirm Password").assertExists()

    }

}