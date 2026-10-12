package com.example.clase1compose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.clase1compose.model.Data
import com.example.clase1compose.ui.theme.Clase1ComposeTheme
import com.example.clase1compose.util.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Clase1ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Clase1Test",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var dat by remember {
        mutableStateOf<Data?>(null)}


    Column() {

        Text(
            text = "Hello $name!",
            modifier = modifier,

            )

        Button(

            onClick = {

                Toast.makeText(
                    context,
                    "¡Botón presionado!",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d("Boton Test:","Boton precionado")

            }



        ) {

            Text("testButton")

        }


       Button(

            onClick = {

                scope.launch {

                    try {

                        dat = RetrofitClient.api.getData(1)

                       /* Log.d("API", "ID: ${resultado.id}")
                        Log.d("API", "Usuario: ${resultado.userId}")
                        Log.d("API", "Título: ${resultado.title}")
                        Log.d("API", "Contenido: ${resultado.body}")
*/
                        Toast.makeText(
                        context,
                        dat!!.title,
                        Toast.LENGTH_SHORT
                    ).show()

                    } catch (e: Exception) {
                        Log.d("Error Retrofit ", e.message.toString())
                        Toast.makeText(
                            context,
                            "¡Error al Conectar al servidor!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

            }

        ){

            Text("RestButton")
        }

    }



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Clase1ComposeTheme {
        Greeting("Alguien")
    }
}