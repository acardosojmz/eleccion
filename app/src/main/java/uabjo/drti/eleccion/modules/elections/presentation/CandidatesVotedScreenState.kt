package uabjo.drti.eleccion.modules.elections.presentation

import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted


sealed class CandidatesVotedScreenState {
    class Error(val message : String) : CandidatesVotedScreenState()
    class Success(val candidatesVoted: CandidatesVoted) : CandidatesVotedScreenState()
}