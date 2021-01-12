import com.example.grocerydelivery.FirstFragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerydelivery.FirstFragmentDirections
import com.example.grocerydelivery.R
import com.example.grocerydelivery.data.StoreItem

class StoresAdapter(private val storeItems: List<StoreItem>) :
    RecyclerView.Adapter<StoresAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.a_single_store_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = storeItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storeItem = storeItems[position]
        holder.bind(storeItem)

    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(storeItem: StoreItem) {
            itemView.findViewById<TextView>(R.id.storeItemTitle).text = storeItem.name
            itemView.findViewById<TextView>(R.id.itemPrice).text = "Price: $ ${storeItem.price}"

            if (storeItem.isHighlyRated) {
                itemView.findViewById<ImageView>(R.id.highlyRatedIcon).visibility = View.VISIBLE
            }

            view.findViewById<ImageView>(R.id.mainPhoto).setOnClickListener {
                val action = FirstFragmentDirections.actionFirstFragmentToProductInfo(storeItem.name)
                view.findNavController().navigate(action)
            }
        }
    }
}