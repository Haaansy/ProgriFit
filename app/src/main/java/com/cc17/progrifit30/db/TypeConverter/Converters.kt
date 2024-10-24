package com.cc17.progrifit30.db.TypeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mapbox.geojson.Point


class Converters {
    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Point>::class.java).toList()
}