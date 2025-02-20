package dcu.fossilfuel.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("컴퓨터소프트웨어학부\n학과 동아리", style = MaterialTheme.typography.headlineMedium)
        Text("Welcome to our club website!")

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        //    Button(onClick = { /* 로그인 이동 */ }) { Text("로그인") }
        //    Button(onClick = { /* 회원가입 이동 */ }) { Text("회원가입") }
        //    Button(onClick = { /* 챗봇 이동 */ }) { Text("AI 챗봇") }
        }
    }
}