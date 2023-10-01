package com.example.snackbardemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.snackbardemo.ui.theme.SnackBarDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackBarDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DisplaySnackBar()
                }
            }
        }
    }
}


@Preview
@Composable
fun DisplaySnackBar(){
   val scaffoldState : ScaffoldState = rememberScaffoldState()
   val coroutineScope : CoroutineScope = rememberCoroutineScope()
   
   Scaffold(scaffoldState = scaffoldState) {
       Button(onClick = {
            coroutineScope.launch {
              val snackBarResult =  scaffoldState.snackbarHostState.showSnackbar(
                   message = "This is the message",
                   actionLabel = "Undo",
                   duration = SnackbarDuration.Indefinite
               )
              when(snackBarResult){
                  SnackbarResult.ActionPerformed -> Log.i("MYTAG","action label clicked")
                  SnackbarResult.Dismissed -> Log.i("MYTAG","dismissed!")
              }
            }
       }) {
       Text(text = "Display SnackBar")
       }
   }
}

