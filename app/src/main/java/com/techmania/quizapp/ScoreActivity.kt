package com.techmania.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.techmania.quizapp.databinding.ActivityQuizBinding
import com.techmania.quizapp.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.score.setText("Congrats !!! Your Score is ${intent.getIntExtra("SCORE",0)}")
        binding.playAgain.setOnClickListener {
           val  intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}