package com.eternia.ai.rpg.personas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonaViewModel @Inject constructor() : ViewModel() {

    fun loadPersonas() {
        viewModelScope.launch {
            // TODO: Load personas from repository
        }
    }
}
