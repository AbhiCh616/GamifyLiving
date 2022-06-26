package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.CoinRepository
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class ChangeTodoStatus @Inject constructor(
    private val statRepository: StatRepository,
    private val coinRepository: CoinRepository,
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) = runSuspendCatching {

        // Change (completion) status
        val updatedTodo = todo.copy(status = !todo.status)
        todoRepository.update(todo = updatedTodo)

        // Increase or decrease the coins, that user has, based on (un)check status
        coinRepository.observe().collect { coins ->
            var updatedCoins = coins
            if (updatedTodo.status) {
                updatedCoins += todo.coinsReward
            } else {
                updatedCoins -= todo.coinsReward
            }
            coinRepository.update(coins = updatedCoins)
        }

        // Increase or decrease the stats based on (un)check status
        todo.rewards?.forEach { reward ->
            val initialStat = statRepository.getById(id = reward.statId)!!
            val finalStat =
                if (updatedTodo.status) {
                    initialStat.copy(value = initialStat.value + reward.points)
                } else {
                    initialStat.copy(value = initialStat.value - reward.points)
                }
            statRepository.update(stat = finalStat)
        }

    }
}
