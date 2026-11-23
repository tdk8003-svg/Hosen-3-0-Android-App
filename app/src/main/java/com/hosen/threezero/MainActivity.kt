package com.hosen.threezero

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                HosenApp(
                    onOpenWebsite = {
                        openLink("https://hosen30.netlify.app")
                    },
                    onOpenYouTube = {
                        openLink("https://www.youtube.com/@hosen.hiron.1")
                    }
                )
            }
        }
    }

    private fun openLink(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
fun HosenApp(
    onOpenWebsite: () -> Unit,
    onOpenYouTube: () -> Unit
) {
    val darkBackground = Color(0xFF10131D)
    val cardColor = Color(0xFF20283A)
    val accent = Color(0xFF38BDF8)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "HOSEN 3.0",
                color = accent,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Welcome to my digital world",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            MenuCard("Home", cardColor)
            MenuCard("Stories", cardColor)
            MenuCard("Projects", cardColor)
            MenuCard("Contact", cardColor)

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = onOpenWebsite,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent
                )
            ) {
                Text("Visit My Website", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onOpenYouTube,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3333)
                )
            ) {
                Text("My YouTube Channel", color = Color.White)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "© 2026 Hosen 3.0",
                color = Color.LightGray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun MenuCard(title: String, cardColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(
                cardColor,
                RoundedCornerShape(12.dp)
            )
            .padding(18.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
