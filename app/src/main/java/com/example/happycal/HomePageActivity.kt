package com.example.happycal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.happycal.GetStartedActivity.Companion.EXTRA_CURRENT_WEIGHT
import com.example.happycal.GetStartedActivity.Companion.EXTRA_DATE_TARGET
import com.example.happycal.GetStartedActivity.Companion.EXTRA_GOALS_WEIGHT
import com.example.happycal.GetStartedActivity.Companion.EXTRA_MAX_CAL
import com.example.happycal.GetStartedActivity.Companion.EXTRA_NAME
import com.example.happycal.GetStartedActivity.Companion.EXTRA_SATUAN1
import com.example.happycal.GetStartedActivity.Companion.EXTRA_SATUAN2
import com.example.happycal.MainActivity.Companion.EXTRA_BURN_CAL
import com.example.happycal.MainActivity.Companion.EXTRA_FOOD_CAL
import com.example.happycal.MainActivity.Companion.EXTRA_TYPE_FOOD
import com.example.happycal.databinding.ActivityHomePageBinding
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date


class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        val todayDate = SimpleDateFormat("EEEE, d MMMM yyyy", DateFormatSymbols()).format(Date())
        val maxDailyCal = intent.getStringExtra(EXTRA_MAX_CAL)
        val burnCal = intent.getStringExtra(EXTRA_BURN_CAL)
        val selectedType = intent.getStringExtra(EXTRA_TYPE_FOOD)
        val calories = intent.getStringExtra(EXTRA_FOOD_CAL)
        val currentWeight = intent.getStringExtra(EXTRA_CURRENT_WEIGHT)
        val goalsWeight = intent.getStringExtra(EXTRA_GOALS_WEIGHT)
        val satuan1 = intent.getStringExtra(EXTRA_SATUAN1)
        val satuan2 = intent.getStringExtra(EXTRA_SATUAN2)


        with(binding) {
            txtTodayDate.text = "$todayDate"
            txtName.text = "Hi, $name!"
            txtSisaCal.text = "$calories cal"
            txtCalIn.text = "$calories cal"
            txtCalOut.text = "$burnCal cal"
            txtCurrentWeight.text = "$currentWeight kg"
            txtGoalsWeight.text = "$goalsWeight kg"

            if (selectedType == "Breakfast") {
                txtCalBreakfast.text = "$calories cal"
            }
            if (selectedType == "Lunch") {
                txtCalLunch.text = "$calories cal"
            }
            if (selectedType == "Dinner") {
                txtCalDinner.text = "$calories cal"
            }
            if (selectedType == "Snack") {
                txtCalSnack.text = "$calories cal"
            }

            btnInput.setOnClickListener{
                val intentToMainActivity = Intent(this@HomePageActivity, MainActivity::class.java)
                startActivity(intentToMainActivity)

                val calOut = txtCalOut.text.toString()
                val sisaCal = txtSisaCal.text.toString()
                val current_weight = txtCurrentWeight.text.toString()
                val goalsWeight = txtGoalsWeight.text.toString()
                val calIn = txtCalIn.text.toString()


                intentToMainActivity.putExtra(EXTRA_BURN_CAL, calOut)
                intentToMainActivity.putExtra(EXTRA_FOOD_CAL, sisaCal)
                intentToMainActivity.putExtra(EXTRA_CURRENT_WEIGHT, current_weight)
                intentToMainActivity.putExtra(EXTRA_GOALS_WEIGHT, goalsWeight)
                intentToMainActivity.putExtra(EXTRA_FOOD_CAL, calIn)
                intentToMainActivity.putExtra(EXTRA_TYPE_FOOD, selectedType)
            }
        }
    }

}

