package com.example.factorymobilplan

abstract class Plan {
    abstract val baseMonthPrice:Double
    abstract val dataPrice:Double
    abstract val messagePrice: Double
    abstract val callPrice:Double
    
    abstract fun getBasePrice(): Double
    abstract fun getDataPrice(amountData: Int):Double
    abstract fun getMessagePrice(amountMessage: Int):Double
    abstract fun getCallPrice(amountCall: Int):Double
    
}
    