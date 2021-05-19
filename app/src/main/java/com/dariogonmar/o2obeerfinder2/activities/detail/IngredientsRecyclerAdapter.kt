package com.dariogonmar.o2obeerfinder2.activities.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dariogonmar.o2obeerfinder2.R
import com.dariogonmar.o2obeerfinder2.models.Unit

class IngredientsRecyclerAdapter(private val ingredients: List<Triple<String, Unit, Double>>): RecyclerView.Adapter<IngredientsRecyclerAdapter.IngredientHolder>() {

    class IngredientHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_detail_ingredient_row, parent, false)) {

            fun bind(data: Triple<String, Unit, Double>) {
                val name = itemView.findViewById<TextView>(R.id.activity_detail_row_name)
                val ammount = itemView.findViewById<TextView>(R.id.activity_detail_row_ammount)
                val unit = itemView.findViewById<TextView>(R.id.activity_detail_row_unit)

                name.text = data.first
                ammount.text = data.third.toString()
                unit.text = data.second.name
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val inflater = LayoutInflater.from(parent.context)
        return IngredientHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        val item = ingredients[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

}