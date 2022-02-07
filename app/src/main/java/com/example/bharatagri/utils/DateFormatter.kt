package com.example.bharatagri.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {
   companion object {
      fun getDate(date: String?): String {

         //current date format
         val dateFormat = SimpleDateFormat("yyyy-MM-dd")

         val objDate: Date = dateFormat.parse(date)

         //Expected date format
         val dateFormat2 = SimpleDateFormat("dd MMM yyyy")

         return if (dateFormat2.format(objDate) != null && dateFormat2.format(objDate)
               .isNotEmpty()
         ) dateFormat2.format(objDate) else ""
      }
   }
}