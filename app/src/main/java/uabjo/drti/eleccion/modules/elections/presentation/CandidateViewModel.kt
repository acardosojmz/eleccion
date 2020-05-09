package uabjo.drti.eleccion.modules.elections.presentation

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import uabjo.drti.eleccion.modules.common.domain.ScreenState
import uabjo.drti.eleccion.modules.elections.domain.model.Votes
import uabjo.drti.eleccion.modules.elections.domain.usecase.CandidateUseCase



class CandidatesViewModel(private val candidatesUseCase: CandidateUseCase) : ViewModel() {

    private lateinit var _candidates: MutableLiveData<ScreenState<CandidatesScreenState>>
    private var _candidatesVoted: MutableLiveData<ScreenState<CandidatesVotedScreenState>> =
        MutableLiveData()

    val candidates: LiveData<ScreenState<CandidatesScreenState>>
        get() {
            if (!::_candidates.isInitialized) {
                _candidates = MutableLiveData()
                retrieveAllCandidates()
            }
            return _candidates
        }

    val candidatesVoted: LiveData<ScreenState<CandidatesVotedScreenState>>
        get() = _candidatesVoted

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun retrieveAllCandidates() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _candidates.value = ScreenState.Loading
            val candidatesResult = runCatching { candidatesUseCase.retrieveCandidates() }

            candidatesResult.onSuccess { allCandidates ->
                _candidates.value =
                    ScreenState.Render(CandidatesScreenState.Candidates(allCandidates))
            }.onFailure { error ->
                _candidates.value =
                    ScreenState.Render(CandidatesScreenState.Error(error.message?: "Error"))
            }
        }
    }

    fun saveCandidatesVotes(votes: Votes) {
        //--- save local

        //--- save remote
        viewModelScope.launch(coroutineExceptionHandler) {
            _candidatesVoted.value = ScreenState.Loading
            val candidatesVotedResult = runCatching { candidatesUseCase.attachVotesToCandidate(votes) }
            candidatesVotedResult.onSuccess { candidatesVoted ->
                _candidatesVoted.value =
                    ScreenState.Render(CandidatesVotedScreenState.Success(candidatesVoted))
            }.onFailure { error ->
                _candidatesVoted.value =
                    ScreenState.Render(CandidatesVotedScreenState.Error(error.message?: "Error"))
            }
        }
    }
}