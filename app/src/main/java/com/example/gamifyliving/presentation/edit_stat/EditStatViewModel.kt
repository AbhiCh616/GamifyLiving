package com.example.gamifyliving.presentation.edit_stat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamifyliving.domain.repository.StatRepository

class EditStatViewModel(
    private val statRepository: StatRepository
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var value by mutableStateOf(0F)
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onValueChange(newValue: Float) {
        value = newValue
    }

    fun onDelete() {

    }

    fun onSaveClicked() {

    }

}

class EditStatViewModelFactory(
    private val statRepository: StatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditStatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditStatViewModel(statRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
