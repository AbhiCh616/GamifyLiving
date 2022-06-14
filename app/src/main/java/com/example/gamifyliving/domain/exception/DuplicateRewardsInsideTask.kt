package com.example.gamifyliving.domain.exception

class DuplicateRewardsInsideTask :
    Exception("a task can not have more than one reward for same stat")