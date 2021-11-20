package com.example.gamifyliving.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.repository.StatRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val statRepository: StatRepository
) : ViewModel() {
    val stats = statRepository.allStats.asLiveData()

    fun insertStat(stat: Stat) = viewModelScope.launch {
        statRepository.addStat(stat)
    }
}

class ProfileViewModelFactory(
    private val statRepository: StatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(statRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}