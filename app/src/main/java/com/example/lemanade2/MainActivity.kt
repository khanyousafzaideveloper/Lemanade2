package com.example.lemanade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemanade2.ui.theme.Lemanade2Theme
import java.time.format.TextStyle

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
fun LemonadeTutorial(modifier: Modifier= Modifier.wrapContentSize(Alignment.Center)) {
    var image by remember {
        mutableStateOf(R.drawable.lemon_tree)
    }
    val textResource = when(image){
        R.drawable.lemon_tree -> R.string.tap_the_lemon_tree_to_select_a_lemon
        R.drawable.lemon_squeeze -> R.string.Keep_tapping_the_lemon_to_squeeze_it
        R.drawable.lemon_drink -> R.string.drink
        else -> R.string.start_again

    }
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(modifier = Modifier
            .size(150.dp)
            .aspectRatio(1f)){
            Button(onClick = {
                    image = when(image){
                        R.drawable.lemon_tree -> R.drawable.lemon_squeeze
                        R.drawable.lemon_squeeze ->R.drawable.lemon_drink
                        R.drawable.lemon_drink -> R.drawable.lemon_restart
                        else -> R.drawable.lemon_tree
                    }
            },
                modifier =Modifier.fillMaxSize()
            ) {
                Image(painter = painterResource(id = image), contentDescription = null, modifier.background(
                    Color.Transparent
                ))

            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = stringResource(id = textResource), fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lemanade2Theme {
        LemonadeApp()
    }
}