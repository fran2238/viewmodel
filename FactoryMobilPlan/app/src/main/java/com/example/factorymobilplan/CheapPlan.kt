package com.example.factorymobilplan

import java.lang.reflect.Array.set

class CheapPlan(amountData:Int,
                amountMessages : Int,
                amountCalls: Int): Plan() {
    override val baseMonthPrice: Double
        get() = 20.2
    override val dataPrice: Double
        get() = 8.0
    override val messagePrice: Double
        get() = 0.2
    override val callPrice: Double
        get() = 1.0

    override fun getBasePrice(): Double {
       return baseMonthPrice
    }

    override fun getDataPrice(amountData: Int): Double {
        return amountData * dataPrice
    }

    override fun getMessagePrice(amountMessage: Int): Double {
        return amountMessage * messagePrice
    }

    override fun getCallPrice(amountCall: Int): Double {
       return amountCall * callPrice
    }

    


}