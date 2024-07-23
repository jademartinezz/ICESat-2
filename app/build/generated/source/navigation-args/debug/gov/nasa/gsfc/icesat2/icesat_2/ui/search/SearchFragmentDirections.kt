package gov.nasa.gsfc.icesat2.icesat_2.ui.search

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import gov.nasa.gsfc.icesat2.icesat_2.R
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String

public class SearchFragmentDirections private constructor() {
  private data class ActionNavigationSearchToResultsHolderFragment(
    public val dateObjectTime: Long,
    public val lat: Float = 0.0F,
    public val long: Float = 0.0F,
    public val title: String = "\"\""
  ) : NavDirections {
    public override val actionId: Int = R.id.action_navigation_search_to_resultsHolderFragment

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

  private data class ActionNavigationSearchToSelectOnMapFragment(
    public val dateObjectTime: Long,
    public val lat: Float = 0.0F,
    public val long: Float = 0.0F,
    public val title: String = "\"\""
  ) : NavDirections {
    public override val actionId: Int = R.id.action_navigation_search_to_selectOnMapFragment

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
    public fun actionNavigationHomeToMapFragment2(): NavDirections =
        ActionOnlyNavDirections(R.id.action_navigation_home_to_mapFragment2)

    public fun actionNavigationSearchToResultsHolderFragment(
      dateObjectTime: Long,
      lat: Float = 0.0F,
      long: Float = 0.0F,
      title: String = "\"\""
    ): NavDirections = ActionNavigationSearchToResultsHolderFragment(dateObjectTime, lat, long,
        title)

    public fun actionNavigationSearchToSelectOnMapFragment(
      dateObjectTime: Long,
      lat: Float = 0.0F,
      long: Float = 0.0F,
      title: String = "\"\""
    ): NavDirections = ActionNavigationSearchToSelectOnMapFragment(dateObjectTime, lat, long, title)

    public fun actionNavigationSearchToSatelliteTrackingFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_navigation_search_to_satelliteTrackingFragment)
  }
}
