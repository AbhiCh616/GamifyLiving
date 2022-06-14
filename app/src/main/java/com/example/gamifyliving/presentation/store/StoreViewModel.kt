package com.example.gamifyliving.presentation.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.use_case.BuyStoreItem
import com.example.gamifyliving.domain.use_case.GetStoreItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    getStoreItems: GetStoreItems,
    private val buyStoreItem: BuyStoreItem
) : ViewModel() {

    val storeItems = getStoreItems()

    var isConfirmBuyDialogVisible by mutableStateOf(false)
        private set

    var storeItemToBuy by mutableStateOf(StoreItem("", 0))
        private set

    fun dismissConfirmBuyDialog() {
        isConfirmBuyDialogVisible = false
    }

    fun onBuyButtonClick(storeItem: StoreItem) {
        storeItemToBuy = storeItem
        isConfirmBuyDialogVisible = true
    }

    fun onAcceptConfirmBuyDialog() = viewModelScope.launch {
        buyStoreItem(storeItemToBuy)
        isConfirmBuyDialogVisible = false
    }

}
