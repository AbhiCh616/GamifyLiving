package com.example.gamifyliving.domain.exception

class DaysIntervalZeroOrNegativeException(daysInterval: Int) :
    Exception("interval of days $daysInterval should not be less than one")