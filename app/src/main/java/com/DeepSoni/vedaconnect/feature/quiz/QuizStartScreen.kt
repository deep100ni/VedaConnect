package com.DeepSoni.vedaconnect.feature.quiz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.DeepSoni.vedaconnect.R
import com.DeepSoni.vedaconnect.Screen
import com.DeepSoni.vedaconnect.ui.theme.Bhagwa
import com.DeepSoni.vedaconnect.ui.theme.GreyBorder
import com.DeepSoni.vedaconnect.ui.theme.LightOrangeBg


@Composable
fun QuizStartScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Bhagwa,
                    shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
                )
                .padding(16.dp)
                .fillMaxHeight(.2f)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Weekly Quiz",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Alarm,
                            contentDescription = "Alarm Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "2:25",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "Questions 5 of 10",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp)

                    )
                    Text(
                        text = "50%",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
                LinearProgressIndicator(
                    progress = .5f,
                    modifier = Modifier
                        .height(5.dp)
                        .fillMaxWidth(),
                    color = LightOrangeBg,
                    trackColor = Color.LightGray
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        QuizQuestionWithOptions(navController = navController)
    }
}

@Composable
fun QuizQuestionWithOptions(navController: NavController) {

    var selectedOption by remember { mutableStateOf<String?>(null) }
    val buttonAlpha by animateFloatAsState(
        targetValue = if (selectedOption != null) 1f else 0.3f,
        label = "buttonAlphaAnimation"
    )
    val optionsList = listOf("Mandala 1", "Mandala 2", "Mandala 3", "Mandala 4")

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
            .background(Color(0xFFFFEEE0), shape = RoundedCornerShape(20.dp))

    ) {
        Text(
            text = "Philosophy",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .background(Bhagwa, shape = RoundedCornerShape(20.dp))
                .height(30.dp)
                .width(150.dp),
            textAlign = TextAlign.Center,

            )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "In Which Mandala of Rigveda is the famous Nasadiya Sukta (Creation Hymn) found?",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        optionsList.forEach { optionText ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(
                        elevation = if (selectedOption == optionText) 4.dp else 1.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        selectedOption = optionText
                    }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (selectedOption == optionText),
                    onClick = { selectedOption = optionText },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Bhagwa,
                        unselectedColor = GreyBorder
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = optionText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 5.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {
            navController.navigate(Screen.QuizComplete.route)
            //selectedOption
        },
        //enabled = selectedOption != null,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Bhagwa),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(56.dp)
            .alpha(buttonAlpha)
    ) {
        Text(text = "Submit Answer >", color = Color.White, fontSize = 18.sp)
    }
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "",
            color = Bhagwa,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}