package com.example.gamifyliving.domain.exception

import java.time.LocalTime

class StartTimeGreaterThanEndTimeException(startTime: LocalTime, endTime: LocalTime) :
    Exception("start time $startTime can't be greater than end time $endTime for a time-span")