package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview()
                }

            }
        }
    }
}

@Composable
fun lemonata(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(0) }

    Column (
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Lemonade",
            textAlign = TextAlign.Center
        )
    }
    when (currentStep) {
        1 -> {
            LemonTextAndImage(
                textLabelResourceId = (R.string.text_1),
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.text_1,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
        }
        2 -> {
            LemonTextAndImage(
                textLabelResourceId = (R.string.text_2),
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.text_2,
                //Lambda Expression Use karke Passing the function as a parameter
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
        }

        3 -> {
            LemonTextAndImage(
                textLabelResourceId = (R.string.text_3),
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.text_3,
                onImageClick = {
                    currentStep = 4
                }
            )
        }
        4 -> {
            LemonTextAndImage(
                textLabelResourceId = (R.string.text_4),
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.text_4,
                onImageClick = {
                    currentStep = 1
                }
            )
        }
    }
}


@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),

                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        lemonata()
    }
}