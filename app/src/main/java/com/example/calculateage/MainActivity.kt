package com.example.calculateage

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var tvSelectedDate:TextView? = null
    var tvSelectmin:TextView?=null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAge:Button=findViewById(R.id.btn_age)
        tvSelectedDate=findViewById(R.id.tv_selectedDate)
        tvSelectmin=findViewById(R.id.tv_min)
        btnAge.setOnClickListener {
            clickDatePicker()
            Toast.makeText(this,"btn pressed",Toast.LENGTH_SHORT).show()
        }
    }

     @RequiresApi(Build.VERSION_CODES.N)
     fun clickDatePicker() {

         val myCalendar=Calendar.getInstance()
         val year = myCalendar.get(Calendar.YEAR)
         val month = myCalendar.get(Calendar.MONTH)
         val day = myCalendar.get(Calendar.DAY_OF_MONTH)
         val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
             view,year,month,dayOfMonth->
             Toast.makeText(this,
                 "btn pressed $year this month${month+1} day of month $dayOfMonth",Toast.LENGTH_SHORT).show()

             val selectDate="$dayOfMonth/${month+1}/$year"
             tvSelectedDate?.text = selectDate
             val sdf=SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
             val theDate=sdf.parse(selectDate)
             //val selectDateofMin1=theDate.time/60000//minute
             val selectDateofMin=theDate.time/3600000//hours
             val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
             //val currentDateMin1=currentDate.time/600000//minute
             val currentDateMin=currentDate.time/3600000//hours
             val differencemin=currentDateMin-selectDateofMin
             tvSelectmin?.text= differencemin.toString() },year,month,day)

         dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
         dpd.show()



    }
}