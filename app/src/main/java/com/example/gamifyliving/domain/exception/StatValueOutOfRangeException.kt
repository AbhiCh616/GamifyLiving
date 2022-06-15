package com.example.gamifyliving.domain.exception

class StatValueOutOfRangeException(statValue: Int) :
    Exception("stat value $statValue is out of range min 0 to max 500")