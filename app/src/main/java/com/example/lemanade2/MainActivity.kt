package com.example.lemanade2

import android.media.ImageReader
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemanade2.ui.theme.Lemanade2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemanade2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}


@Composable
fun LemonadeApp() {
    LemonadeTutorial()
}


@Composable
fun LemonadeTutorial(modifier: Modifier= Modifier) {
    var image by remember {
        mutableStateOf(R.drawable.lemon_tree)
    }
    var imageArray = intArrayOf(R.drawable.lemon_tree, R.drawable.lemon_squeeze, R.drawable.lemon_drink, R.drawable.lemon_restart)
    val textResource = when(image){
        R.drawable.lemon_tree -> R.string.tap_the_lemon_tree_to_select_a_lemon
        R.drawable.lemon_squeeze -> R.string.Keep_tapping_the_lemon_to_squeeze_it
        R.drawable.lemon_drink -> R.string.drink
        else -> R.string.start_again

    }
    Column {
        Button(onClick = {if(image==R.drawable.lemon_tree){
            image =R.drawable.lemon_squeeze
        }
        else if(image==R.drawable.lemon_squeeze){
            image = R.drawable.lemon_drink
        }
        else if(image == R.drawable.lemon_drink){
            image = R.drawable.lemon_restart
        }
        else if(image==R.drawable.lemon_restart){
            image = R.drawable.lemon_tree
        }
        }) {
            Image(painter = painterResource(id = image), contentDescription = null)
        }
        Text(text = stringResource(id = textResource))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lemanade2Theme {
        LemonadeApp()
    }
}