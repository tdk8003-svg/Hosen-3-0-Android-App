package com.hosen.threezero

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF080D19)
private val Panel = Color(0xFF141E32)
private val Blue = Color(0xFF38BDF8)
private val White = Color(0xFFF4F7FF)
private val Muted = Color(0xFF9AAAC2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HosenApp(
                openLink = { url ->
                    try {
                        startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            )
        }
    }
}

@Composable
fun HosenApp(openLink: (String) -> Unit) {
    var page by remember { mutableStateOf("Home") }
    var bangla by remember { mutableStateOf(true) }

    val pages = listOf(
        "Home", "Profile", "YouTube", "Projects",
        "Stories", "Contact", "Gallery", "Music", "Admin"
    )

    val labels = mapOf(
        "Home" to "হোম",
        "Profile" to "প্রোফাইল",
        "YouTube" to "ইউটিউব",
        "Projects" to "প্রজেক্ট",
        "Stories" to "গল্প",
        "Contact" to "যোগাযোগ",
        "Gallery" to "ফটো গ্যালারি",
        "Music" to "মিউজিক প্লেয়ার",
        "Admin" to "অ্যাডমিন প্যানেল"
    )

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Background,
            surface = Panel,
            primary = Blue,
            onBackground = White,
            onSurface = White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "HOSEN 3.0",
                        color = Blue,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        if (bangla) "আমার ডিজিটাল দুনিয়া" else "MY DIGITAL WORLD",
                        color = Muted,
                        fontSize = 11.sp
                    )
                }

                Button(
                    onClick = { bangla = !bangla },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Panel
                    )
                ) {
                    Text(if (bangla) "EN" else "বাংলা", color = Blue)
                }
            }

            HorizontalDivider(color = Panel)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp)
            ) {
                Text(
                    if (bangla) labels[page] ?: page else page.uppercase(),
                    color = White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                when (page) {
                    "Home" -> {
                        Text(
                            if (bangla) "স্বাগতম Hosen 3.0-এ!" else "WELCOME TO HOSEN 3.0!",
                            color = Blue,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            if (bangla)
                                "এটি আমার ব্যক্তিগত ডিজিটাল জগৎ। এখানে আমার পরিচয়, কাজ, গল্প ও পছন্দের বিষয়গুলো থাকবে।"
                            else
                                "My personal digital world. Explore my profile, projects, stories and more.",
                            color = Muted,
                            fontSize = 15.sp
                        )
                        Spacer(Modifier.height(20.dp))
                        InfoCard(
                            "PERSONAL BRAND",
                            if (bangla) "নিজের পরিচয়, নিজের স্টাইল।" else "My identity. My style."
                        )
                        InfoCard(
                            "EXPLORE",
                            if (bangla) "নিচের মেনু থেকে পছন্দের বিভাগ বেছে নাও।"
                            else "Choose a section from the menu below."
                        )
                    }

                    "Profile" -> {
                        InfoCard(
                            "HOSEN",
                            if (bangla) "আমি Hosen। এটি আমার ব্যক্তিগত প্রোফাইল।"
                            else "I am Hosen. Welcome to my personal profile."
                        )
                        InfoCard(
                            "ABOUT ME",
                            if (bangla) "এখানে পরে তোমার নিজের পরিচয়, ছবি ও বায়ো যোগ করব।"
                            else "Your personal bio and photo can be added here."
                        )
                    }

                    "YouTube" -> {
                        InfoCard(
                            "MY YOUTUBE CHANNEL",
                            "@hosen.hiron.1"
                        )
                        ActionButton(
                            if (bangla) "YouTube চ্যানেল খোলো" else "OPEN YOUTUBE CHANNEL"
                        ) {
                            openLink("https://www.youtube.com/@hosen.hiron.1")
                        }
                    }

                    "Projects" -> {
                        InfoCard(
                            "MY PROJECTS",
                            if (bangla) "তোমার তৈরি ওয়েবসাইট, অ্যাপ ও অন্যান্য কাজ এখানে দেখানো যাবে।"
                            else "Your websites, apps and other work will appear here."
                        )
                    }

                    "Stories" -> {
                        InfoCard(
                            "MY STORIES",
                            if (bangla) "তোমার লেখা গল্প, ভাবনা ও অভিজ্ঞতা এখানে থাকবে।"
                            else "Your stories, thoughts and experiences will appear here."
                        )
                    }

                    "Contact" -> {
                        InfoCard(
                            "GET IN TOUCH",
                            if (bangla) "আমার সঙ্গে যোগাযোগ করতে নিচের বাটন ব্যবহার করো।"
                            else "Use the button below to get in touch."
                        )
                        ActionButton(
                            if (bangla) "ওয়েবসাইট খোলো" else "VISIT WEBSITE"
                        ) {
                            openLink("https://hosen30.netlify.app")
                        }
                    }

                    "Gallery" -> {
                        InfoCard(
                            "PHOTO GALLERY",
                            if (bangla) "এখানে এখনো ছবি যোগ করা হয়নি। পরে তোমার ছবি যুক্ত করব।"
                            else "No photos added yet. Your photos can be added here."
                        )
                    }

                    "Music" -> {
                        InfoCard(
                            "MUSIC PLAYER",
                            if (bangla) "এখানে এখনো গান যুক্ত করা হয়নি। অডিও ফাইল যোগ করলে প্লেয়ার চালু করা যাবে।"
                            else "No audio added yet. Add an audio file to enable playback."
                        )
                    }

                    "Admin" -> {
                        InfoCard(
                            "ADMIN PANEL",
                            if (bangla)
                                "এটি এখন ডেমো স্ক্রিন। নিরাপদ Admin Login ও অনলাইন কন্ট্রোল পরে Backend দিয়ে তৈরি করতে হবে।"
                            else
                                "This is a demo screen. Secure admin login and online controls require a backend."
                        )
                        Text(
                            if (bangla) "পরবর্তী ধাপ: নিরাপদ লগইন তৈরি" else "NEXT: BUILD SECURE LOGIN",
                            color = Blue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))
                Text(
                    "© 2026 HOSEN 3.0",
                    color = Muted,
                    fontSize = 12.sp
                )
            }

            HorizontalDivider(color = Panel)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 150.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                pages.chunked(3).forEach { group ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        group.forEach { item ->
                            val selected = page == item
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 4.dp)
                                    .background(
                                        if (selected) Blue.copy(alpha = 0.16f)
                                        else Panel,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable { page = item }
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (bangla) labels[item] ?: item else item,
                                    color = if (selected) Blue else Muted,
                                    fontSize = 11.sp,
                                    fontWeight = if (selected) FontWeight.Bold
                                    else FontWeight.Normal
                                )
                            }
                        }
                        repeat(3 - group.size) {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoCard(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(Panel, RoundedCornerShape(18.dp))
            .padding(18.dp)
    ) {
        Text(
            title,
            color = Blue,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            description,
            color = White,
            fontSize = 15.sp
        )
    }
}

@Composable
fun ActionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(text, color = Background, fontWeight = FontWeight.Bold)
    }
}
