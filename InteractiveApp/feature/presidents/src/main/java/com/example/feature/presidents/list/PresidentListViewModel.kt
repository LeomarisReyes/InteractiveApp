package com.example.feature.presidents.list

import androidx.lifecycle.ViewModel
import com.example.core.models.presidents.ColombiaPresident
import com.example.data.remote.mappers.toColombiaPresident
import com.example.data.remote.repository.ColombiaPresidentRepository
import com.example.data.remote.utils.NetworkResult
import com.example.feature.presidents.details.PresidentDetailsViewModel.ViewEvent
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
            ViewEvent.OnGoBack -> {
                _viewStateFlow.update { it.copy(navigateEffect = ViewEffect.GoBack) }
            }
            is ViewEvent.OnItemSelected -> {
                _viewStateFlow.update { it.copy(navigateEffect = ViewEffect.Navigate("PresidentDetailsScreen/${viewEvent.itemId}")) }
            }

            is ViewEvent.ConsumeEffect -> {
                _viewStateFlow.update { it.copy(navigateEffect = ViewEffect.Navigate("")) }
            }
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
        val loading : Boolean = true,
        val navigateEffect: ViewEffect = ViewEffect.Navigate("")
    )

    sealed interface ViewEvent {
        data object OnPresident : ViewEvent
        data object OnGoBack : ViewEvent
        data class OnItemSelected(val itemId: Int) : ViewEvent
        data class OnSearchPresident(val searchDescription: String) : ViewEvent
        data object ConsumeEffect : ViewEvent
    }

    sealed interface ViewEffect {
        data class Navigate(val route: String) : ViewEffect
        data object GoBack : ViewEffect
    }

}