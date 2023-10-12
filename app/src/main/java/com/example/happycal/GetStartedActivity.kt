package com.example.happycal

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.happycal.databinding.ActivityGetStartedBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding

    private val satuan1 = arrayOf(
        "kg",
        "lbs")

    private val satuan2 = arrayOf(
        "kg",
        "lbs")

    private val goals = arrayOf(
        "Cutting",
        "Bulking",
        "Maintaning")

    private var selectedDate:String=""
    private var selectedSatuan1: String = ""
    private var selectedSatuan2: String = ""
    private var selectedGoal: String = ""

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CURRENT_WEIGHT = "extra_current_weight"
        const val EXTRA_GOALS_WEIGHT = "extra_goals_weight"
        const val EXTRA_GOALS = "extra_goals"
        const val EXTRA_DATE_TARGET = "extra_date_target"
        const val EXTRA_MAX_CAL = "extra_max_cal"
        const val EXTRA_SATUAN1 = "extra_satuan1"
        const val EXTRA_SATUAN2 = "extra_satuan2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val adapterGoals = ArrayAdapter(this@GetStartedActivity, R.layout.simple_spinner_item, goals)
            adapterGoals.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerGoals.adapter= adapterGoals
            spinnerGoals.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedGoal = goals[position]
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

            val datePicker = binding.datePickerTarget

            datePicker.init(
                datePicker.year, datePicker.month, datePicker.dayOfMonth,
                DatePicker.OnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, monthOfYear, dayOfMonth)
                    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                    selectedDate = dateFormat.format(calendar.time)
                    binding.txtTitleTargetGoal.setText(selectedDate)
                }
            )

            val adapterSatuan1 = ArrayAdapter(this@GetStartedActivity, R.layout.simple_spinner_item, satuan1)
            adapterSatuan1.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerSatuan1.adapter= adapterSatuan1

            spinnerSatuan1.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedSatuan1 = satuan1[position]
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }


            val adapterSatuan2 = ArrayAdapter(this@GetStartedActivity, R.layout.simple_spinner_item, satuan2)
            adapterSatuan2.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)

            spinnerSatuan2.adapter= adapterSatuan2

            spinnerSatuan2.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        val selectedSatuan2 = satuan2[position]
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }


            btnNext.setOnClickListener {
                val intentToHomePageActivity =
                    Intent(this@GetStartedActivity, HomePageActivity::class.java)

                val name = editTxtName.text.toString()
                val current_weight = editTxtCurentWeight.text.toString()
                val goals_weight = editTxtGoalWeight.text.toString()
                val max_cal = editTxtMaxCal.text.toString()
                val satuan1 = selectedSatuan1
                val satuan2 = selectedSatuan2


                intentToHomePageActivity.putExtra(EXTRA_NAME, name)
                intentToHomePageActivity.putExtra(EXTRA_CURRENT_WEIGHT, current_weight)
                intentToHomePageActivity.putExtra(EXTRA_GOALS_WEIGHT, goals_weight)
                intentToHomePageActivity.putExtra(EXTRA_MAX_CAL, max_cal)
                intentToHomePageActivity.putExtra(EXTRA_DATE_TARGET, selectedDate)
                intentToHomePageActivity.putExtra(EXTRA_GOALS, selectedGoal) // Ini adalah contoh untuk spinnerGoals
                intentToHomePageActivity.putExtra(EXTRA_SATUAN1, satuan1)
                intentToHomePageActivity.putExtra(EXTRA_SATUAN2, satuan2)

                startActivity(intentToHomePageActivity)
            }
        }
    }
}