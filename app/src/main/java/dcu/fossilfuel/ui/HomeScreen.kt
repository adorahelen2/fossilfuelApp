package dcu.fossilfuel.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dcu.fossilfuel.data.api.ApiService
import dcu.fossilfuel.ui.components.*


@Composable
fun HomeScreen(apiService: ApiService) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TitleSection()
        StudySection()
        MemberSection()
        MemoriesSection()
      // GuestbookSection()
      //  FooterSection()


    }


}

@Composable
fun GuestbookSection(apiService: ApiService) {
    GuestbookScreen(apiService = apiService, modifier = Modifier.fillMaxSize())
}