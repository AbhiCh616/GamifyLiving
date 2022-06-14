package com.example.gamifyliving.domain.exception

class RewardPointsZeroOrNegativeException(rewardPoints: Int) :
    Exception("reward points $rewardPoints can not be less than one")