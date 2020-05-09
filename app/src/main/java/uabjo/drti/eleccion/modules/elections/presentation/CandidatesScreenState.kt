package uabjo.drti.eleccion.modules.elections.presentation

import uabjo.drti.eleccion.modules.elections.domain.model.Candidate


sealed class CandidatesScreenState {
    class Error(val message : String) : CandidatesScreenState()
    class Candidates(val list : List<Candidate>) : CandidatesScreenState()
}