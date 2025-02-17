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
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var submitButton: Button
    private lateinit var scoreText: TextView

    private var score: Int = 0
    private var currentPokemonIndex: Int = 0
    private var isAnswerSubmitted = false


    private val pokemonNames = arrayOf("Farigiraf", "Snom", "Lopunny", "Krabby")
    private val pokemonSilhouettes = arrayOf(
        R.drawable.farigiraf_shadow,
        R.drawable.snom_shadow,
        R.drawable.lopunny_shadow,
        R.drawable.krabby_shadow
    )
    private val pokemonRevealed = arrayOf(
        R.drawable.farigiraf_revealed,
        R.drawable.snom_revealed,
        R.drawable.lopunny_revealed,
        R.drawable.krabby_revealed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        pokemonImage = findViewById(R.id.pokemon_image)
        radioGroup = findViewById(R.id.radio_group)
        option1 = findViewById(R.id.radioButton)
        option2 = findViewById(R.id.radioButton2)
        option3 = findViewById(R.id.radioButton3)
        option4 = findViewById(R.id.radioButton4)
        submitButton = findViewById(R.id.submit_button)
        scoreText = findViewById(R.id.score_text)

        loadNewPokemon()

        submitButton.setOnClickListener {
            if (!isAnswerSubmitted) {
                checkAnswer()
            } else {
                loadNewPokemon()
            }
        }
    }

    private fun loadNewPokemon() {
        isAnswerSubmitted = false
        submitButton.text = "Submit"
        radioGroup.clearCheck()


        val random = Random()
        currentPokemonIndex = random.nextInt(pokemonNames.size)
        pokemonImage.setImageResource(pokemonSilhouettes[currentPokemonIndex])


        val shuffledOptions = pokemonNames.toList().shuffled()
        option1.text = shuffledOptions[0]
        option2.text = shuffledOptions[1]
        option3.text = shuffledOptions[2]
        option4.text = shuffledOptions[3]
    }

    private fun checkAnswer() {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        val selectedOption = findViewById<RadioButton>(selectedRadioButtonId).text.toString()
        val correctAnswer = pokemonNames[currentPokemonIndex]


        if (selectedOption == correctAnswer) {
            score++
        } else {
            score--
        }


        scoreText.text = "Score: $score"
        pokemonImage.setImageResource(pokemonRevealed[currentPokemonIndex])
        submitButton.text = "Next"
        isAnswerSubmitted = true
    }
}