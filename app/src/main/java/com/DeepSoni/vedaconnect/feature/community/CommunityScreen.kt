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
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Search
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
import com.DeepSoni.vedaconnect.ui.theme.VedaTheme

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

// Main Composable for the Community Screen
@Composable
fun CommunityScreen(navController: NavController) {
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
                            it.category.contains(searchQuery, ignoreCase = true)
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
            Column (modifier = Modifier.padding(bottom = 16.dp)) {
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
        // Apply horizontalScroll to the Row for filter chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()), // <--- Applied horizontalScroll here
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Use spacedBy for consistent spacing
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

// Composable for a single filter chip
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
        // No horizontal padding on modifier here, let Row's Arrangement.spacedBy handle it
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
fun CommunityScreenPreview() {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            CommunityScreen(navController = rememberNavController())
        }
    }
}