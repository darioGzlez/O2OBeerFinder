package com.dariogonmar.o2obeerfinder2.activities.detail

import androidx.lifecycle.*
import com.dariogonmar.o2obeerfinder2.models.Beer
import com.dariogonmar.o2obeerfinder2.repositories.BeerRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    beerRepository: BeerRepository,
    @Assisted private val id: Long
) : ViewModel() {

    private val _beer = MutableLiveData<Beer>()
    val beer : LiveData<Beer>
        get() = _beer

    init {
        viewModelScope.launch {
            _beer.value = beerRepository.getBeer(id)
        }
    }

}

class Factory(
    private val assistedFactory: DetailViewModelAssistedFactory,
    private val id: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return assistedFactory.buildViewModel(id) as T
    }
}

@AssistedFactory
interface DetailViewModelAssistedFactory {
    fun buildViewModel(id: Long): DetailViewModel
}