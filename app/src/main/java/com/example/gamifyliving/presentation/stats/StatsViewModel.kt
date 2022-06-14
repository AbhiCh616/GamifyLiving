package com.example.gamifyliving.presentation.stats

import androidx.lifecycle.ViewModel
import com.example.gamifyliving.domain.use_case.query.GetCoins
import com.example.gamifyliving.domain.use_case.query.GetStats
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    getStats: GetStats,
    getCoins: GetCoins
) : ViewModel() {

    val stats = getStats()
    val coins = getCoins()

}
