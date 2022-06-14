package com.example.gamifyliving.presentation.edit_store_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.use_case.DeleteStoreItem
import com.example.gamifyliving.domain.use_case.GetStoreItemById
import com.example.gamifyliving.domain.use_case.UpdateStoreItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditStoreItemViewModel @Inject constructor(
    private val getStoreItemById: GetStoreItemById,
    private val deleteStoreItem: DeleteStoreItem,
    private val updateStoreItem: UpdateStoreItem,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var costCoins by mutableStateOf("")
        private set

    private var selectedStoreItem: StoreItem? = null

    init {
        savedStateHandle.get<Int>("store_item_id")?.let { storeItemId ->
            viewModelScope.launch {
                getStoreItemById(id = storeItemId)
                    .onSuccess { storeItem ->
                        storeItem?.let {
                            selectedStoreItem = storeItem
                            name = storeItem.name
                            costCoins = storeItem.costCoins.toString()
                        }

                    }
            }
        }
    }

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onCostCoinsChange(newCostCoins: String) {
        costCoins = newCostCoins
    }

    fun onDeleteClicked() = viewModelScope.launch {
        selectedStoreItem?.let { deleteStoreItem(it) }
    }

    fun onSaveClicked() = viewModelScope.launch {
        val updatedStoreItem = selectedStoreItem?.copy(name = name, costCoins = costCoins.toInt())
        updatedStoreItem?.let { updateStoreItem(it) }
    }

}
