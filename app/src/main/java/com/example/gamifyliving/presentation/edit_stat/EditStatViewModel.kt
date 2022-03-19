package com.example.gamifyliving.presentation.edit_stat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.use_case.DeleteStat
import com.example.gamifyliving.domain.use_case.GetStatById
import com.example.gamifyliving.domain.use_case.UpdateStat
import com.example.gamifyliving.presentation.util.getProgressFromStatValue
import com.example.gamifyliving.presentation.util.getStatValueFromSliderValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditStatViewModel @Inject constructor(
    private val getStatById: GetStatById,
    private val deleteStat: DeleteStat,
    private val updateStat: UpdateStat,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var sliderValue by mutableStateOf(0F)
        private set

    val sliderText
        get() = (sliderValue * 100).toInt().toString()

    private var selectedStat: Stat? = null

    init {
        savedStateHandle.get<Int>("stat_id")?.let { statId ->
            viewModelScope.launch {
                getStatById(statId)?.let { stat ->
                    selectedStat = stat
                    name = stat.name
                    sliderValue = getProgressFromStatValue(stat.value)
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onValueChange(newValue: Float) {
        sliderValue = newValue
    }

    fun onDeleteClicked() = viewModelScope.launch {
        selectedStat?.let { deleteStat(it) }
    }

    fun onSaveClicked() = viewModelScope.launch {
        val statValue = getStatValueFromSliderValue(sliderValue)
        val updatedStat = selectedStat?.copy(name = name, value = statValue)
        updatedStat?.let { updateStat(it) }
    }

}
