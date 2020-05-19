package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel (finalScore: Int):ViewModel(){

    // Score
    private var _score= MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    //event playagain
    private var _eventPlayAgain = MutableLiveData<Boolean>()

    val eventPlayAgain : LiveData<Boolean>
    get() = _eventPlayAgain

    init {
        Log.i("ScoreViewModel", "The Final IS $finalScore")
        _score.value = finalScore

    }
    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }
    fun onPlayAgianComplete(){
        _eventPlayAgain.value = false
    }
}