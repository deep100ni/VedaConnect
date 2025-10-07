package com.DeepSoni.vedaconnect.feature.weeklyquiz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import com.DeepSoni.vedaconnect.R

//val OrangePrimary = Color(0xFFF77F00)
//val LightOrangeBg: Color = Color(0xFFFFEEE0)

@Composable
fun QuizStartScreen(navController: NavHostController) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    OrangePrimary,
                    shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
                )
                .padding(16.dp)
                .height(210.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(25.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {

                    Text(
                        text = "Weekly Quiz",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp)

                        )
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.alarmclock),
                            contentDescription = " ",
                            modifier = Modifier.size(24.dp)
                        )

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
                        .weight(.5f)
                        .height(20.dp)
                        .fillMaxWidth(),
                    color = LightOrangeBg,
                    trackColor = Color.LightGray
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        QuizQuestionWithOptions()
    }
}

val OrangePrimary = Color(0xFFF77F00)
val LightOrangeBg = Color(0xFFFFEEE0)
val GreyBorder = Color(0xFFE0E0E0)

@Composable
fun QuizQuestionWithOptions() {

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
                .background(Color(0xFFF77F00), shape = RoundedCornerShape(20.dp))
                .height(30.dp)
                .width(150.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,

            )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "In Which Mandala of Rigveda is the famous Nasadiya Sukta (Creation Hymn) found?",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
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
                        selectedColor = OrangePrimary,
                        unselectedColor = GreyBorder
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))
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
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {
            selectedOption
        },
        //enabled = selectedOption != null,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(56.dp)
            .alpha(buttonAlpha)
    ) {
        Text(text = "Submit Answer >", color = Color.White, fontSize = 18.sp)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Select an Option",
            color = Color(0xFFF77F00),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)

            //textAlign = androidx.compose.ui.text.style.TextAlign.Center,

            )
    }

    Spacer(modifier = Modifier.height(110.dp))
    BottomNavigationBar()
}