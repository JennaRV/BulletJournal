package com.example.rocketjournal

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rocketjournal.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AuthTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun logOutTest(){

    }

}