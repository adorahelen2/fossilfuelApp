package dcu.fossilfuel.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import dcu.fossilfuel.R

@Composable
fun MemberSection() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Member", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.professor),
                    contentDescription = "Professor",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 20.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center
            ) {
                listOf(
                    R.drawable.member1,
                    R.drawable.member2,
                    R.drawable.member3,
                    R.drawable.member4,
                    R.drawable.member5,
                    R.drawable.member6,
                    R.drawable.member7,
                    R.drawable.member9,
                    R.drawable.member10,
                    R.drawable.lastmember





                ).forEach { member ->
                    Image(
                        painter = painterResource(id = member),
                        contentDescription = "Member",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(horizontal = 10.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
