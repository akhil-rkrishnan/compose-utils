package ak.composeapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class PresentationViewModel : ViewModel() {

    var presentationFlow: Flow<Int> = emptyFlow()
    var count = 0
    var initalString by mutableStateOf("String ${count++}")



    fun updateString() {
        viewModelScope.launch {
            repeat(1000) {
                delay(50)
                initalString += "\nString ${count++}"
                presentationFlow = flow { emit(1000 + count) }
            }
        }

    }
}