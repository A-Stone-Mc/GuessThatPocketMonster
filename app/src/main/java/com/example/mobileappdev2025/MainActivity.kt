package com.example.mobileappdev2025

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.RadioGroup
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonImage: ImageView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioGroup2: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var submitButton: Button
    private lateinit var scoreText: TextView
    private var score: Int = 0
    private var currentPokemonPlace: Int = 0
    private var isPressed = false

    private val pokemonNames = arrayOf("Farigiraf", "Snom", "Lopunny", "Krabby")
    private val pokemonShadows = arrayOf(R.drawable.farigiraf_shadow, R.drawable.snom_shadow, R.drawable.lopunny_shadow, R.drawable.krabby_shadow)
    private val pokemonRevealed = arrayOf(R.drawable.farigiraf_revealed, R.drawable.snom_revealed, R.drawable.lopunny_revealed, R.drawable.krabby_revealed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonImage = findViewById(R.id.pokemon_image)
        radioGroup = findViewById(R.id.radio_group)
        radioGroup2 = findViewById(R.id.radio_group2)
        option1 = findViewById(R.id.radioButton)
        option2 = findViewById(R.id.radioButton2)
        option3 = findViewById(R.id.radioButton3)
        option4 = findViewById(R.id.radioButton4)
        submitButton = findViewById(R.id.submit_button)
        scoreText = findViewById(R.id.score_text)

        shuffleAgain()

        //solution used from stackOverflow
        radioGroup.setOnCheckedChangeListener { _, _ ->
            if (radioGroup.checkedRadioButtonId != -1) {
                radioGroup2.clearCheck()
            }
        }

        radioGroup2.setOnCheckedChangeListener { _, _ ->
            if (radioGroup2.checkedRadioButtonId != -1) {
                radioGroup.clearCheck()
            }
        }

        submitButton.setOnClickListener {
            if (!isPressed) {
                checkAnswer()
            } else {
                shuffleAgain()
            }
        }
    }

    private fun shuffleAgain() {
        isPressed = false
        submitButton.text = "Submit"
        radioGroup.clearCheck()
        radioGroup2.clearCheck()

        val random = Random()
        currentPokemonPlace = random.nextInt(4)
        pokemonImage.setImageResource(pokemonShadows[currentPokemonPlace])


        val shuffledName = pokemonNames.toList().shuffled()
        option1.text = shuffledName[0]
        option2.text = shuffledName[1]
        option3.text = shuffledName[2]
        option4.text = shuffledName[3]
    }

    private fun checkAnswer() {

        val selectedOption = when {
            option1.isChecked -> option1.text.toString()
            option2.isChecked -> option2.text.toString()
            option3.isChecked -> option3.text.toString()
            option4.isChecked -> option4.text.toString()
            else -> return
        }

        val correctAnswer = pokemonNames[currentPokemonPlace]

        if (selectedOption == correctAnswer) {
            score++
        } else {
            score--
        }


        scoreText.text = "Score: $score"
        pokemonImage.setImageResource(pokemonRevealed[currentPokemonPlace])
        submitButton.text = "Next"
        isPressed = true
    }
}