package com.example.lifecycleapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/* ---------------------------------------------------------------------------
 *  พาเลตสี
 * ------------------------------------------------------------------------- */
private val Indigo = Color(0xFF4F46E5)
private val Violet = Color(0xFF7C3AED)
private val Sky = Color(0xFF0EA5E9)
private val Bg = Color(0xFFF5F6FA)
private val CardWhite = Color(0xFFFFFFFF)
private val Ink = Color(0xFF111827)
private val InkSoft = Color(0xFF4B5563)
private val Muted = Color(0xFF9CA3AF)
private val Hairline = Color(0xFFE5E7EB)
private val ChipBg = Color(0xFFEEF2FF)

private val HeaderBrush = Brush.linearGradient(listOf(Indigo, Violet, Sky))

/**
 * ข้อที่ 1: ระบบตรวจสอบ Activity Lifecycle
 * Override เมทอดวงจรชีวิตครบทั้ง 6 ขั้นตอน และแสดง Toast ด้วย applicationContext
 */
class MainActivity : ComponentActivity() {

    /** ฟังก์ชันกลางสำหรับแสดง Toast โดยใช้ applicationContext ตามข้อกำหนดของข้อสอบ */
    private fun showLifecycleToast(methodName: String) {
        Toast.makeText(applicationContext, methodName, Toast.LENGTH_SHORT).show()
    }

    // ---------- 1) onCreate() ----------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLifecycleToast("onCreate()")
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Bg) {
                    ProfileScreen()
                }
            }
        }
    }

    // ---------- 2) onStart() ----------
    override fun onStart() {
        super.onStart()
        showLifecycleToast("onStart()")
    }

    // ---------- 3) onResume() ----------
    override fun onResume() {
        super.onResume()
        showLifecycleToast("onResume()")
    }

    // ---------- 4) onPause() ----------
    override fun onPause() {
        super.onPause()
        showLifecycleToast("onPause()")
    }

    // ---------- 5) onStop() ----------
    override fun onStop() {
        super.onStop()
        showLifecycleToast("onStop()")
    }

    // ---------- 6) onDestroy() ----------
    override fun onDestroy() {
        super.onDestroy()
        showLifecycleToast("onDestroy()")
    }
}

/* ===========================================================================
 *  หน้าจอโปรไฟล์
 *  ภาพถ่ายใบหน้าจริงนำเข้าผ่าน Resource Drawable (res/drawable/profile_photo.jpg)
 * ========================================================================= */
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Bg)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GradientHeader()

        // เนื้อหาเลื่อนขึ้นทับส่วนหัว เพื่อให้รูปโปรไฟล์คร่อมขอบพอดี
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-84).dp)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvatarWithRing()

            Spacer(Modifier.height(18.dp))

            Text(
                text = "ภควัต ซามงค์",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Ink,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Pakawat Samong",
                fontSize = 15.sp,
                color = InkSoft,
                letterSpacing = 1.2.sp
            )

            Spacer(Modifier.height(12.dp))

            IdBadge(studentId = "67102122140")

            Spacer(Modifier.height(24.dp))

            InfoCard()

            Spacer(Modifier.height(16.dp))

            InterestCard()

            Spacer(Modifier.height(16.dp))

            HintCard()

            Spacer(Modifier.height(28.dp))
        }
    }
}

/** ส่วนหัวไล่ระดับสี พร้อมวงกลมตกแต่งโปร่งแสง */
@Composable
private fun GradientHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(HeaderBrush)
    ) {
        // วงกลมตกแต่ง
        Box(
            modifier = Modifier
                .offset(x = (-40).dp, y = (-30).dp)
                .size(160.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.10f))
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 50.dp, y = 20.dp)
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.08f))
        )

        Text(
            text = "PROFILE",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(alpha = 0.85f),
            letterSpacing = 5.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 56.dp)
        )
    }
}

/** รูปโปรไฟล์วงกลม พร้อมวงแหวนขาวและเงา */
@Composable
private fun AvatarWithRing() {
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(164.dp)
                .shadow(elevation = 12.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(CardWhite)
        )
        Image(
            painter = painterResource(id = R.drawable.profile_photo),
            contentDescription = "ภาพถ่ายใบหน้าของนักศึกษา",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(148.dp)
                .clip(CircleShape)
                .border(width = 2.dp, color = Hairline, shape = CircleShape)
        )
    }
}

/** ป้ายรหัสนักศึกษาแบบแคปซูล */
@Composable
private fun IdBadge(studentId: String) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(ChipBg)
            .padding(horizontal = 18.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(Indigo)
        )
        Spacer(Modifier.width(9.dp))
        Text(
            text = "รหัสนักศึกษา $studentId",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Indigo
        )
    }
}

/** การ์ดข้อมูลการศึกษา */
@Composable
private fun InfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
            InfoRow(Icons.Filled.Info, "สาขาวิชา", "เทคโนโลยีคอมพิวเตอร์และดิจิทัล")
            RowDivider()
            InfoRow(Icons.Filled.Person, "คณะ", "วิทยาศาสตร์และเทคโนโลยี")
            RowDivider()
            InfoRow(Icons.Filled.Place, "สถาบัน", "มหาวิทยาลัยราชภัฏสกลนคร")
            RowDivider()
            InfoRow(Icons.Filled.Star, "กิจกรรม", "ชมรมเทควันโดและฮับกิโด")
        }
    }
}

@Composable
private fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(ChipBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Indigo,
                modifier = Modifier.size(19.dp)
            )
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = label, fontSize = 12.sp, color = Muted)
            Spacer(Modifier.height(3.dp))
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Ink
            )
        }
    }
}

@Composable
private fun RowDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Hairline)
    )
}

/** การ์ดความสนใจ */
@Composable
private fun InterestCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "ความสนใจ",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Ink
            )
            Spacer(Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Chip("Android")
                Chip("Kotlin")
                Chip("Data Mining")
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Chip("Jetpack Compose")
                Chip("GIS")
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Chip("Mixed Reality")
                Chip("E-Commerce")
            }
        }
    }
}

@Composable
private fun Chip(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = Indigo,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(ChipBg)
            .padding(horizontal = 13.dp, vertical = 7.dp)
    )
}

/** การ์ดคำแนะนำการทดสอบ */
@Composable
private fun HintCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = ChipBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = null,
                tint = Indigo,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(14.dp))
            Column {
                Text(
                    text = "ทดสอบ Activity Lifecycle",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Ink
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = "หมุนหน้าจอเพื่อดู Toast แจ้งลำดับทั้ง 6 ขั้นตอน",
                    fontSize = 12.sp,
                    color = InkSoft
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}