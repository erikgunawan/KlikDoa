package id.ergun.klikdoa.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.repository.DoaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author erikgunawan
 * Created 24/12/22 at 22.50
 */
@HiltViewModel
class DoaViewModel @Inject constructor(private val repository: DoaRepository) : ViewModel() {
    private val _doaList = MutableStateFlow(
        repository.getDoas()
    )

    val doaList: StateFlow<List<Doa>> get() = _doaList

    private var _favoriteDoas = MutableStateFlow<List<Doa>>(
        value = emptyList()
    )
    val favoriteDoas: StateFlow<List<Doa>> get() = _favoriteDoas// = repository.getFavoriteDoas()

    private val _doaNameQuery = mutableStateOf("")
    val doaName: State<String> get() = _doaNameQuery

    var favoriteDoa by mutableStateOf(Doa.generateDefaultDoa())
        private set

    fun searchDoa(doaName: String) {
        _doaNameQuery.value = doaName
        _doaList.value = repository.searchDoa(_doaNameQuery.value)
            .sortedBy { it.doaName }
    }

    fun getDoaById(doaId: String): Doa {
        return repository.getDoas().first {
            it.id == doaId
        }
    }

    fun searchFavoriteDoa(doaName: String) = viewModelScope.launch(Dispatchers.IO) {
        _doaNameQuery.value = doaName
        _favoriteDoas.value = repository.searchFavoriteDoa(_doaNameQuery.value)
            .sortedBy { it.doaName }
    }

    fun getFavoriteDoaById(doaId: String) = viewModelScope.launch(Dispatchers.IO) {
        favoriteDoa = repository.getFavoriteDoaById(doaId) ?: Doa.generateDefaultDoa()
    }

    fun getFavoriteDoas() = viewModelScope.launch(Dispatchers.IO) {
        _favoriteDoas.value = repository.getFavoriteDoas()
    }

    fun addFavoriteDoa(doa: Doa) = viewModelScope.launch(Dispatchers.IO) {
        repository.addFavoriteDoa(doa)
    }

    fun removeFavoriteDoa(doaId: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.removeFavoriteDoa(doaId)
    }
}