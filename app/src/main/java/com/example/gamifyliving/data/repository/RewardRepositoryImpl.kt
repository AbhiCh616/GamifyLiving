package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.RewardDao
import com.example.gamifyliving.data.data_source.local.mapper.toDataModel
import com.example.gamifyliving.data.data_source.local.mapper.toDomainModel
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.application.repository.RewardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepositoryImpl @Inject constructor(
    private val rewardDao: RewardDao
) : RewardRepository {

    override suspend fun addRewards(rewards: List<Reward>) {
        rewardDao.insert(rewards.toDataModel())
    }

    override suspend fun deleteRewardsForTask(taskId: Int) {
        rewardDao.deleteRewardsForTask(taskId)
    }

    override fun getRewardsForTask(taskId: Int): Flow<List<Reward>> =
        rewardDao.getAll().map {
            it.filter { reward -> reward.taskId == taskId }.toDomainModel()
        }

}
