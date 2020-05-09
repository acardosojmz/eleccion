package uabjo.drti.eleccion.modules.elections.domain.usecase

import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted
import uabjo.drti.eleccion.modules.elections.domain.model.Votes

interface CandidateUseCase {
    suspend fun retrieveCandidates() : List<Candidate>
    suspend fun attachVotesToCandidate(votes : Votes) : CandidatesVoted
}

