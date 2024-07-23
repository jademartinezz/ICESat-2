package gov.nasa.gsfc.icesat2.icesat_2.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import gov.nasa.gsfc.icesat2.icesat_2.FavoritesAdapter
import gov.nasa.gsfc.icesat2.icesat_2.ILaunchSingleMarkerMap
import gov.nasa.gsfc.icesat2.icesat_2.R
import gov.nasa.gsfc.icesat2.icesat_2.favoritesdb.FavoritesEntry
// import kotlinx.android.synthetic.main.fragment_favorite.*        // DEPRECATED LANGUAGE
private const val TAG = "FavoritesFragment"

class FavoritesFragment : Fragment(), ILaunchSingleMarkerMap {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var favoritesList: ArrayList<FavoritesEntry>
    private lateinit var favoriteRecyclerView: RecyclerView // Define RecyclerView variable
    private lateinit var snackbarCoordinator: View // Define snackbarCoordinator variable
    private lateinit var textViewNoFavorites: TextView // Define textViewNoFavorites variable

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView - FavoritesFragment")
        favoritesViewModel =
            ViewModelProvider(this)[FavoritesViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        // Initialize RecyclerView
        favoriteRecyclerView = root.findViewById(R.id.favoriteRecyclerView)
        // Initialize snackbarCoordinator
        snackbarCoordinator = root.findViewById(R.id.snackbarCoordinator)
        // Initialize textViewNoFavorites
        textViewNoFavorites = root.findViewById(R.id.textViewNoFavorites)
        favoritesViewModel.getAllFavorites().observe(viewLifecycleOwner) {
            Log.d(TAG, "OBSERVED. size is ${it.size}")
            favoritesList = it as ArrayList<FavoritesEntry>
            initRV()
            displayNoFavoritesTextIfNecessary()
        }

        // setHasOptionsMenu(true) // DEPRECATED LANGUAGE
        return root
    }

    private fun initRV() {
        Log.d(TAG, "initRV - FavoritesFragment")
        val adapter = FavoritesAdapter(requireContext(), favoritesList)
        adapter.setListener(this)
        favoriteRecyclerView.adapter = adapter
        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder): Boolean {
                Log.d(TAG, "onMove - FavoritesFragment")
                return false
            }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    Log.d(TAG, "onSwiped - FavoritesFragment")
                    val pos = viewHolder.bindingAdapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        val element = favoritesList[pos]
                        favoritesViewModel.delete(element.dateObjectTime)

                        Snackbar.make(
                            snackbarCoordinator,
                            R.string.itemDeleted,
                            Snackbar.LENGTH_LONG
                        ).setAction(R.string.undo) {
                            favoritesViewModel.insert(element)
                            displayNoFavoritesTextIfNecessary()
                        }.show()
                    }
                }

            }).attachToRecyclerView(favoriteRecyclerView)
    }

    private fun displayNoFavoritesTextIfNecessary() {
        Log.d(TAG, "displayNoFavoritesTextIfNecessary - FavoritesFragment")
        if (favoritesList.isEmpty()) {
            textViewNoFavorites.visibility = View.VISIBLE
        } else {
            textViewNoFavorites.visibility = View.INVISIBLE
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "onCreateOptionsMenu - FavoritesFragment")
        inflater.inflate(R.menu.delete_menu, menu)
        // Set click listener for each menu item
        menu.findItem(R.id.menuDelete)?.setOnMenuItemClickListener {
            showDialog(
                getString(R.string.deleteAllTitle),
                getString(R.string.deleteAllDescription),
                getString(R.string.yes),
                getString(R.string.cancel)
            )
            true // Indicate that the click was handled
        }
    }

    private fun showDialog(title: String, description: String, positive: String, negative: String) {
        Log.d(TAG, "showDialog - FavoritesFragment")
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setMessage(description)
            ?.setTitle(title)
            ?.setPositiveButton(positive) { _, _ ->
                favoritesViewModel.deleteAll()
            }
            ?.setNegativeButton(negative) { _, _ ->  }
        alertBuilder.show()
    }

    //navigates from favorites adapter to single marker map
    override fun navigateToSingleMarkerMap(
        lat: Double,
        long: Double,
        title: String,
        dateObjectTime: Long
    ) {
        Log.d(TAG, "navigateToSingleMarkerMap - FavoritesFragment")
        val params = FavoritesFragmentDirections.actionNavigationFavoritesToSingleMarkerMap(lat.toLong(), long.toFloat(), title.toFloat(),
            dateObjectTime.toString()
        )
        this.findNavController().navigate(params)
    }
}
