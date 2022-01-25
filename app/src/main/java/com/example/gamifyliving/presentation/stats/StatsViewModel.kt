package com.example.gamifyliving.presentation.stats

import androidx.lifecycle.ViewModel
import com.example.gamifyliving.domain.repository.StatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    statRepository: StatRepository
) : ViewModel() {

    val stats = statRepository.observeStats()

}
