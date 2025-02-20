```

/project-root
  ├── app/src/main/java/com/example/app
  │   ├── data/          # 데이터 관련 (Entity, Repository, API)
  │   │   ├── model/     # DTO 및 데이터 모델 클래스
  │   │   │   ├── Guestbook.kt
  │   │   │   ├── LoginRequest.kt
  │   │   │   ├── LoginResponse.kt
  │   │   ├── repository/ # 데이터 접근 계층 (RoomDB, Retrofit 등)
  │   │   │   ├── GuestbookRepository.kt
  │   │   │   ├── AuthRepository.kt
  │   │   ├── api/        # Retrofit 관련 코드 추가
  │   │   │   ├── ApiService.kt
  │   │   │   ├── RetrofitClient.kt
  │   ├── ui/            # UI 관련 (Activity, Fragment, ViewModel)
  │   │   ├── main/      # 메인 화면 관련 UI
  │   │   │   ├── MainActivity.kt
  │   │   ├── profile/   # 프로필 관련 UI
  │   │   │   ├── ProfileScreen.kt
  │   │   ├── login/     # 로그인 관련 UI
  │   │   │   ├── LoginScreen.kt
  │   │   ├── guestbook/ # 방명록 관련 UI
  │   │   │   ├── GuestbookScreen.kt
  │   ├── viewmodel/     # MVVM 패턴의 ViewModel 클래스
  │   │   ├── GuestbookViewModel.kt
  │   │   ├── LoginViewModel.kt
  ├── app/src/main/res/  # XML 레이아웃 및 리소스 파일
  ├── app/src/main/AndroidManifest.xml # 앱 설정 파일
  ├── build.gradle.kts  # Gradle 빌드 설정 파일

```