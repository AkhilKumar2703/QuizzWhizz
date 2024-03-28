package com.techmania.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.techmania.quizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var list : ArrayList<QuestionModel>
    private var count : Int = 0
    private var score = 0
    private var userLife : Int = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList<QuestionModel>()
        Firebase.firestore.collection("quiz").get().addOnSuccessListener {
         doct->
            list.clear()
          for (i in doct.documents){

              var questionModel = i.toObject(QuestionModel::class.java)
              list.add(questionModel!!)

          }
            binding.question.setText(list.get(0).question)

            binding.option1.setText(list.get(0).option1)
            binding.option2.setText(list.get(0).option2)
            binding.option3.setText(list.get(0).option3)
            binding.option4.setText(list.get(0).option4)

        }
//        list.add(QuestionModel("Who is PM of India","Modi","Keju","Yogi","Akhil","Akhil"))
//        list.add(QuestionModel("Who is PM of India","rahul","Keju","Yogi","Akhil","Akhil"))
//        list.add(QuestionModel("Who is PM of India","sukhu","Keju","Yogi","Akhil","Akhil"))
//        list.add(QuestionModel("Who is PM of India","jairam","Keju","Yogi","Akhil","Akhil"))


        binding.option1.setOnClickListener {
            nextData(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener {
            nextData(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener {
            nextData(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener {
            nextData(binding.option4.text.toString())
        }

    }
    private  fun nextData(i : String) {
        if (count < list.size) {

            if (list.get(count).ans.equals(i)) { score++
            }else{
                userLife--
                binding.textViewLife.text = userLife.toString()
            }


        }

        count++
        if (count>=list.size )
        {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE",score)
           startActivity(intent)
            finish()
        }
        else {
            binding.question.setText(list.get(count).question)
            binding.option1.setText(list.get(count).option1)
            binding.option2.setText(list.get(count).option2)
            binding.option3.setText(list.get(count).option3)
            binding.option4.setText(list.get(count).option4)
        }
        if (userLife == 0) {
            Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
            finish()
        }else {
            binding.question.setText(list.get(count).question)
            binding.option1.setText(list.get(count).option1)
            binding.option2.setText(list.get(count).option2)
            binding.option3.setText(list.get(count).option3)
            binding.option4.setText(list.get(count).option4)
        }


    }

}