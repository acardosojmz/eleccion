package uabjo.drti.eleccion.modules.elections.domain.repository

import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted
import uabjo.drti.eleccion.modules.elections.domain.model.Votes


interface CandidateRepository {
    suspend fun retrieveCandidates() : List<Candidate>
    suspend fun attachVotesToCandidate(votes : Votes) : CandidatesVoted
}
