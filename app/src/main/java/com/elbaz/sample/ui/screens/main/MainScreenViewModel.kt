package com.elbaz.sample.ui.screens.main

import com.elbaz.sample.ui.base.BaseViewModel
import com.elbaz.sample.data.models.AnimeModel
import com.elbaz.sample.data.AnimeRepository
import com.elbaz.sample.data.local.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class OnboardingState(
    val recent: List<AnimeModel>,
    val popular: List<AnimeModel>,
    val soon: List<AnimeModel>,
    val msg: String,
    val isLoading: Boolean,
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repo: AnimeRepository

) : BaseViewModel() {

    private val _uiState =
        MutableStateFlow(
            OnboardingState(emptyList(), emptyList(), emptyList(), "", false)
        )
    val uiState
        get() = _uiState.asStateFlow()


    init {
        scope.launch(Dispatchers.IO) {
            launch { updateData() }

            repo.getAnimeList()
                .catch { _uiState.emit(uiState.value.copy(msg = it.message.toString())) }
                .collect { list ->
                    Timber.d("Data from localDatabase, size ${list.size}")
                    val recent = mutableListOf<AnimeModel>()
                    val popular = mutableListOf<AnimeModel>()
                    val soon = mutableListOf<AnimeModel>()
                    list.forEach {
                        when (it.category) {
                            Category.RECENT -> {
                                recent.add(it)
                            }

                            Category.POPULAR -> {
                                popular.add(it)
                            }

                            Category.SOON -> {
                                soon.add(it)
                            }
                        }

                    }
                    _uiState.emit(
                        uiState.value.copy(
                            recent = recent,
                            popular = popular,
                            soon = soon, isLoading = false
                        )
                    )
                }
        }
    }

    override fun onCoroutineError(errorMsg: String) {
        super.onCoroutineError(errorMsg)
        scope.launch {
            Timber.d("Error: $errorMsg")
            _uiState.emit(_uiState.value.copy(msg = errorMsg))
        }
    }

    fun onRefresh() {

        scope.launch(Dispatchers.IO) {

            _uiState.emit(_uiState.value.copy(isLoading = true))
            updateData()
            _uiState.emit(_uiState.value.copy(isLoading = false))

        }


    }

    private suspend fun updateData() = repo.updateAnimes()

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}