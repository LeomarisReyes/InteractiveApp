package com.example.feature.passwordgenerator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.round

class RandomPasswordGeneratorViewModel : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(ViewState())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    fun processEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            ViewEvent.OnCopyPassword -> TODO()
            ViewEvent.OnGeneratedPassword -> generatePassword()
            is ViewEvent.OnIncludeNumberChanged -> _viewStateFlow.update { it.copy(includeNumber = viewEvent.isEnabled) }
            is ViewEvent.OnIncludeSymbolChanged -> _viewStateFlow.update { it.copy(includeSymbol = viewEvent.isEnabled) }
            is ViewEvent.OnIncludeUpperCaseLetterChanged -> _viewStateFlow.update { it.copy(includeUpperCaseLetter = viewEvent.isEnabled) }
            is ViewEvent.OnSizeChanged -> _viewStateFlow.update {
                it.copy(size = round(viewEvent.size))
            }
        }
    }

    private fun generatePassword(
        hasUppercaseLetters: Boolean = viewStateFlow.value.includeUpperCaseLetter,
        hasNumbers: Boolean = viewStateFlow.value.includeNumber,
        hasSymbols: Boolean = viewStateFlow.value.includeSymbol,
        passwordSize: Float = viewStateFlow.value.size
    ) {
        viewModelScope.launch(Dispatchers.Default) {

            var generatedPassword = ""

            val lowerCaseList = ('a'..'z').toList()
            val numberList = if (hasNumbers) ('0'..'9').toList() else emptyList()
            val uppercaseList = if (hasUppercaseLetters) ('A'..'Z').toList() else emptyList()
            val symbolList = if (hasSymbols) {
                ('!'..'/') + (':'..'@') + ('['..'`') + ('{'..'~')
            } else emptyList()

            val passwordPossibleCharacters = mutableListOf<Char>().apply {
                addAll(lowerCaseList)
                if (hasUppercaseLetters) addAll(uppercaseList)
                if (hasNumbers) addAll(numberList)
                if (hasSymbols) addAll(symbolList)
            }

            for (i in 1..passwordSize.toInt()) {
                val index = (System.currentTimeMillis() % passwordPossibleCharacters.size).toInt()
                generatedPassword += passwordPossibleCharacters[index]
                delay(1)
            }

            generatedPassword = ensureContains(generatedPassword, numberList, hasNumbers)
            generatedPassword = ensureContains(generatedPassword, uppercaseList, hasUppercaseLetters)
            generatedPassword = ensureContains(generatedPassword, symbolList, hasSymbols)
            generatedPassword = ensureContains(generatedPassword, lowerCaseList, true)
            _viewStateFlow.update { it.copy(generatedPassword = generatedPassword) }
        }
    }

    private fun ensureContains(
        password: String,
        charList: List<Char>,
        condition: Boolean
    ): String {
        if (!condition || charList.isEmpty()) return password

        val hasRequiredChar = password.any { it in charList }
        if (!hasRequiredChar) {
            val randomChar = charList[(System.currentTimeMillis() % charList.size).toInt()]
            val randomPosition = (System.currentTimeMillis() % password.length).toInt()
            return password.substring(0, randomPosition) +
                    randomChar +
                    password.substring(randomPosition)
        }

        return password
    }

    data class ViewState(
        val includeUpperCaseLetter: Boolean = true,
        val includeNumber: Boolean = false,
        val includeSymbol: Boolean = false,
        val size: Float = 5f,
        val generatedPassword: String = "Password not generated yet"
    )

    sealed interface ViewEvent {
        data class OnIncludeUpperCaseLetterChanged(val isEnabled: Boolean) : ViewEvent
        data class OnIncludeNumberChanged(val isEnabled: Boolean) : ViewEvent
        data class OnIncludeSymbolChanged(val isEnabled: Boolean) : ViewEvent
        data class OnSizeChanged(val size: Float) : ViewEvent
        data object OnGeneratedPassword : ViewEvent
        data object OnCopyPassword : ViewEvent
    }

}