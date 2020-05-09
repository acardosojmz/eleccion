package uabjo.drti.eleccion.modules.elections.data.datasource


import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted
import uabjo.drti.eleccion.modules.elections.domain.model.Votes


interface CandidateRemoteDataSource {
    suspend fun listAllCandidates() : List<Candidate>
    suspend fun sendVotes(votes : Votes) : CandidatesVoted
}