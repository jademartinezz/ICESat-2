package gov.nasa.gsfc.icesat2.icesat_2

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String

public class ResultsHolderFragmentDirections private constructor() {
  private data class ActionResultsHolderFragmentToSingleMarkerMap(
    public val dateObjectTime: Long,
    public val lat: Float = 0.0F,
    public val long: Float = 0.0F,
    public val title: String = "\"\""
  ) : NavDirections {
    public override val actionId: Int = R.id.action_resultsHolderFragment_to_singleMarkerMap

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putFloat("lat", this.lat)
        result.putFloat("long", this.long)
        result.putString("title", this.title)
        result.putLong("dateObjectTime", this.dateObjectTime)
        return result
      }
  }

  public companion object {
    public fun actionResultsHolderFragmentToSingleMarkerMap(
      dateObjectTime: Long,
      lat: Float = 0.0F,
      long: Float = 0.0F,
      title: String = "\"\""
    ): NavDirections = ActionResultsHolderFragmentToSingleMarkerMap(dateObjectTime, lat, long,
        title)
  }
}
