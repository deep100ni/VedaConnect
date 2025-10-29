package com.DeepSoni.vedaconnect.feature.community

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.DeepSoni.vedaconnect.R
import com.DeepSoni.vedaconnect.ui.theme.headerOrangeGradient

// Assuming VedaTheme and its Orange color are defined elsewhere in your project.
// For demonstration, I'll include a placeholder definition here.
// In a real project, this would likely be in a 'ui.theme' package.


// -----------------------------------------------------------
// Data classes for Awareness Screen Articles
// -----------------------------------------------------------

data class Article(
    val id: Int,
    val category: String,
    val title: String,
    val readTimeMinutes: Int,
    val views: String, // e.g., "2.4K views"
    val isFeatured: Boolean = false,
    val description: String? = null,
    val videoUrl: String? = null,
    val articalUrl: String? = null,// Add this field
    val imageUrl: Int? = null // Add this field
)

val sampleArticles = listOf(
    Article(
        1,
        "History",
        "Rigveda Manuscripts: A Journey Through Time",
        12,
        "",
        description = "The Rig Veda, as one of the oldest and most significant texts in the history of Hinduism, provides profound insights into the spiritual...",
        imageUrl = R.drawable.therigveda,
        videoUrl = "https://youtu.be/YXfRsS8MzX4"
    ), // Replace with a real link
    Article(
        2,
        "History",
        "The Rig Veda: A Gateway to Understanding Ancient Indian Spirituality",
        8,
        "",
        description = "The Rig Veda, as one of the oldest and most significant texts in the history of Hinduism, provides profound insights into the spiritual...",
        imageUrl = R.drawable.therigveda,
        articalUrl = "https://papers.ssrn.com/sol3/papers.cfm?abstract_id=5165911"
    ),
    Article(
        3,
        "Law",
        "Vedic Literature and Human Rights: Resonances and Dissonances",
        6,
        "",
        description = "The Vedic literature constitutes the fulcrum of Sanskrit literature and is repositories of some fundamental concepts of human rights....",
        imageUrl = R.drawable.law_of,
        articalUrl = "https://tandfonline.com/doi/full/10.1080/23311886.2020.1858562"
    ),
    Article(
        4,
        "Philosophy",
        "The Age of INDIA'S OLDEST BOOK: What They Won't Tell You",
        10,
        "",
        description = "Deep dive into the concept of universal truth and cosmic harmony...",
        imageUrl = R.drawable.therigveda,
        videoUrl = "https://youtube.com/watch?v=ZvTlJDWG0lM"
    ),
    Article(
        5,
        "Philosophy",
        "वेद हिन्दू धर्म",
        7,
        "",
        description = "वेद , दूसरी सहस्राब्दी ईसा पूर्व के दौरान उत्तर-पश्चिम भारत में रहने वाले इंडो-यूरोपीय भाषी लोगों द्वारा प्राचीन संस्कृत में रचित कविताओं या भजनों का एक संग्रह है।...",
        imageUrl = R.drawable.therigveda,
        articalUrl = "https://britannica.com/topic/Veda"
    ),
    Article(
        6,
        "Culture",
        "Rigveda - Memory of the World by UNESCO",
        9,
        "",
        description = "The Vedas are generally known as the scriptures of the Hindu community...",
        imageUrl = R.drawable.therigveda,
        articalUrl = "https://unesco.org/en/memory-world/rigveda"
    ),
    Article(
        7,
        "History",
        "Lost Cities of Saraswati: Unraveling Ancient Civilizations",
        15,
        "",
        description = "Investigating the archaeological significance of the mythical Saraswati River...",
        imageUrl = R.drawable.therigveda,
        articalUrl = "https://youtube.com/watch?v=vDZ0Jig5avE"
    ),
    Article(
        8,
        "Law",
        "Rigved : The History and Lessons Of Rigved",
        11, "",
        description = "The Rigved (or Rig Ved) is the oldest and most important of the four Vedas, which are the foundational scriptures of Sanatan Dharm (Hinduism)....",
        imageUrl = R.drawable.therigveda,
        articalUrl = "https://sameedh.com/the-history-and-lessons-of-rigved/"
    )
)

// -----------------------------------------------------------
// Awareness Screen Composable
// -----------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AwarenessScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("All") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.padding(top = 5.dp),

                    ) {
                        Text(
                            text = "Samvaad",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Awareness & enlightenment",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                modifier = Modifier.clip(
                    androidx.compose.foundation.shape.RoundedCornerShape(
                        bottomStart = 24.dp,
                        bottomEnd = 24.dp
                    )
                )
                    .height(120.dp)
                    .background(headerOrangeGradient)


            )
        },
        containerColor = Color(0xFFFFF7F0) // Background color matching the image
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .padding(top = paddingValues.calculateTopPadding()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val categories = listOf("All", "History", "Law", "Philosophy", "Culture")
                categories.forEach { categoryText ->
                    AwarenessFilterChip(
                        text = categoryText,
                        selected = selectedCategory == categoryText,
                        onClick = { selectedCategory = categoryText }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = paddingValues.calculateBottomPadding() + 90.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val recentArticles =
                    sampleArticles.filter { !it.isFeatured && (selectedCategory == "All" || it.category == selectedCategory) }
                if (recentArticles.isNotEmpty()) {
                    item {
                        Text(
                            text = "Rigveda Studies",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.Black
                        )
                    }
                    items(recentArticles) { article ->
                        RecentArticleCard(article = article) {
                            println("Clicked on recent article: ${article.title}")
                        }
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------
// Reusable Components for Awareness Screen
// -----------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AwarenessFilterChip(text: String, selected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                color = if (selected) Color.White else Color.Black
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFFF57C00), // Orange
            containerColor = Color.White,
            labelColor = Color.Black,
        ),
        border = if (!selected) BorderStroke(
            width = 1.dp,
            color = Color.Gray.copy(alpha = 0.5f)
        ) else null,
        shape = RoundedCornerShape(12.dp)
    )
}



@Composable
fun RecentArticleCard(article: Article, onClick: () -> Unit) {
    val context = LocalContext.current // Get the current context
    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.clickable(onClick = onClick),
            .clickable {
                // Define urlToOpen by checking for available URLs
                val urlToOpen = article.articalUrl ?: article.videoUrl

                if (!urlToOpen.isNullOrBlank()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlToOpen))
                    context.startActivity(intent)
                }
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val isVideo = article.videoUrl != null
            val hasLink = isVideo || article.articalUrl != null

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(article.imageUrl ?: R.drawable.achievement),
                    contentDescription = "Article Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                if (isVideo) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Video",
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.Black.copy(alpha = 0.6f), CircleShape),
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.width(15.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFBE9E7)),
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Text(
                            text = article.category,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFE64A19),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 14.sp
                )
                article.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

private fun openUrlInBrowser(context: Context, url: String?) {

    if (url.isNullOrBlank()) {
        println("URL is missing.")
        return
    }

    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        println("Could not open URL: ${e.message}")
    }
}
// -----------------------------------------------------------
// Vimarsh Manch (Community Screen) - Retained from previous context
// -----------------------------------------------------------

// Data class for a community question
data class CommunityQuestion(
    val id: Int,
    val category: String,
    val title: String,
    val author: String,
    val comments: Int,
    val likes: Int,
    val isAIAnswered: Boolean = false
)

// Sample data for demonstration
val sampleQuestions = listOf(
    CommunityQuestion(
        1,
        "Philosophy",
        "What is the meaning of 'Dharma' in Rigveda?",
        "Priya K.",
        12,
        24,
        true
    ),
    CommunityQuestion(
        2,
        "Practice",
        "How to incorporate daily Vedic practices in modern life?",
        "Rahul M.",
        8,
        18,
        true
    ),
    CommunityQuestion(
        3,
        "History",
        "Rigveda and modern scientific discoveries",
        "Dr. Anya S.",
        15,
        30
    ),
    CommunityQuestion(
        4,
        "Spirituality",
        "Understanding the concept of Atman and Brahman",
        "Vivek J.",
        20,
        45,
        true
    ),
    CommunityQuestion(5, "Rituals", "Significance of Agnihotra in daily life", "Maya R.", 7, 15),
    CommunityQuestion(
        6,
        "Philosophy",
        "Interpreting the concept of 'Karma' in Vedic texts",
        "Ankit S.",
        10,
        20
    ),
    CommunityQuestion(
        7,
        "Practice",
        "Mantras for peace and well-being",
        "Dr. Nitya D.",
        18,
        35,
        true
    ),
    CommunityQuestion(
        8,
        "History",
        "Influence of Vedic culture on ancient civilizations",
        "Prof. Rina V.",
        25,
        50
    ),
    CommunityQuestion(
        9,
        "Spirituality",
        "Exploring different paths to spiritual enlightenment",
        "Sameer A.",
        14,
        28
    ),
    CommunityQuestion(
        10,
        "Rituals",
        "The role of offerings in Vedic rituals",
        "Geeta P.",
        9,
        17,
        true
    ),
)

// Main Composable for the Vimarsh Manch (Community) Screen
@Composable
fun VimarshManchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF7F0))
    ) {
        CommunityTopBar()
        CommunitySearchAndFilter(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            selectedFilter = selectedFilter,
            onFilterSelected = { selectedFilter = it }
        )

        val filteredQuestions = remember(selectedFilter, searchQuery) {
            var questions = sampleQuestions
            if (searchQuery.isNotBlank()) {
                questions = questions.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            it.author.contains(searchQuery, ignoreCase = true) ||
                            it.category.contains(
                                searchQuery,
                                ignoreCase = true
                            ) // Corrected typo here
                }
            }

            when (selectedFilter) {
                "AI Answers" -> questions.filter { it.isAIAnswered }
                "Trending" -> questions.sortedByDescending { it.likes }
                "Community" -> questions.filter { !it.isAIAnswered }
                else -> questions
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredQuestions) { question ->
                CommunityQuestionCard(question = question) {
                    println("Clicked on question: ${question.title}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityTopBar() {
    TopAppBar(
        title = {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = "Vimarsh Manch",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Community wisdom & discussions",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
        },
        //colors = TopAppBarDefaults.topAppBarColors(headerOrangeGradient),
        modifier = Modifier.background( headerOrangeGradient )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitySearchAndFilter(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search questions...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFF57C00),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val filters = listOf("All", "Trending", "AI Answers", "Community")
            filters.forEach { filterText ->
                CommunityFilterChip(
                    text = filterText,
                    selected = selectedFilter == filterText,
                    onClick = { onFilterSelected(filterText) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityFilterChip(text: String, selected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = selected,
        enabled = true,
        onClick = onClick,
        label = {
            Text(
                text = text,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFFF57C00),
            selectedLabelColor = Color.White,
            containerColor = Color.White,
            labelColor = Color.Black,
            selectedLeadingIconColor = Color.White,
        ),
        border = if (!selected) BorderStroke(
            width = 1.dp,
            color = Color.Gray.copy(alpha = 0.5f)
        ) else null,
    )
}

@Composable
fun CommunityQuestionCard(question: CommunityQuestion, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFBE9E7)),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = question.category,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFE64A19),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                if (question.isAIAnswered) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E0F8)),
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.WorkspacePremium,
                                contentDescription = "AI Answered",
                                tint = Color(0xFF673AB7),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "AI",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF673AB7)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = question.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "by ${question.author}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconText(
                    icon = Icons.Filled.ChatBubbleOutline,
                    text = question.comments.toString(),
                    tint = Color.Gray
                )
                IconText(
                    icon = Icons.Filled.ThumbUp,
                    text = question.likes.toString(),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun IconText(icon: ImageVector, text: String, tint: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, style = MaterialTheme.typography.bodySmall, color = tint)
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun AwarenessScreenPreview() {
    // A placeholder for your actual VedaTheme implementation.
    // Ensure you have a proper theme defined in your project.
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(Color.Transparent)
    ) {
        Scaffold { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                AwarenessScreen(navController = rememberNavController())
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun VimarshManchScreenPreview() {
    // A placeholder for your actual VedaTheme implementation.
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(Color.Transparent)
    ) {
        Scaffold { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                VimarshManchScreen(navController = rememberNavController())
            }
        }
    }
}