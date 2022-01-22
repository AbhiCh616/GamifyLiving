package com.example.gamifyliving.presentation.add_stat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.presentation.util.getStatValueFromProgress
import kotlinx.coroutines.launch

class AddStatViewModel(
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

    fun onSaveClicked() = viewModelScope.launch {
        statRepository.addStat(Stat(name = name, value = getStatValueFromProgress(value)))
    }

}

class AddStatViewModelFactory(
    private val statRepository: StatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddStatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddStatViewModel(statRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
