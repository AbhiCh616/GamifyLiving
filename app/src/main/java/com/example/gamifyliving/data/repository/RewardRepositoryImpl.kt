package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.RewardDao
import com.example.gamifyliving.data.data_source.local.mapper.toDataModel
import com.example.gamifyliving.data.data_source.local.mapper.toDomainModel
import com.example.gamifyliving.domain.entity.Reward
import com.example.gamifyliving.application.repository.RewardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
