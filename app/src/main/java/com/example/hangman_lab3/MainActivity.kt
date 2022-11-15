package com.example.hangman_lab3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    val dictionary = listOf<String>("kind","love","sugar","barfi","honey","panda")

    var guess = ""
    var word = ""
    var guessesLeft = -1
    val indices = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        indices.clear()
        val guessLeftTextView = findViewById<TextView>(R.id.guessLeft)


            val random = Random()

        val randomNumber = random.nextInt(5)
        word = dictionary[randomNumber]
        guessesLeft = word.length
        guessLeftTextView.setText("Guesses Left: "+guessesLeft)
        val hint = word.map { ch->guess+="?"}

        val tvWord = findViewById<TextView>(R.id.tvWord)
        tvWord.text = guess

    }
    fun newGame(v:View){
        indices.clear()
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finishAffinity()
    }
    fun guess(v: View)
    {

        val etGuess = findViewById<EditText>(R.id.etGuess)
        val tvWord = findViewById<TextView>(R.id.tvWord)
        var tv_word = tvWord.text.toString()
        val text = etGuess.text.toString()
        val guessLeftTextView = findViewById<TextView>(R.id.guessLeft)
        val guessBtn = findViewById<Button>(R.id.button)

        if(text.length>1)
        {
            Toast.makeText(this,"Please enter a single character", Toast.LENGTH_SHORT).show()
        }
        else if(text.length==1)
        {
            val ch : Char = text[0]
            if(ch in word){
                val index= word.indexOf(ch)
                if(!(index in indices))
                {
                    indices.add(index)
                    tv_word = tv_word.replaceRange(index,index+1,ch+"")
                    tvWord.text = tv_word
                    if(!('?' in tv_word)){
                        Toast.makeText(this,"You Guessed correctly",Toast.LENGTH_LONG).show()
                        guessBtn.setEnabled(false)
                    }
//
                }
                else{
                    guessesLeft= guessesLeft-1
                    if(guessesLeft!=0){
                        guessLeftTextView.setText("Guesses Left: "+guessesLeft)
                    }
                    else{
                        Toast.makeText(this,"Please try again",Toast.LENGTH_SHORT).show()
                        guessBtn.setEnabled(false)
                        etGuess.setEnabled(false)
                    }
                }

            }
            else
            {
                guessesLeft= guessesLeft-1
                if(guessesLeft!=0){
                    guessLeftTextView.setText("Guesses Left: "+guessesLeft)
                }
                else{
                    Toast.makeText(this,"Please try again",Toast.LENGTH_SHORT).show()
                    guessBtn.setEnabled(false)
                    etGuess.setEnabled(false)

                }
            }
        }
    }
}

