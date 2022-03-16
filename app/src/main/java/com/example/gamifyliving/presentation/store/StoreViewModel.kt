package com.example.gamifyliving.presentation.store

import androidx.lifecycle.ViewModel
import com.example.gamifyliving.domain.use_case.GetStoreItems
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    getStoreItems: GetStoreItems
) : ViewModel() {

    val storeItems = getStoreItems()

}
