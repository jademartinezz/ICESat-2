package gov.nasa.gsfc.icesat2.icesat_2.ui.gallery

import android.os.Bundle
import androidx.navigation.NavDirections
import gov.nasa.gsfc.icesat2.icesat_2.R
import kotlin.Int

public class GalleryFragmentDirections private constructor() {
  private data class ActionNavigationGalleryToGalleryDisplay(
    public val index: Int = 0
  ) : NavDirections {
    public override val actionId: Int = R.id.action_navigation_gallery_to_galleryDisplay

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("index", this.index)
        return result
      }
  }

  private data class ActionNavigationGalleryToGalleryContainerFragment2(
    public val index: Int = 0
  ) : NavDirections {
    public override val actionId: Int = R.id.action_navigation_gallery_to_galleryContainerFragment2

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("index", this.index)
        return result
      }
  }

  public companion object {
    public fun actionNavigationGalleryToGalleryDisplay(index: Int = 0): NavDirections =
        ActionNavigationGalleryToGalleryDisplay(index)

    public fun actionNavigationGalleryToGalleryContainerFragment2(index: Int = 0): NavDirections =
        ActionNavigationGalleryToGalleryContainerFragment2(index)
  }
}
