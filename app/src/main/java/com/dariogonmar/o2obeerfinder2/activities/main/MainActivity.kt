package com.dariogonmar.o2obeerfinder2.activities.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dariogonmar.o2obeerfinder2.R
import com.dariogonmar.o2obeerfinder2.databinding.ActivityMainBinding
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.ferfalk.simplesearchview.SimpleSearchView
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: BeersRecyclerAdapter
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.activityMainRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.beers.observe(this) {
            adapter = BeersRecyclerAdapter(it, this)
            binding.activityMainRecyclerView.adapter = adapter
        }

        skeleton = binding.activityMainRecyclerView.applySkeleton(R.layout.activity_mainbeer_row)
        skeleton.showSkeleton()

        binding.searchView.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                adapter.filter.filter("")
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val item: MenuItem = menu.findItem(R.id.action_search)
        binding.searchView.setMenuItem(item)

        return true
    }
}