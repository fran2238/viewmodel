package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)



class GameViewModel : ViewModel(){

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L

        const val COUNTDOWN_PANIC_SECONDS = 10L
    }
    private val timer: CountDownTimer



    // current Time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
    get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)

    }

    // The current word
 private  val _word = MutableLiveData<String>()
    val word: LiveData<String>
    get() = _word

    // The current score
  private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    // livedata for buzz
    private val _buzzEvent = MutableLiveData<BuzzType>()
    val buzzEvent:LiveData<BuzzType>
    get() = _buzzEvent

    private lateinit var wordList: MutableList<String>

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished : LiveData<Boolean>
    get() = _eventGameFinished

    init {
        Log.i("GameViewModel", "GameViewModel Was Created")
        resetList()
        nextWord()

        _currentTime.value = 0
        _score.value = 0
        _word.value = ""
      //  var newTIme = (_currentTime.value) ?: 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinished.value = true
            }

            override fun onTick(p0: Long) {
            _currentTime.value = (p0 / ONE_SECOND)
                if (p0 / ONE_SECOND <= COUNTDOWN_PANIC_SECONDS){
                    _buzzEvent.value = BuzzType.COUNTDOWN_PANIC
                }
            }

        }

        timer.start()

    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }
    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
         resetList()
        }
            _word.value = wordList.removeAt(0)


    }
    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect() {
         _buzzEvent.value = BuzzType.CORRECT
         _score.value = (score.value)?.plus(1)
        nextWord()
    }
    fun onGameFinishComplete(){
        _buzzEvent.value = BuzzType.GAME_OVER
        _eventGameFinished.value = false
    }
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel","GameViewModel Was Destroyed")
    }
    fun onBuzzComplete(){
        _buzzEvent.value = BuzzType.NO_BUZZ
    }
}