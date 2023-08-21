package com.example.lemanade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun LemonadeTutorial(modifier: Modifier= Modifier.wrapContentSize(Alignment.Center)) {
    var image by remember {
        mutableStateOf(R.drawable.lemon_tree)
    }
    val textResource = when (image) {
        R.drawable.lemon_tree -> R.string.tap_the_lemon_tree_to_select_a_lemon
        R.drawable.lemon_squeeze -> R.string.Keep_tapping_the_lemon_to_squeeze_it
        R.drawable.lemon_drink -> R.string.drink
        else -> R.string.start_again
    }
    val contentDesc = when(textResource){
        R.string.tap_the_lemon_tree_to_select_a_lemon-> R.string.Lemon_tree
        R.string.Keep_tapping_the_lemon_to_squeeze_it -> R.string.Lemon
        R.string.drink -> R.string.Glass_of_lemanade
        else -> R.string.start_again
    }
    var times = (2..4).random()


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(190.dp).background(Color.Gray) .border(4.dp, Color(105, 205, 216), RoundedCornerShape(4.dp)))
        {
            Button(
                onClick = {
                    image = when (image) {
                        R.drawable.lemon_tree -> R.drawable.lemon_squeeze
                        R.drawable.lemon_squeeze -> {
                            times--
                            if(times!=0){
                                R.drawable.lemon_squeeze
                            }
                        else{
                            R.drawable.lemon_drink
                        }}
                        R.drawable.lemon_drink -> R.drawable.lemon_restart
                        else -> R.drawable.lemon_tree
                    }
                },
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(dimensionResource(androidx.core.R.dimen.compat_control_corner_material)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = contentDesc.toString(),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lemanade2Theme {
        LemonadeApp()
    }
}