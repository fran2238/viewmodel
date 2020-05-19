package com.example.factorymobilplan

class ExpensivePlan(amountData:Int,
                    amountMessages : Int,
                    amountCalls: Int):Plan() {
    override val baseMonthPrice: Double
        get() = 300.0
    override val dataPrice: Double
        get() = 0.0
    override val messagePrice: Double
        get() = 0.0
    override val callPrice: Double
        get() = 0.0
    
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