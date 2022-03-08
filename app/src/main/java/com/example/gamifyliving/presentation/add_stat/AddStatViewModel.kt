package com.example.gamifyliving.presentation.add_stat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.use_case.AddStat
import com.example.gamifyliving.presentation.util.getStatValueFromSliderValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStatViewModel @Inject constructor(
    private val addStat: AddStat
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
        addStat(Stat(name = name, value = getStatValueFromSliderValue(value)))
    }

}
