package com.example.firstandroidproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(private val context: Context, private var fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.FruitViewHolder>(), Filterable {

    private var fruitListFiltered: List<Fruit> = fruitList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        return FruitViewHolder(view)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = fruitListFiltered[position]
        holder.nameTextView.text = fruit.name
        holder.imageView.setImageResource(fruit.imageResId)
    }

    override fun getItemCount(): Int = fruitListFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrEmpty()) {
                    fruitList
                } else {
                    fruitList.filter {
                        it.name.contains(constraint, ignoreCase = true)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                fruitListFiltered = results?.values as List<Fruit>
                notifyDataSetChanged()
            }
        }
    }

    class FruitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.fruitName)
        val imageView: ImageView = view.findViewById(R.id.fruitImage)
    }
}
