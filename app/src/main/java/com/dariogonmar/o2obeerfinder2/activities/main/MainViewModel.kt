package com.dariogonmar.o2obeerfinder2.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariogonmar.o2obeerfinder2.models.BeersResponse
import com.dariogonmar.o2obeerfinder2.repositories.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    beerRepository: BeerRepository
) : ViewModel() {

    private val _beers = MutableLiveData<BeersResponse>()
    val beers : LiveData<BeersResponse>
        get() = _beers

    init {
        viewModelScope.launch {
            _beers.value = beerRepository.getBeers()
        }
    }
}