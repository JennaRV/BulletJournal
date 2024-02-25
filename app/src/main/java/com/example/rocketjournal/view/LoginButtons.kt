// LoginButtons.kt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rocketjournal.viewmodel.MainViewModel
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController


@Composable
fun LoginButtons(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
/*
These buttons are not going to be positioned relative to the screen size, I have tried to get it to
work that way using a spacer but it just sends them to the bottom, if you are able to get it to work
please do!
 */
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)
                .absolutePadding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Spacer(modifier = Modifier.weight(0.6f))
            Button(
                onClick = { navController.navigate("loginPage") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFCE4B3)
                )
            ) {
                Text(
                    "Login",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W800,
                    color = Color(red = 100, green = 110, blue = 245)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("signup") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFCE4B3)
                )
            ) {
                Text(
                    "Sign Up",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W800,
                    color = Color(red = 100, green = 110, blue = 245)
                )
            }
        }
    }
}