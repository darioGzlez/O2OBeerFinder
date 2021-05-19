package com.dariogonmar.o2obeerfinder2.activities.main

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dariogonmar.o2obeerfinder2.R
import com.dariogonmar.o2obeerfinder2.activities.detail.DetailActivity
import com.dariogonmar.o2obeerfinder2.filter.BeerFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.DescripcionFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.NameFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.TagFilter
import com.dariogonmar.o2obeerfinder2.models.Beer
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.squareup.picasso.Picasso

class BeersRecyclerAdapter(val beers: List<Beer>, private val activity: Activity) : RecyclerView.Adapter<BeersRecyclerAdapter.BeerHolder>(), Filterable  {

    private var beerFilter = BeerFilter(listOf(
        NameFilter(),
        DescripcionFilter(),
        TagFilter()
    ))
    private var filteredBeers: List<Beer>

    init {
        filteredBeers = beers
    }

    class BeerHolder(private val activity: Activity, inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_mainbeer_row, parent, false)) {

        fun bind(beer: Beer) {
            val beerName = itemView.findViewById<TextView>(R.id.beer_roow_info_name)
            val beerDescription = itemView.findViewById<TextView>(R.id.beer_roow_info_description)
            val firstBrewed = itemView.findViewById<TextView>(R.id.beer_roow_info_first_brewed)
            val tagsGroup = itemView.findViewById<ChipGroup>(R.id.beer_roow_info_tags_group)
            val imageView = itemView.findViewById<ImageView>(R.id.beer_roow_info_image_view)
            val cardView = itemView.findViewById<CardView>(R.id.beer_roow_info_card)

            beerName.text = beer.name
            beerDescription.text = beer.description
            firstBrewed.text = beer.firstBrewed

            Picasso.get().load(beer.imageURL).into(imageView);

            tagsGroup.removeAllViews()
            beer.tagline.split(",").forEach { tag ->
                val chip = Chip(itemView.context)
                chip.text = tag
                tagsGroup.addView(chip)
            }

            itemView.setOnClickListener {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("id", beer.id)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    activity,
                    cardView,
                    "shared_element_container"
                )
                activity.startActivity(intent, options.toBundle())
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BeerHolder(activity, inflater, parent)
    }

    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        val item = filteredBeers.get(position)
        holder.bind(item)
    }

    override fun getItemCount() = filteredBeers.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().uppercase()

                val results = FilterResults()

                if (query.isEmpty()) {
                    results.values = beers
                } else {
                    results.values = beerFilter.applyFilter(query, beers)
                }

                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredBeers = results?.values as List<Beer>
                notifyDataSetChanged()
            }
        }
    }

}