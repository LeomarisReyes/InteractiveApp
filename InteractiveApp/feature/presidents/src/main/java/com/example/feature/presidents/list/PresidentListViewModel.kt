package com.example.feature.presidents.list

import androidx.lifecycle.ViewModel
import com.example.data.remote.mappers.toColombiaPresident
import com.example.data.remote.repository.ColombiaPresidentRepository
import com.example.data.remote.utils.NetworkResult
import com.example.domain.models.ColombiaPresident
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresidentListViewModel @Inject constructor(
    private val colombiaPresidentRepository : ColombiaPresidentRepository
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(ViewState())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun processEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            ViewEvent.OnPresident -> { coroutineScope.launch{
                getPresidents()
                _viewStateFlow.update { it.copy(loading = false) }
            } }
            is ViewEvent.OnSearchPresident -> TODO()
        }
    }

   private suspend fun getPresidents(){
        when(val response = colombiaPresidentRepository.getPresidents()){
            is NetworkResult.Success -> {
                _viewStateFlow.update { it.copy(presidents = response.data.map { presidentList ->
                    presidentList.toColombiaPresident() }
                )
                }
            }
            is NetworkResult.ApiError -> TODO()
            is NetworkResult.ApiException -> TODO()
        }
    }

    data class ViewState(
        val presidents : List<ColombiaPresident> = emptyList(),
        val searchDescription : String = "",
        val loading : Boolean = true
    )

    sealed interface ViewEvent{
        data object OnPresident : ViewEvent
        data class OnSearchPresident(val searchDescription: String) : ViewEvent
    }

    sealed interface ViewEffect {
        data class Navigate(val route: String) : ViewEffect
    }

}