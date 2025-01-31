package gov.nasa.gsfc.icesat2.icesat_2

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


private const val TAG = "ListRecyclerViewAdapter"

//ViewHolder - storing references to the views involved to make this more efficient
class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textViewDateTime: TextView = view.findViewById(R.id.textViewDateTime)
    var textViewLatLng: TextView = view.findViewById(R.id.textViewLatLng)
    var imageView: ImageView = view.findViewById(R.id.imageView)
    var locationListLinearLayout: LinearLayout = view.findViewById(R.id.locationListLinearLayout)
}

class ListRecyclerViewAdapter(val context: Context, private val allPoints: ArrayList<Point>) : RecyclerView.Adapter<ListViewHolder>(){
    //the indices that the headers start at
    private var headerLocations = ArrayList<Int>()
    //variables for setting the padding programmatically for the headers
    private val headerPadding = 30
    private val scale: Float = context.resources.displayMetrics.density
    private val dpAsPixels = (headerPadding * scale + 0.5f).toInt()
    private var listWithHeaders: ArrayList<Point?>
    private lateinit var listener: ILaunchSingleMarkerMap
    private var dimenHeader = R.dimen.header_font_large
    private var dimenListItem = R.dimen.list_item_font_medium

    init {
        headerLocations = calculateHeaderLocations(allPoints)
        Log.d(TAG, "headerLocations $headerLocations")
        listWithHeaders = ArrayList(allPoints)
        for (i in 0 until headerLocations.size) {
            listWithHeaders.add(headerLocations[i], null)
        }
        Log.d(TAG, "list with headers is $listWithHeaders")

        val displayMetrics = context.resources.displayMetrics
        val density = displayMetrics.density
        val dpWidth = displayMetrics.widthPixels / density

        Log.d(TAG, "dp with is $dpWidth")
        if (dpWidth > 375) {
            dimenListItem = R.dimen.list_item_font_large
        }
    }

    /**
     * find the indices where the headers should be inserted (ie: the indices at the begging of all
     * of the new dates)
     */
    private fun calculateHeaderLocations(chain: ArrayList<Point>) : ArrayList<Int> {
        Log.d(TAG, "calculateHeaderLocations - ListRecyclerViewAdapter")
        val arr = ArrayList<Int>()
        arr.add(0)
        for (i in 1 until allPoints.size) {
            if (!onSameChain(chain[i - 1], chain[i])) {
                arr.add(i + arr.size)
            }
        }
        return arr
    }

    private fun onSameChain(p1: Point, p2: Point) : Boolean {
        Log.d(TAG, "onSameChain - ListRecyclerViewAdapter")
        val timingThreshold = 60
        return p1.dateObject.time + timingThreshold * 1000 > p2.dateObject.time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d(TAG, "onCreateViewHolder - ListRecyclerViewAdapter")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_list_ticket, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder - ListRecyclerViewAdapter")
        val item = listWithHeaders[position]
        if (item == null) {
            val nextItem = listWithHeaders[position + 1]
            holder.textViewDateTime.text = context.getString(R.string.dateYearString, nextItem!!.date, nextItem.year)
            holder.textViewDateTime.typeface = Typeface.DEFAULT_BOLD
            holder.textViewDateTime.setPadding(dpAsPixels, dpAsPixels / 2, dpAsPixels, dpAsPixels / 10)
            holder.textViewDateTime.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(dimenHeader))
            holder.textViewLatLng.visibility = View.GONE
            holder.imageView.visibility = View.GONE
        } else {
            holder.textViewDateTime.text = item.dateString
            holder.textViewDateTime.typeface = Typeface.DEFAULT
            holder.textViewDateTime.setPadding(0, 0, 0, 0)
            holder.textViewDateTime.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(dimenListItem))
            holder.textViewLatLng.visibility = View.VISIBLE
            holder.imageView.visibility = View.VISIBLE
            holder.textViewLatLng.text = context.getString(R.string.latLngDisplayString, item.latitude.toString(), 0x00B0.toChar(), item.longitude.toString(), 0x00B0.toChar())

            holder.locationListLinearLayout.setOnClickListener {
                Log.d(TAG, "list clicked at position $position")
                listener.navigateToSingleMarkerMap(item.latitude, item.longitude, item.dateString, item.dateObject.time)
            }
        }

    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount - ListRecyclerViewAdapter")
        return allPoints.size + headerLocations.size
    }

    fun setUpListener(listener: ILaunchSingleMarkerMap) {
        Log.d(TAG, "setUpListener - ListRecyclerViewAdapter")
        this.listener = listener
    }


}