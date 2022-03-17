package com.example.gamifyliving.presentation.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.use_case.GetStoreItems
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    getStoreItems: GetStoreItems
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

    fun onAcceptConfirmBuyDialog() {
        isConfirmBuyDialogVisible = false
    }

}
