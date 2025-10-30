package com.example.calculatoralon

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatoralon.ui.theme.CalculatorAlonTheme
import android.content.Context // דרוש כדי להשתמש ב-Intent
import android.content.Intent // הכלי המרכזי להעברת מידע בין מסכים
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext // דרך להשיג את ה-Context בתוך Composable
import androidx.compose.ui.text.input.KeyboardType


//we are creating a new class named mainactivity which is an instance of kotlins "component activity" class
class MainActivity : ComponentActivity(){
    //then we override the component activity class pre-defined function "oncreate" to our own need
    //we fill the screen that is created with "SavedInstances" - that's all of the dynamic info that was stored on all our STATES on the current screen
    //"SavedInstances" is the name of that dynamic data variable. that varible's type is "Bundle" - bundle is a kitlin pre-defined class that stores dynamic data and has methids to deal with it. it's like a kotloin storage box.
    // first time wer'e creating the screen, SavedInstances(=Bundle) is empty (NULL)because  the user havn't stored any daya yet
    //that's why we need to give it the ability to be null (the "?" part)

    override fun onCreate(savedInstanceState: Bundle?) {
        //"super" reffering to our original parent class (component activity)
        super.onCreate(savedInstanceState)

//wer'e using the setontent method of our parent class (Component activity)  that takes all of the code inside the upcoming {...} and turns it into UI
//
        setContent {
            //our personal UI function (@composble function) that turns into the calculator UI
                CalculatorScreen()
        }

    }
}

@Composable
fun CalculatorScreen() {
    //creating a context so we can use intent
    val context = LocalContext.current
// storing our numbers inside changble states
    //every update to the state will trigger the UI to reload
    // the "by" techniqe is so we can just declare the states name and no have to write ".value" after it every time
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }

    //starting to use jetcompose pre-defined UI elements like column, text etc.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Alon Giladi",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // pre defined UI element for text input field
        //it's values will be the state that we created before
            //whenever it's changing, we'll take "it" (thats a pre-defined reffrence in...
        //...jetcompose for the value the user entered the input field) and define it to the state's new value
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Insert 1st Number") },
            //making sure the app will show an only numbers keyboard (instead of a regular one with letters whoch will crash the app if it tries to do math on it)

            
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Framework predefined Code - just to space between blocks on the screen
        Spacer(modifier = Modifier.height(8.dp))
        //same excact logic but for showing the value of number2 state varible
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Insert 2nd Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Buttons Horizntel tray

        // 4. ה"מגש" של הכפתורים - עם לוגיקה
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            Button(
                onClick = {

                    val num1 = number1.toDoubleOrNull()
                    val num2 = number2.toDoubleOrNull()


                    if (num1 != null && num2 != null) {
                        val result = num1 + num2


                        val intent = Intent(context, CalculatorResultActivity::class.java)

                        intent.putExtra("RESULT_KEY", result.toString())

                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "+", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))


            Button(
                onClick = {
                    val num1 = number1.toDoubleOrNull()
                    val num2 = number2.toDoubleOrNull()
                    if (num1 != null && num2 != null) {
                        val result = num1 - num2
                        val intent = Intent(context, CalculatorResultActivity::class.java)
                        intent.putExtra("RESULT_KEY", result.toString())
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "-", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))


            Button(
                onClick = {
                    val num1 = number1.toDoubleOrNull()
                    val num2 = number2.toDoubleOrNull()
                    if (num1 != null && num2 != null) {
                        val result = num1 * num2
                        val intent = Intent(context, CalculatorResultActivity::class.java)
                        intent.putExtra("RESULT_KEY", result.toString())
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "*", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))


            Button(
                onClick = {
                    val num1 = number1.toDoubleOrNull()
                    val num2 = number2.toDoubleOrNull()


                    if (num1 != null && num2 != null) {
                        // Core Language Syntax: 'if', 'else'
                        val result: String
                        if (num2 == 0.0) {
                            result = "Error: Division by zero "
                        } else {
                            result = (num1 / num2).toString()
                        }

                        val intent = Intent(context, CalculatorResultActivity::class.java)
                        intent.putExtra("RESULT_KEY", result)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "/", fontSize = 20.sp)
            }
        }
    }
}