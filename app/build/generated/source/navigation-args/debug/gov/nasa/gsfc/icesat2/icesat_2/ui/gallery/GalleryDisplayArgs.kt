package gov.nasa.gsfc.icesat2.icesat_2.ui.gallery

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class GalleryDisplayArgs(
  public val index: Int = 0
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("index", this.index)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("index", this.index)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): GalleryDisplayArgs {
      bundle.setClassLoader(GalleryDisplayArgs::class.java.classLoader)
      val __index : Int
      if (bundle.containsKey("index")) {
        __index = bundle.getInt("index")
      } else {
        __index = 0
      }
      return GalleryDisplayArgs(__index)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): GalleryDisplayArgs {
      val __index : Int?
      if (savedStateHandle.contains("index")) {
        __index = savedStateHandle["index"]
        if (__index == null) {
          throw IllegalArgumentException("Argument \"index\" of type integer does not support null values")
        }
      } else {
        __index = 0
      }
      return GalleryDisplayArgs(__index)
    }
  }
}
