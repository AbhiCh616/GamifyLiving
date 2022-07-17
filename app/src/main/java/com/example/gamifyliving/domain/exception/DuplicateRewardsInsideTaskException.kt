package com.example.gamifyliving.domain.exception

class DuplicateRewardsInsideTaskException :
    Exception("a task can not have more than one reward for same stat")