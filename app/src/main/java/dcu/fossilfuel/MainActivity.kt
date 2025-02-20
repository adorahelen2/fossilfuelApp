package dcu.fossilfuel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dcu.fossilfuel.api.ApiService // Retrofit API 인터페이스
import dcu.fossilfuel.api.ApiClient // Retrofit 클라이언트
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        GuestbookScreen(modifier = Modifier.padding(innerPadding), apiService)
                    }
                )
            }
        }
    }
}

@Composable
fun GuestbookScreen(modifier: Modifier = Modifier, apiService: ApiService) {
    var guestbookEntries by remember { mutableStateOf(listOf<Guestbook>()) }
    var newEntryText by remember { mutableStateOf(TextFieldValue("")) }

    // 방명록 목록 조회
    fetchGuestbookEntries(apiService) { entries ->
        guestbookEntries = entries
    }

    Column(modifier = modifier.padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(guestbookEntries) { entry ->
                Text(text = entry.content, modifier = Modifier.padding(vertical = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = newEntryText,
            onValueChange = { newEntryText = it },
            label = { Text("새 방명록 항목 추가") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(autoCorrect = true)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                addGuestbookEntry(apiService, newEntryText.text) {
                    newEntryText = TextFieldValue("") // 입력 필드 초기화
                    fetchGuestbookEntries(apiService) { entries ->
                        guestbookEntries = entries // 방명록 목록 업데이트
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("추가")
        }
    }
}

// 방명록 목록 조회 함수
private fun fetchGuestbookEntries(apiService: ApiService, onResult: (List<Guestbook>) -> Unit) {
    apiService.getGuestbookEntries().enqueue(object : Callback<List<Guestbook>> {
        override fun onResponse(call: Call<List<Guestbook>>, response: Response<List<Guestbook>>) {
            if (response.isSuccessful) {
                onResult(response.body() ?: emptyList())
            } else {
                onResult(emptyList()) // 오류 발생 시 빈 목록 반환
            }
        }

        override fun onFailure(call: Call<List<Guestbook>>, t: Throwable) {
            onResult(emptyList()) // 실패 시 빈 목록 반환
        }
    })
}

// 방명록 추가 함수
private fun addGuestbookEntry(apiService: ApiService, content: String, onResult: () -> Unit) {
    val newEntry = Guestbook(content = content)
    apiService.addGuestbookEntry(newEntry).enqueue(object : Callback<Guestbook> {
        override fun onResponse(call: Call<Guestbook>, response: Response<Guestbook>) {
            if (response.isSuccessful) {
                onResult() // 성공 시 콜백 호출
            }
        }

        override fun onFailure(call: Call<Guestbook>, t: Throwable) {
            // 실패 처리 (예: 로그 출력)
        }
    })
}

@Preview(showBackground = true)
@Composable
fun GuestbookScreenPreview() {
    FossilfuelTheme {
        GuestbookScreen(apiService = ApiClient.getClient().create(ApiService::class.java))
    }
}
