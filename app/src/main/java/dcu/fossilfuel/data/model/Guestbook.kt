package dcu.fossilfuel.data.model

data class Guestbook(
    val id: Int = 0, // 서버에서 생성된 아이디 (기본값 0)
    val content: String // 방명록 내용
)
