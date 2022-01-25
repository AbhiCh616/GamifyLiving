package com.example.gamifyliving.presentation.edit_stat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.presentation.util.getProgressFromStatValue
import com.example.gamifyliving.presentation.util.getStatValueFromProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditStatViewModel @Inject constructor(
    private val statRepository: StatRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var value by mutableStateOf(0F)
        private set

    private var selectedStat: Stat? = null

    init {
        savedStateHandle.get<Int>("stat_id")?.let { statId ->
            viewModelScope.launch {
                statRepository.getStatById(statId)?.let { stat ->
                    selectedStat = stat
                    name = stat.name
                    value = getProgressFromStatValue(stat.value)
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onValueChange(newValue: Float) {
        value = newValue
    }

    fun onDelete() = viewModelScope.launch {
        selectedStat?.let { statRepository.deleteStat(it) }
    }

    fun onSaveClicked() = viewModelScope.launch {
        val updatedStat = selectedStat?.copy(name = name, value = getStatValueFromProgress(value))
        updatedStat?.let { statRepository.updateStat(it) }
    }

}
