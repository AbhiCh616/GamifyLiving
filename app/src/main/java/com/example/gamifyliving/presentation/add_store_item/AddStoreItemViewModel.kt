package com.example.gamifyliving.presentation.add_store_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.use_case.AddStoreItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStoreItemViewModel @Inject constructor(
    private val addStoreItem: AddStoreItem
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var costCoins by mutableStateOf(0)
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onCostCoinsChange(newCostCoins: Int) {
        costCoins = newCostCoins
    }

    fun onSaveClicked() = viewModelScope.launch {
        addStoreItem(StoreItem(name = name, costCoins = costCoins))
    }

}