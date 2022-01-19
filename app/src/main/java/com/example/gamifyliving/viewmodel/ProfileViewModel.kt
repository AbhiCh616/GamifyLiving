package com.example.gamifyliving.viewmodel

import androidx.lifecycle.*
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.repository.StatRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val statRepository: StatRepository
) : ViewModel() {

    fun addStat(stat: Stat) = viewModelScope.launch {
        statRepository.addStat(stat)
    }

    private fun updateStat(stat: Stat) = viewModelScope.launch {
        statRepository.updateStat(stat)
    }

    fun updateStatValues(stat: Stat, statName: String, statValue: Int) {
        val newStat = stat.copy(name = statName, value = statValue)
        updateStat(newStat)
    }

    fun deleteStat(stat: Stat) = viewModelScope.launch {
        statRepository.deleteStat(stat)
    }

    fun getAllStats(): LiveData<List<Stat>> = statRepository.observeStats().asLiveData()

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
