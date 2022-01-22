package com.example.gamifyliving.ui.screen.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamifyliving.domain.repository.StatRepository

class StatsViewModel(
    statRepository: StatRepository
) : ViewModel() {

    val stats = statRepository.observeStats()

}

class StatsViewModelFactory(
    private val statRepository: StatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StatsViewModel(statRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
