package com.example.feature.presidents.details

import androidx.lifecycle.ViewModel
import com.example.data.remote.mappers.toColombiaPresident
import com.example.data.remote.repository.ColombiaPresidentRepository
import com.example.data.remote.utils.NetworkResult
import com.example.domain.models.ColombiaPresident
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PresidentDetailsViewModel @Inject constructor(
    private val colombiaPresidentRepository: ColombiaPresidentRepository
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(ViewState())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    fun processEvent(viewEvent : ViewEvent) {
        when (viewEvent) {
            ViewEvent.OnBack -> TODO()
        }
    }

//    suspend fun getPresident(id: Int){
//        when (val response = colombiaPresidentRepository.getPresidents()){
//            is NetworkResult.Success -> {_viewStateFlow.update { it.copy(president = response) }}
//            is NetworkResult.ApiError -> TODO()
//            is NetworkResult.ApiException -> TODO()
//        }
//    }

    data class ViewState(
        val president: ColombiaPresident = ColombiaPresident()
    )

    sealed interface ViewEvent {
        data object OnBack : ViewEvent
    }
}