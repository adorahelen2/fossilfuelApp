package dcu.fossilfuel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dcu.fossilfuel.data.api.ApiClient
import dcu.fossilfuel.data.api.ApiService // Retrofit API 인터페이스
import dcu.fossilfuel.data.model.Guestbook
import dcu.fossilfuel.ui.HomeScreen
import dcu.fossilfuel.ui.theme.FossilfuelTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        apiService = ApiClient.getClient().create(ApiService::class.java)

        setContent {
            FossilfuelTheme {
                var currentScreen by remember { mutableStateOf("Home") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            // 화면 전환 버튼
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(onClick = { currentScreen = "Home" }) {
                                    Text("메인")
                                }
                                Button(onClick = { currentScreen = "Guestbook" }) {
                                    Text("방명록")
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // 현재 화면에 따라 다른 Composable 표시
                            when (currentScreen) {
                                "Home" -> HomeScreen(apiService = apiService) // HomeScreen 호출
                                "Guestbook" -> GuestbookScreen(apiService = apiService, modifier = Modifier.fillMaxSize()) // 방명록 호출
                            }
                        }
                    }
                )
            }
        }
    }

}
@Composable
fun GuestbookScreen(apiService: ApiService, modifier: Modifier) {
    var guestbookEntries by remember { mutableStateOf(listOf<Guestbook>()) }
    var newEntryText by remember { mutableStateOf(TextFieldValue("")) }
    var currentPage by remember { mutableStateOf(1) }
    val entriesPerPage = 6

    // 방명록 목록 가져오기
    LaunchedEffect(Unit) {
        fetchGuestbookEntries(apiService) { entries ->
            guestbookEntries = entries
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("방명록", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        // 방명록 입력
        OutlinedTextField(
            value = newEntryText,
            onValueChange = { newEntryText = it },
            label = { Text("새 방명록 작성") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                addGuestbookEntry(apiService, newEntryText.text) {
                    newEntryText = TextFieldValue("")
                    fetchGuestbookEntries(apiService) { entries ->
                        guestbookEntries = entries
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("작성")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 방명록 목록 표시
        val start = (currentPage - 1) * entriesPerPage
        val end = (start + entriesPerPage).coerceAtMost(guestbookEntries.size)
        val pageEntries = guestbookEntries.subList(start, end)

        LazyColumn {
            items(pageEntries) { entry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = entry.content,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }

        // 페이지네이션
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { if (currentPage > 1) currentPage-- },
                enabled = currentPage > 1
            ) { Text("이전") }

            Spacer(modifier = Modifier.width(16.dp))

            Text("페이지 $currentPage", fontSize = 16.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { if (currentPage * entriesPerPage < guestbookEntries.size) currentPage++ },
                enabled = currentPage * entriesPerPage < guestbookEntries.size
            ) { Text("다음") }
        }
    }
}

// 방명록 가져오기
private fun fetchGuestbookEntries(apiService: ApiService, onResult: (List<Guestbook>) -> Unit) {
    apiService.getGuestbookEntries().enqueue(object : Callback<List<Guestbook>> {
        override fun onResponse(call: Call<List<Guestbook>>, response: Response<List<Guestbook>>) {
            if (response.isSuccessful) {
                onResult(response.body() ?: emptyList())
            } else {
                onResult(emptyList())
            }
        }

        override fun onFailure(call: Call<List<Guestbook>>, t: Throwable) {
            onResult(emptyList())
        }
    })
}

// 방명록 추가
private fun addGuestbookEntry(apiService: ApiService, content: String, onResult: () -> Unit) {
    val newEntry = Guestbook(content = content)
    apiService.addGuestbookEntry(newEntry).enqueue(object : Callback<Guestbook> {
        override fun onResponse(call: Call<Guestbook>, response: Response<Guestbook>) {
            if (response.isSuccessful) {
                onResult()
            }
        }

        override fun onFailure(call: Call<Guestbook>, t: Throwable) {}
    })
}