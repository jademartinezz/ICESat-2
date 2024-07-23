package gov.nasa.gsfc.icesat2.icesat_2

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Float
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmStatic

public data class SingleMarkerMapArgs(
  public val dateObjectTime: Long,
  public val lat: Float = 0.0F,
  public val long: Float = 0.0F,
  public val title: String = "\"\""
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putFloat("lat", this.lat)
    result.putFloat("long", this.long)
    result.putString("title", this.title)
    result.putLong("dateObjectTime", this.dateObjectTime)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("lat", this.lat)
    result.set("long", this.long)
    result.set("title", this.title)
    result.set("dateObjectTime", this.dateObjectTime)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): SingleMarkerMapArgs {
      bundle.setClassLoader(SingleMarkerMapArgs::class.java.classLoader)
      val __lat : Float
      if (bundle.containsKey("lat")) {
        __lat = bundle.getFloat("lat")
      } else {
        __lat = 0.0F
      }
      val __long : Float
      if (bundle.containsKey("long")) {
        __long = bundle.getFloat("long")
      } else {
        __long = 0.0F
      }
      val __title : String?
      if (bundle.containsKey("title")) {
        __title = bundle.getString("title")
        if (__title == null) {
          throw IllegalArgumentException("Argument \"title\" is marked as non-null but was passed a null value.")
        }
      } else {
        __title = "\"\""
      }
      val __dateObjectTime : Long
      if (bundle.containsKey("dateObjectTime")) {
        __dateObjectTime = bundle.getLong("dateObjectTime")
      } else {
        throw IllegalArgumentException("Required argument \"dateObjectTime\" is missing and does not have an android:defaultValue")
      }
      return SingleMarkerMapArgs(__dateObjectTime, __lat, __long, __title)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): SingleMarkerMapArgs {
      val __lat : Float?
      if (savedStateHandle.contains("lat")) {
        __lat = savedStateHandle["lat"]
        if (__lat == null) {
          throw IllegalArgumentException("Argument \"lat\" of type float does not support null values")
        }
      } else {
        __lat = 0.0F
      }
      val __long : Float?
      if (savedStateHandle.contains("long")) {
        __long = savedStateHandle["long"]
        if (__long == null) {
          throw IllegalArgumentException("Argument \"long\" of type float does not support null values")
        }
      } else {
        __long = 0.0F
      }
      val __title : String?
      if (savedStateHandle.contains("title")) {
        __title = savedStateHandle["title"]
        if (__title == null) {
          throw IllegalArgumentException("Argument \"title\" is marked as non-null but was passed a null value")
        }
      } else {
        __title = "\"\""
      }
      val __dateObjectTime : Long?
      if (savedStateHandle.contains("dateObjectTime")) {
        __dateObjectTime = savedStateHandle["dateObjectTime"]
        if (__dateObjectTime == null) {
          throw IllegalArgumentException("Argument \"dateObjectTime\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"dateObjectTime\" is missing and does not have an android:defaultValue")
      }
      return SingleMarkerMapArgs(__dateObjectTime, __lat, __long, __title)
    }
  }
}
