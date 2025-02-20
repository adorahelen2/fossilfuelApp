package dcu.fossilfuel.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StudySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("화석연료", style = MaterialTheme.typography.headlineMedium)
        Text("설립 목적")
        Text("• 개발자로서 지속적인 성장과 동기부여를 위한 환경 조성")
        Text("• 사회에 나아갈 방향성을 함께 고민하고 준비")

        Text("활동 내용")
        Text("• 자기소개서 및 이력서 피드백 제공")
        Text("• 코딩 테스트 연습 (하루 1문제 또는 주 2문제)")
        Text("• 채용 공고 공유 및 지원 전략 검토")
        Text("• 자격증 준비 (정보처리기사, 정보보안기사 등)")
        Text("• 선후배 간 자유로운 멘토링 및 네트워킹")
        Text("• 강의 및 학습 자료 공유, 효율적인 공부 환경 제공")
        Text("• 주 5일 자유롭게 참여 (09:00 ~ 22:00)")
        Text("• 프로젝트 기획 및 피드백 지원")
    }
}