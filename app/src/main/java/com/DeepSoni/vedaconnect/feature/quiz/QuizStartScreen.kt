package com.DeepSoni.vedaconnect.feature.quiz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.DeepSoni.vedaconnect.ui.theme.LightBlue
import com.DeepSoni.vedaconnect.ui.theme.Red
import com.DeepSoni.vedaconnect.ui.theme.headerOrangeGradient


data class QuizQuestions(
    val questionText: Map<String, String>,
    val options: List<Map<String, String>>,
    val correctAnswerKey: String
)

val masterQuizData = listOf(
    QuizQuestions(
        questionText = mapOf(
            "en" to "The Gayatri Mantra is found in which Mandala of the Rigveda?",
            "Hindi" to "गायत्री मंत्र ऋग्वेद के किस मंडल में पाया जाता है?"
        ),
        options = listOf(
            mapOf("en" to "Mandala 1", "Hindi" to "मंडल 1"),
            mapOf("en" to "Mandala 3", "Hindi" to "मंडल 3"),
            mapOf("en" to "Mandala 9", "Hindi" to "मंडल 9"),
            mapOf("en" to "Mandala 10", "Hindi" to "मंडल 10"),
        ),
        correctAnswerKey = "Mandala 3"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "Which Mandala of the Rigveda is completely dedicated to the deity Soma?",
            "Hindi" to "ऋग्वेद का कौन सा मंडल पूरी तरह से देवता सोम को समर्पित है?"
        ),
        options = listOf(
            mapOf("en" to "Seventh", "Hindi" to "सातवां"),
            mapOf("en" to "Eighth", "Hindi" to "आठवां"),
            mapOf("en" to "Ninth", "Hindi" to "नौवां"),
            mapOf("en" to "Tenth", "Hindi" to "दसवां"),
        ),
        correctAnswerKey = "Ninth"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "The term 'Purandara' (Destroyer of Forts) is used in the Rigveda for which deity?",
            "Hindi" to "'पुरंदर' (किलों को तोड़ने वाला) शब्द का प्रयोग ऋग्वेद में किस देवता के लिए किया गया है?"
        ),
        options = listOf(
            mapOf("en" to "Agni", "Hindi" to "अग्नि"),
            mapOf("en" to "Indra", "Hindi" to "इंद्र"),
            mapOf("en" to "Varuna", "Hindi" to "वरुण"),
            mapOf("en" to "Rudra", "Hindi" to "रुद्र"),
        ),
        correctAnswerKey = "Indra"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "The famous Battle of Ten Kings (Dasharajna War) was fought on the banks of which river?",
            "Hindi" to "प्रसिद्ध दस राजाओं का युद्ध (दशराज्ञ युद्ध) किस नदी के तट पर लड़ा गया था?"
        ),
        options = listOf(
            mapOf("en" to "Ganga", "Hindi" to "गंगा"),
            mapOf("en" to "Vipasha", "Hindi" to "विपाशा"),
            mapOf("en" to "Sutudri", "Hindi" to "शुतुद्री"),
            mapOf("en" to "Parushni", "Hindi" to "परुष्णी"),
        ),
        correctAnswerKey = "Parushni"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "Which god in the Rigvedic period was considered the upholder of 'Rita' (Cosmic Order)?",
            "Hindi" to "ऋग्वैदिक काल में किस देवता को 'ऋत' (ब्रह्मांडीय व्यवस्था) का संरक्षक माना जाता था?"
        ),
        options = listOf(
            mapOf("en" to "Indra", "Hindi" to "इंद्र"),
            mapOf("en" to "Agni", "Hindi" to "अग्नि"),
            mapOf("en" to "Varuna", "Hindi" to "वरुण"),
            mapOf("en" to "Vishnu", "Hindi" to "विष्णु"),
        ),
        correctAnswerKey = "Varuna"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "The word 'Varna' (system of social classes) is mentioned for the first time in which hymn of the Rigveda?",
            "Hindi" to "'वर्ण' (सामाजिक वर्ग) शब्द का उल्लेख ऋग्वेद के किस सूक्त में सर्वप्रथम मिलता है?"
        ),
        options = listOf(
            mapOf("en" to "Nasadiya Sukta", "Hindi" to "नासदीय सूक्त"),
            mapOf("en" to "Purusha Sukta", "Hindi" to "पुरुष सूक्त"),
            mapOf("en" to "Vivaha Sukta", "Hindi" to "िवाह सूक्त"),
            mapOf("en" to "Vak Sukta", "Hindi" to "वाक् सूक्त"),
        ),
        correctAnswerKey = "Purusha Sukta"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "What was the primary occupation of the Rigvedic Aryans?",
            "Hindi" to "ऋग्वैदिक आर्यों का मुख्य व्यवसाय क्या था?"
        ),
        options = listOf(
            mapOf("en" to "Trade", "Hindi" to "व्यापार"),
            mapOf("en" to "Agriculture", "Hindi" to "कृषि"),
            mapOf("en" to "Cattle Rearing", "Hindi" to "पशुपालन"),
            mapOf("en" to "Industry", "Hindi" to "उद्योग"),
        ),
        correctAnswerKey = "Cattle Rearing"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "The term 'Aghanya' used in the Rigveda refers to which animal?",
            "Hindi" to "ऋग्वेद में प्रयुक्त शब्द 'अघन्या' किस पशु को संदर्भित करता है?"
        ),
        options = listOf(
            mapOf("en" to "Horse", "Hindi" to "घोड़ा"),
            mapOf("en" to "Cow", "Hindi" to "गाय"),
            mapOf("en" to "Goat", "Hindi" to "बकरी"),
            mapOf("en" to "Elephant", "Hindi" to "हाथी"),
        ),
        correctAnswerKey = "Cow"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "What does the word 'Veda' literally mean?",
            "Hindi" to "शब्द 'वेद' का शाब्दिक अर्थ क्या है?"
        ),
        options = listOf(
            mapOf("en" to "Wisdom", "Hindi" to "बुद्धिमत्ता"),
            mapOf("en" to "Ritual", "Hindi" to "कर्मकांड"),
            mapOf("en" to "Knowledge", "Hindi" to "ज्ञान"),
            mapOf("en" to "Hymn", "Hindi" to "भजन"),
        ),
        correctAnswerKey = "Knowledge"
    ),
    QuizQuestions(
        questionText = mapOf(
            "en" to "Which river is most frequently mentioned in the hymns of the Rigveda?",
            "Hindi" to "ऋग्वेद के भजनों में किस नदी का उल्लेख सबसे अधिक बार किया गया है?"
        ),
        options = listOf(
            mapOf("en" to "Ganga", "Hindi" to "गंगा"),
            mapOf("en" to "Yamuna", "Hindi" to "यमुना"),
            mapOf("en" to "Sindhu", "Hindi" to "सिंधु"),
            mapOf("en" to "Saraswati", "Hindi" to "सरस्वती"),
        ),
        correctAnswerKey = "Sindhu"
    ),

    )

@Composable
fun QuizStartScreen(navController: NavController) {
    val context = LocalContext.current
    val scoreManager = remember { ScoreManager(context) }
    val selectedQuestions = remember { masterQuizData.shuffled().take(10) }
    val totalQuestions = selectedQuestions.size
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var totalPoints by remember { mutableIntStateOf(0) }
    var selectedAnswerKey by remember { mutableStateOf<String?>(null) }
    var answerStatus by remember { mutableStateOf<Boolean?>(null) }
    var currentLanguage by remember { mutableStateOf("en") }
    val currentQuestion = selectedQuestions.getOrNull(currentQuestionIndex)
    var correctCount by remember { mutableIntStateOf(0) }
    var wrongCount by remember { mutableIntStateOf(0) }
    val progress = if (totalQuestions > 0) {
        (minOf(currentQuestionIndex, totalQuestions).toFloat() / totalQuestions.toFloat())
        //((currentQuestionIndex + 1).toFloat() / totalQuestions.toFloat())
    } else {
        0f
    }
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "progressAnimation")
    val scrollState = rememberScrollState()

    var isQuizFinished by remember { mutableStateOf(false) }
    LaunchedEffect(isQuizFinished) {
        if (isQuizFinished) {
            scoreManager.saveScore(totalPoints)

            navController.navigate(
                Screen.QuizComplete.createRoute(
                    score,
                    totalQuestions,
                    totalPoints
                )
            ) {
                popUpTo(Screen.QuizStart.route) { inclusive = true }
            }
        }
    }

    val CORRECT_ANSWER_POINTS = 10

    val submitAnswer: () -> Unit = {
        if (selectedAnswerKey != null && answerStatus == null) {
            val isCorrect = selectedAnswerKey == currentQuestion?.correctAnswerKey
            answerStatus = isCorrect
            if (isCorrect) {
                score++
                totalPoints += CORRECT_ANSWER_POINTS
                correctCount++
            }else{
                wrongCount++
            }
        }
    }
    val nextQuestion: () -> Unit = {
        if (currentQuestionIndex < totalQuestions - 1) {
            currentQuestionIndex++
            selectedAnswerKey = null
            answerStatus = null

        } else {
            selectedAnswerKey = null
            answerStatus = null
            isQuizFinished = true
        }
        //}
    }
    val buttonText = when {
        currentQuestionIndex == totalQuestions - 1 ->
            if (currentLanguage == "en") "Go to Leaderboard" else "लीडरबोर्ड पर जाएं"

        answerStatus == null ->
            if (currentLanguage == "en") "Submit Answer" else "उत्तर सबमिट करें"

        currentQuestionIndex < totalQuestions - 1 ->
            if (currentLanguage == "en") "Next Question" else "अगला प्रश्न"

        else ->
            if (currentLanguage == "en") "Finish Quiz" else "प्रश्नोत्तरी समाप्त"
    }

    val switchButtonText = if (currentLanguage == "en") "English" else "Hindi"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    headerOrangeGradient,
                    shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
                )
                .padding(top = 50.dp, bottom = 15.dp, start = 16.dp, end = 16.dp),
            //.fillMaxHeight()
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
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                    )
                    Row(
                        modifier = Modifier

                    ) {
                        Image(
                            painter = painterResource(R.drawable.alarmclock),
                            contentDescription = " ",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "2:25",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    val questionNumber = minOf(currentQuestionIndex, totalQuestions)
                    val progressText =
                        if (currentLanguage == "en") "Questions $questionNumber of $totalQuestions"
                        else "Questions $questionNumber of $totalQuestions"
                    Text(
                        text = progressText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp)

                    )
                    Text(
                        text = "${(animatedProgress * 100).toInt()}%",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
                LinearProgressIndicator(
                    progress = animatedProgress,
                    modifier = Modifier
                        .height(5.dp)
                        .fillMaxWidth(),
                    color = LightOrangeBg,
                    trackColor = Color.LightGray
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        if (currentQuestion != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (currentLanguage == "en") "Points" else "अंक",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$totalPoints",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Bhagwa
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Correct",
                            modifier = Modifier.size(14.dp),
                            tint = LightBlue
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (currentLanguage == "en") "Correct: $correctCount" else "सही: $correctCount",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = LightBlue
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "Wrong",
                            modifier = Modifier.size(14.dp),
                            tint = Red
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (currentLanguage == "en") "Wrong: $wrongCount" else "गलत: $wrongCount",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Red
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentLanguage == "en") "Language" else "भाषा",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Button(
                        onClick = {
                            currentLanguage = if (currentLanguage == "en") "हिंदी" else "en"
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Bhagwa),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = switchButtonText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
            }
            QuizQuestionWithOptions(
                navController = navController,
                question = currentQuestion,
                selectedAnswerKey = selectedAnswerKey,
                onOptionSelected = { selectedAnswerKey = it },
                answerStatus = answerStatus,
                currentLanguage = currentLanguage,
                isCategoryVisible = false
            )
        } else {
            val pointsEarned = totalPoints
            LaunchedEffect(Unit) {
                navController.navigate(
                    "quizComplete/$score/$totalQuestions/$totalPoints"
                ) {
                    popUpTo(Screen.Quiz.route) { inclusive = false }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                when (buttonText) {
                    "Submit Answer", "उत्तर सबमिट करें" -> submitAnswer()
                    "Next Question", "अगला प्रश्न" -> nextQuestion()
                    "Go to Leaderboard", "लीडरबोर्ड पर जाएं" -> {
                        submitAnswer()
                        nextQuestion()
//                        navController.navigate(
//                            Screen.QuizComplete.route)
                    }
                }
            },
            enabled = selectedAnswerKey != null || answerStatus != null || currentQuestionIndex == totalQuestions,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Bhagwa),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(56.dp)
                .alpha(
                    if (selectedAnswerKey != null || answerStatus != null || currentQuestionIndex == totalQuestions) 1f else 0.3f
                )

        ) {
            Text(text = "$buttonText >", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val feedbackText = when (answerStatus) {
                true -> if (currentLanguage == "en") "Correct Answer" else "सही उत्तर "
                false -> {
                    val correctDisplay =
                        currentQuestion?.options?.first { it["en"] == currentQuestion.correctAnswerKey }
                            ?.get(currentLanguage) ?: currentQuestion?.correctAnswerKey
                    if (currentLanguage == "en") "Wrong Answer. The correct answer was: ${correctDisplay}" else "गलत उत्तर। सही उत्तर था: ${correctDisplay}"
                }

                null -> if (currentQuestionIndex == totalQuestions) (if (currentLanguage == "en") "Quiz Complete! Score: " else "क्विज समाप्त! स्कोर: ") + "$score/$totalQuestions" else (if (currentLanguage == "en") "Select an Option" else "एक विकल्प चुनें")
            }
            Text(
                text = feedbackText,
                color = when (answerStatus) {
                    true -> LightBlue
                    false -> Red
                    else -> Bhagwa
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )

        }
        //QuizQuestionWithOptions(navController = navController)
    }
}

@Composable
fun QuizQuestionWithOptions(
    navController: NavController,
    question: QuizQuestions,
    selectedAnswerKey: String?,
    onOptionSelected: (String) -> Unit,
    answerStatus: Boolean?,
    currentLanguage: String,
    isCategoryVisible: Boolean
) {
    val correctAnswerKey = question.correctAnswerKey
    val displayedQuestionText =
        question.questionText[currentLanguage] ?: question.questionText["en"] ?: "Error"

    fun getOptionDisplayText(option: Map<String, String>): String {
        return option[currentLanguage] ?: option["en"] ?: "Error"
    }
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
            text = displayedQuestionText,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        question.options.forEach { optionMap ->
            val verificationKey = optionMap["en"]!!
            val displayedOptionText = getOptionDisplayText(optionMap)
            val isSelected = selectedAnswerKey == verificationKey
            val isChecked = answerStatus != null
            val boxColor = when {
                !isChecked -> Color.White
                verificationKey == correctAnswerKey -> LightBlue
                isSelected && verificationKey != correctAnswerKey -> Red
                else -> Color.White
            }
            val isClickable = answerStatus == null
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(
                        elevation = if (isSelected) 4.dp else 1.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = boxColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable(enabled = isClickable) {
                        onOptionSelected(verificationKey)
                    }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { if (isClickable) onOptionSelected(verificationKey) },
                    enabled = isClickable,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = if (answerStatus == true && isSelected) Color.Green else if (answerStatus == false && isSelected) Red else Bhagwa,
                        unselectedColor = GreyBorder,
                        disabledSelectedColor = if (verificationKey == correctAnswerKey) LightBlue else Color.Gray,
                        disabledUnselectedColor = GreyBorder
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = displayedOptionText,
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
}