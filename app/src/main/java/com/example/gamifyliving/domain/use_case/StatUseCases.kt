package com.example.gamifyliving.domain.use_case

data class StatUseCases(
    val addStat: AddStat,
    val updateStat: UpdateStat,
    val deleteStat: DeleteStat,
    val getStats: GetStats
)
