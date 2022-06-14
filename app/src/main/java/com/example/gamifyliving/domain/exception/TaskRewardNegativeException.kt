package com.example.gamifyliving.domain.exception

class TaskRewardNegativeException(taskReward: Int) :
    Exception("task reward $taskReward can not be negative")