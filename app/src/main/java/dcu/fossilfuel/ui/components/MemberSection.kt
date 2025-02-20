package dcu.fossilfuel.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dcu.fossilfuel.R

@Composable
fun MemberSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Member", style = MaterialTheme.typography.headlineMedium)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(painter = painterResource(id = R.drawable.professor), contentDescription = "Professor")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(painter = painterResource(id = R.drawable.member1), contentDescription = "Member1")
            Image(painter = painterResource(id = R.drawable.member2), contentDescription = "Member2")
            Image(painter = painterResource(id = R.drawable.member3), contentDescription = "Member3")
            Image(painter = painterResource(id = R.drawable.member4), contentDescription = "Member4")
        }
    }
}
