package id.ergun.klikdoa.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.repository.DoaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by alfacart on 12/12/22.
 */

class DoaViewModel(private val repository: DoaRepository) : ViewModel() {
  private val _groupedDoas = MutableStateFlow(
    repository.getDoas()
//      .sortedBy { it.doaName }
      .groupBy { it.doaName[0] }
  )
  val groupedDoas: StateFlow<Map<Char, List<Doa>>> get() = _groupedDoas

  private val _query = mutableStateOf("")
  val query: State<String> get() = _query

  fun search(newQuery: String) {
    _query.value = newQuery
    _groupedDoas.value = repository.searchDoa(_query.value)
      .sortedBy { it.doaName }
      .groupBy { it.doaName[0] }
  }
}

class ViewModelFactory(private val repository: DoaRepository) :
  ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(DoaViewModel::class.java)) {
      return DoaViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
  }
}