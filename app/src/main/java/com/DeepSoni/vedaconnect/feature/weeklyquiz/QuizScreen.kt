package com.DeepSoni.vedaconnect.feature.weeklyquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.DeepSoni.vedaconnect.feature.home.HomeScreen



@Composable
fun QuizScreen(navController: NavHostController) {


    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    OrangePrimary,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(16.dp)
                .height(100.dp)
        ) {
            Column {
                Text(
                    text = "Weekly Quiz",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Test your Vedic Knowledge",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }


        Column(

            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            WeeklyChallengeCard(
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            PreviouseWeekSection()
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}


@Composable
fun PreviouseWeekSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Previous Week",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))

                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Week 1",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = .8f,
                    modifier = Modifier
                        .weight(1f)
                        .height(10.dp)

                        .width(8.dp),
                    color = OrangePrimary,
                    trackColor = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "80/100",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = "",
                    tint = Color.Gray
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))

                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Week 2",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = .6f,
                    modifier = Modifier
                        .weight(1f)
                        .height(10.dp)

                        .width(8.dp),
                    color = OrangePrimary,
                    trackColor = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "80/100",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = "Icon",
                    tint = Color.Gray
                )
            }

        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))

                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Week 3",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = .7f,
                    modifier = Modifier
                        .weight(1f)
                        .height(10.dp)

                        .width(8.dp),
                    color = OrangePrimary,
                    trackColor = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "80/100",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = "Icon",
                    tint = Color.Gray
                )
            }

        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))

                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Week 4",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = .5f,
                    modifier = Modifier
                        .weight(1f)
                        .height(10.dp)

                        .width(8.dp),
                    color = OrangePrimary,
                    trackColor = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "50/100",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = "Icon",
                    tint = Color.Gray
                )
            }

        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))

                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Week 5",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = .9f,
                    modifier = Modifier
                        .weight(1f)
                        .height(10.dp)

                        .width(8.dp),
                    color = OrangePrimary,
                    trackColor = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "90/100",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = "Icon",
                    tint = Color.Gray
                )
            }

        }

    }
}

@Composable
fun StateCard(icon: ImageVector, label: String, value: String, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightOrangeBg)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .heightIn(60.dp)
                    .fillMaxWidth()
                    .background(LightOrangeBg, shape = RoundedCornerShape(50.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color(0xFFF57C00),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .padding(start = 0.dp),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = value,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = label,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

            }
        }
    }
}

@Composable
fun WeeklyChallengeCard(modifier: Modifier = Modifier, navController: NavHostController) {
    val quizNavController = rememberNavController()

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightOrangeBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Icon")
        }
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(LightOrangeBg, shape = RoundedCornerShape(50.dp))
                .padding(8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            //Text(text = "Icon", fontSize = 24.sp, color = Color.Gray)
        }
        //Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Weekly Challenge",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 70.dp),
            textAlign = TextAlign.Center

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Answer 10 questions to get points and climb the leaderboard",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuizInfoItem(value = "10", label = "Questions")
            QuizInfoItem(value = "5.00", label = "Minutes")
            QuizInfoItem(value = "+100", label = "Max Points")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("quiz_start_screen")
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp)
        ) {
            Text(text = "Start Quiz", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun QuizInfoItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 15.sp, color = Color.Gray)
    }
}


@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {

            HomeScreen(navController = navController)
        }
        composable("weekly_quiz_screen") {
            QuizScreen(
                //navController = navController,
                navController = navController
            )
        }
    }
}
