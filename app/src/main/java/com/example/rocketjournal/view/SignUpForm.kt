package com.example.rocketjournal.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rocketjournal.model.dataModel.UserState
import com.example.rocketjournal.viewmodel.SignUpViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(navController: NavController, signUpViewModel: SignUpViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    //val signUpViewModel: SignUpViewModel
   // val userState: UserState
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var currentUserState by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        // Define your Column here and include TextFields and Button
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .absolutePadding(top = 200.dp)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var email = signUpViewModel.email.collectAsState(initial = "")
            val password = signUpViewModel.password.collectAsState()
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, // Label color when focused
                    unfocusedLabelColor = Color.White, // Label color when not focused
                    focusedBorderColor = Color.White, // Border color when focused
                    unfocusedBorderColor = Color.White, // Border color when not focused
                ),
                shape = RoundedCornerShape(60.dp)

            )
            Spacer(modifier = Modifier.height(1.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = { lastName= it },
                label = { Text("Last Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, // Label color when focused
                    unfocusedLabelColor = Color.White, // Label color when not focused
                    focusedBorderColor = Color.White, // Border color when focused
                    unfocusedBorderColor = Color.White, // Border color when not focused
                ),
                shape = RoundedCornerShape(60.dp)

            )
            Spacer(modifier = Modifier.height(1.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, // Label color when focused
                    unfocusedLabelColor = Color.White, // Label color when not focused
                    focusedBorderColor = Color.White, // Border color when focused
                    unfocusedBorderColor = Color.White, // Border color when not focused
                ),
                shape = RoundedCornerShape(60.dp)

            )
            Spacer(modifier = Modifier.height(1.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email.value,
                onValueChange = { signUpViewModel.onEmailChange(it) },
                label = { Text("Email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, // Label color when focused
                    unfocusedLabelColor = Color.White, // Label color when not focused
                    focusedBorderColor = Color.White, // Border color when focused
                    unfocusedBorderColor = Color.White, // Border color when not focused
                ),
                shape = RoundedCornerShape(60.dp)

            )
            Spacer(modifier = Modifier.height(1.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password.value,
                onValueChange = { signUpViewModel.onPasswordChange(it) },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, // Label color when focused
                    unfocusedLabelColor = Color.White, // Label color when not focused
                    focusedBorderColor = Color.White, // Border color when focused
                    unfocusedBorderColor = Color.White, // Border color when not focused
                ),
                shape = RoundedCornerShape(60.dp)

            )
            Spacer(modifier = Modifier.height(1.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, // Label color when focused
                    unfocusedLabelColor = Color.White, // Label color when not focused
                    focusedBorderColor = Color.White, // Border color when focused
                    unfocusedBorderColor = Color.White, // Border color when not focused
                ),
                shape = RoundedCornerShape(60.dp)

            )
            Spacer(modifier = Modifier.height(1.dp))
            Button(
                onClick = {


        //            supabaseViewModel.saveUser(firstName,lastName,username,email,password)
                    signUpViewModel.onSignUp()

                    navController.navigate("login")


                          },
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


//            when(userState){
//                is UserState.Loading -> {
//                    LoadingComponent()
//                }
//                is UserState.Success -> {
//                    val message = (userState as UserState.Success).message
//                    currentUserState = message
//                }
//                is UserState.Error -> {
//                    val message = (userState as UserState.Error).message
//                    currentUserState = message
//                }
//            }
//
//            if(currentUserState.isNotEmpty()){
//                Text(text = currentUserState)
//            }


        }
    }
}

