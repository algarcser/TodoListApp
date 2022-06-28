package clases_java.model_Databases

import androidx.room.TypeConverter
import java.sql.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromArrayStrings(value: List<String>?) : String?{
        var resultado: String = ""
        if(value == null){
            return null
        }else {
            for (string in value) {
                if (resultado != "") {
                    resultado = resultado + "%" + string
                } else {
                    resultado = string
                }
            }
            return resultado
        }
    }

    @TypeConverter
    fun stringToArrayStrings(string: String?) : List<String>?{
        if(string == null){
            return null
        }else{
            return string.split("%")
        }
    }


}