import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.filters.LargeTest
import com.example.rocketjournal.view.MainActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class AuthTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun logInTest(){

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
        Thread.sleep(2000)
    }

    @Test
    fun signUpTest(){
        composeTestRule.onNodeWithText("Sign Up").assertExists()
        composeTestRule.onNodeWithText("Sign Up").performClick()
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("First Name").assertExists()
        composeTestRule.onNodeWithText("First Name").performTextInput("test")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Last Name").assertExists()
        composeTestRule.onNodeWithText("Last Name").performTextInput("dummy")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Username").assertExists()
        composeTestRule.onNodeWithText("Username").performTextInput("tdummy")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Email").performTextInput("tdummy@gmail.com")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Password").performTextInput("123456!")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Confirm Password").assertExists()
        composeTestRule.onNodeWithText("Confirm Password").performTextInput("123456!")
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Sign Up").performClick()
        Thread.sleep(2000)

    }

}
