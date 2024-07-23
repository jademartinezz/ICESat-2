package gov.nasa.gsfc.icesat2.icesat_2.ui.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeStandalonePlayer
import gov.nasa.gsfc.icesat2.icesat_2.R

// import kotlinx.android.synthetic.main.fragment_info.* // DEPRECATED LANGUAGE

class InfoFragment : Fragment() {
    private lateinit var textViewDateRange: TextView
    private lateinit var textViewWatchVideo: TextView
    private lateinit var textViewInfo1: TextView
    private lateinit var textViewInfo2: TextView
    private lateinit var textViewInfo3: TextView
    private lateinit var textViewInfo4: TextView
    companion object {
        private const val TAG = "InfoFragment"
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView - InfoFragment")
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated - InfoFragment")
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated - InfoFragment")

        textViewDateRange = view.findViewById(R.id.textViewDateRange)
        textViewWatchVideo = view.findViewById(R.id.textViewWatchVideo)
        textViewInfo1 = view.findViewById(R.id.textViewInfo1)
        textViewInfo2 = view.findViewById(R.id.textViewInfo2)
        textViewInfo3 = view.findViewById(R.id.textViewInfo3)
        textViewInfo4 = view.findViewById(R.id.textViewInfo4)

        textViewDateRange.text = getString(R.string.currentData, "Date 1, 2020 - Date 2, 2020")

        textViewWatchVideo.setOnClickListener {
            val intent = YouTubeStandalonePlayer.createVideoIntent(
                requireActivity(),
                getString(R.string.google_maps_key),
                "VTVXrnuvGzU",
                0,
                true,
                false
            )
            startActivity(intent)
        }

        // Clicking on links takes you to the appropriate webpage
        textViewInfo1.movementMethod = LinkMovementMethod.getInstance()
        textViewInfo2.movementMethod = LinkMovementMethod.getInstance()
        textViewInfo3.movementMethod = LinkMovementMethod.getInstance()
        textViewInfo4.movementMethod = LinkMovementMethod.getInstance()
    }
}