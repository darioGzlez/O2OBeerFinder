package com.dariogonmar.o2obeerfinder2.activities.detail

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dariogonmar.o2obeerfinder2.databinding.ActivityDetailBinding
import com.dariogonmar.o2obeerfinder2.models.Unit
import com.google.android.material.chip.Chip
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var assistedFactory: DetailViewModelAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        findViewById<View>(android.R.id.content).transitionName = "shared_element_container"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
        }

        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getLongExtra("id", -1)
        val viewModel: DetailViewModel by viewModels {
            Factory(assistedFactory, id)
        }

        viewModel.beer.observe(this) {
            supportActionBar?.title = it.name

            binding.activityDetailDescription.text = it.description
            binding.activityDetailTips.text = it.brewersTips

            val ingredients = mutableListOf<Triple<String, Unit, Double>>()
            ingredients.addAll(it.ingredients.hops.map { hop -> Triple(hop.name, hop.amount.unit, hop.amount.value) })
            ingredients.addAll(it.ingredients.malt.map { malt -> Triple(malt.name, malt.amount.unit, malt.amount.value)})

            binding.activityDetailRecyclerView.isNestedScrollingEnabled = false
            binding.activityDetailRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.activityDetailRecyclerView.adapter = IngredientsRecyclerAdapter(ingredients)

            it.tagline.split(",").forEach { tag ->
                val chip = Chip(this)
                chip.text = tag
                binding.activityDetailRecyclerTags.addView(chip)
            }

            Picasso.get().load(it.imageURL).into(binding.activityDetailImageView)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}