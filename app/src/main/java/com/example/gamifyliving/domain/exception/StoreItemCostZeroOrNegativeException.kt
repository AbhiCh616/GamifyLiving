package com.example.gamifyliving.domain.exception

class StoreItemCostZeroOrNegativeException(storeItemCost: Int) :
    Exception("store item cost $storeItemCost can not be less than one")