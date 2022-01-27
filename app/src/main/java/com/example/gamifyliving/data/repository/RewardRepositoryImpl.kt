package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.RewardDAO
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.RewardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepositoryImpl @Inject constructor(
    private val rewardDAO: RewardDAO
) : RewardRepository {

    override suspend fun addReward(reward: Reward) {
        rewardDAO.insert(reward)
    }

    override suspend fun updateReward(reward: Reward) {
        rewardDAO.update(reward)
    }

    override suspend fun deleteReward(reward: Reward) {
        rewardDAO.delete(reward)
    }

    override fun getRewardsForTask(task: Task): Flow<List<Reward>> =
        rewardDAO.getAll().map {
            it.filter { reward -> reward.taskId == task.uid }
        }

}
