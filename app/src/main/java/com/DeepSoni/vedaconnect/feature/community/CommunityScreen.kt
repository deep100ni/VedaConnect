package com.DeepSoni.vedaconnect.feature.community

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

// Assuming VedaTheme and its Orange color are defined elsewhere in your project.
// For demonstration, I'll include a placeholder definition here.
// In a real project, this would likely be in a 'ui.theme' package.
object VedaTheme {
    val Orange = Color(0xFFF57C00) // Define your primary orange color
}

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
    val description: String? = null, // For recent articles
    val videoUrl: String? = null // Add this field
)

val sampleArticles = listOf(
    Article(1, "History", "Rigveda Manuscripts: A Journey Through Time", 12, "2.4K views", isFeatured = true,
        videoUrl = "https://youtu.be/YXfRsS8MzX4"), // Replace with a real link
    Article(2, "History", "The Historical Context of Rigveda: Dating and Discovery", 8, "", description = "Exploring archaeological evidence and scholarly consensus on Rigveda's origins..."),
    Article(3, "Law", "Vedic Heritage and Constitutional Rights in India", 6, "", description = "Understanding how ancient wisdom influences modern legal frameworks..."),
    Article(4, "Philosophy", "The Philosophy of Rita: Cosmic Order in Vedic Thought", 10, "", description = "Deep dive into the concept of universal truth and cosmic harmony..."),
    Article(5, "Philosophy", "Sanskrit Mantras: Science Behind Sound Vibrations", 7, "", description = "Delving into the scientific aspects of Vedic chants and their effects..."),
    Article(6, "Culture", "Impact of Vedic Traditions on Indian Festivals", 9, "", description = "Tracing the roots of popular Indian festivals back to Vedic customs..."),
    Article(7, "History", "Lost Cities of Saraswati: Unraveling Ancient Civilizations", 15, "", description = "Investigating the archaeological significance of the mythical Saraswati River..."),
    Article(8, "Law", "Justice and Ethics in Ancient Vedic Jurisprudence", 11, "", description = "Examining the principles of justice and morality as laid out in Vedic legal texts...")
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
                        modifier = Modifier.padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Samvaad",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Awareness & enlightenment",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VedaTheme.Orange)
            )
        },
        containerColor = Color(0xFFFFF7F0) // Background color matching the image
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Category Filter Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Featured Article
                val featuredArticle = sampleArticles.firstOrNull { it.isFeatured && (selectedCategory == "All" || it.category == selectedCategory) }
                if (featuredArticle != null) {
                    item {
                        Text(
                            text = "Featured",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.Black
                        )
                        FeaturedArticleCard(article = featuredArticle) {
                            println("Clicked on featured article: ${featuredArticle.title}")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                // Recent Articles
                val recentArticles = sampleArticles.filter { !it.isFeatured && (selectedCategory == "All" || it.category == selectedCategory) }
                if (recentArticles.isNotEmpty()) {
                    item {
                        Text(
                            text = "Recent Articles",
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
                } else if (featuredArticle == null) {
                    item {
                        Text(
                            text = "No articles found for selected category.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                        )
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
fun FeaturedArticleCard(article: Article, onClick: () -> Unit) {
    val context = LocalContext.current // Get the current context

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Placeholder for image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ChatBubbleOutline,
                    contentDescription = "Article Image Placeholder",
                    modifier = Modifier.size(64.dp),
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                // Category Tag
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
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.ChatBubbleOutline,
                        contentDescription = "Read Time",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${article.readTimeMinutes} min read",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Views",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = article.views,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                // Vertical arrangement of buttons
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            article.videoUrl?.let { url ->
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = VedaTheme.Orange),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(vertical = 12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = article.videoUrl != null // Disable button if no video URL
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play Video",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Play Video",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecentArticleCard(article: Article, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
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
            // Placeholder for image
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ChatBubbleOutline, // Generic icon
                    contentDescription = "Article Image Placeholder",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.ChatBubbleOutline, // Placeholder for clock
                            contentDescription = "Read Time",
                            tint = Color.Gray,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${article.readTimeMinutes} min",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                article.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 2
                    )
                }
            }
            // Save, Share, and See icons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 16.dp)
            ) {
                IconText(Icons.Filled.BookmarkBorder, "Save", Color.Gray) // Placeholder for Save
                IconText(Icons.Filled.Share, "Share", Color.Gray) // Placeholder for Share
                IconText(Icons.Filled.Search, "Read", Color.Gray) // Placeholder for See
            }
        }
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
    CommunityQuestion(1, "Philosophy", "What is the meaning of 'Dharma' in Rigveda?", "Priya K.", 12, 24, true),
    CommunityQuestion(2, "Practice", "How to incorporate daily Vedic practices in modern life?", "Rahul M.", 8, 18, true),
    CommunityQuestion(3, "History", "Rigveda and modern scientific discoveries", "Dr. Anya S.", 15, 30),
    CommunityQuestion(4, "Spirituality", "Understanding the concept of Atman and Brahman", "Vivek J.", 20, 45, true),
    CommunityQuestion(5, "Rituals", "Significance of Agnihotra in daily life", "Maya R.", 7, 15),
    CommunityQuestion(6, "Philosophy", "Interpreting the concept of 'Karma' in Vedic texts", "Ankit S.", 10, 20),
    CommunityQuestion(7, "Practice", "Mantras for peace and well-being", "Dr. Nitya D.", 18, 35, true),
    CommunityQuestion(8, "History", "Influence of Vedic culture on ancient civilizations", "Prof. Rina V.", 25, 50),
    CommunityQuestion(9, "Spirituality", "Exploring different paths to spiritual enlightenment", "Sameer A.", 14, 28),
    CommunityQuestion(10, "Rituals", "The role of offerings in Vedic rituals", "Geeta P.", 9, 17, true),
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
                            it.category.contains(searchQuery, ignoreCase = true) // Corrected typo here
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
        colors = TopAppBarDefaults.topAppBarColors(containerColor = VedaTheme.Orange)
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
        colorScheme = MaterialTheme.colorScheme.copy(primary = VedaTheme.Orange)
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
        colorScheme = MaterialTheme.colorScheme.copy(primary = VedaTheme.Orange)
    ) {
        Scaffold { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                VimarshManchScreen(navController = rememberNavController())
            }
        }
    }
}