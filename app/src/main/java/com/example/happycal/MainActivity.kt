package com.example.happycal

import android.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.example.happycal.GetStartedActivity.Companion.EXTRA_CURRENT_WEIGHT
import com.example.happycal.GetStartedActivity.Companion.EXTRA_GOALS_WEIGHT
import com.example.happycal.GetStartedActivity.Companion.EXTRA_NAME
import com.example.happycal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val type = arrayOf(
        "Breakfast",
        "Lunch",
        "Dinner",
        "Snack"
    )

    private var selectedWoTime: String = ""
    private var selectedEatTime: String = ""
    private var selectedType: String = ""


    companion object {
        const val EXTRA_TYPE_FOOD = "extra_type"
        const val EXTRA_EAT_TIME = "extra_eat_time"
        const val EXTRA_FOOD_CAL = "extra_food_cal"
        const val EXTRA_BURN_CAL = "extra_burn_cal"
        const val EXTRA_WO_TIME = "extra_wo_time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentWeight = intent.getStringExtra(EXTRA_CURRENT_WEIGHT)
        val goalsWeight = intent.getStringExtra(EXTRA_GOALS_WEIGHT)
        val name = intent.getStringExtra(EXTRA_NAME)



        with(binding) {
            val adapterType = ArrayAdapter(this@MainActivity, R.layout.simple_spinner_item, type)
            adapterType.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )

            spinnerTypeFood.adapter = adapterType
            spinnerTypeFood.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectedType = type[position] // Set selectedType
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

            val timePicker1 = binding.timePickerWo
            timePicker1.setOnTimeChangedListener { _, hourOfDay, minute ->
                selectedWoTime = String.format("%02d:%02d", hourOfDay, minute)
                binding.editTxtWoTime.setText(selectedWoTime)
            }

            val timePicker2 = binding.timePickerEat
            timePicker2.setOnTimeChangedListener { _, hourOfDay, minute ->
                selectedEatTime = String.format("%02d:%02d", hourOfDay, minute)
                binding.editTxtEatTime.setText(selectedEatTime)
            }


            btnSave.setOnClickListener {
                btnSave.setOnClickListener {
                    val intentToHomePageActivity =
                        Intent(this@MainActivity, HomePageActivity::class.java)

                    val burnCal = editTxtCalBurn.text.toString()
                    val foodCal = editTxtCalFood.text.toString()


                    intentToHomePageActivity.putExtra(EXTRA_FOOD_CAL, foodCal)
                    intentToHomePageActivity.putExtra(EXTRA_BURN_CAL, burnCal)
                    intentToHomePageActivity.putExtra(EXTRA_NAME, name)
                    intentToHomePageActivity.putExtra(EXTRA_CURRENT_WEIGHT, currentWeight)
                    intentToHomePageActivity.putExtra(EXTRA_GOALS_WEIGHT, goalsWeight)
                    intentToHomePageActivity.putExtra(EXTRA_TYPE_FOOD, selectedType)
                    intentToHomePageActivity.putExtra(EXTRA_EAT_TIME, selectedEatTime)
                    intentToHomePageActivity.putExtra(EXTRA_WO_TIME, selectedWoTime)



                    startActivity(intentToHomePageActivity)
                }
            }
        }
    }
}