package id.ergun.klikdoa.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _groupedDoas = MutableStateFlow(
        repository.getDoas()
    )

    val groupedDoas: StateFlow<List<Doa>> get() = _groupedDoas

    private val _favoriteDoas = MutableStateFlow<List<Doa>>(arrayListOf())
    val favoriteDoas: StateFlow<List<Doa>> get() = _favoriteDoas

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedDoas.value = repository.searchDoa(_query.value)
            .sortedBy { it.doaName }
    }

    fun getDoaById(doaId: String): Doa {
        return repository.getDoas().first {
            it.id == doaId
        }
    }

    fun getFavoriteDoas() {
        viewModelScope.launch(Dispatchers.IO) {
//      _favoriteDoas = repository.getFavoriteDoas()//.collect {
//        _favoriteDoas.value = it.groupBy { doa ->
//          doa.doaName[0]
//        }
//      }
        }
    }
}