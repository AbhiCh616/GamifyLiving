package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.RewardDAO
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.RewardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepositoryImpl @Inject constructor(
    private val rewardDAO: RewardDAO
) : RewardRepository {

    override suspend fun addRewards(rewards: List<Reward>) {
        rewardDAO.insert(rewards)
    }

    override suspend fun deleteRewardsForTask(taskId: Int) {
        rewardDAO.deleteRewardsForTask(taskId)
    }

    override fun getRewardsForTask(task: Task): Flow<List<Reward>> =
        rewardDAO.getAll().map {
            it.filter { reward -> reward.taskId == task.uid }
        }

}
