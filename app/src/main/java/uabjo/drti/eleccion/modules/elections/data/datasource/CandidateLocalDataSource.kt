package uabjo.drti.eleccion.modules.elections.data.datasource

import uabjo.drti.eleccion.modules.elections.domain.model.Candidate

interface CandidateLocalDataSource {
    suspend fun saveCandidates(candidates : List<Candidate>)
    suspend fun listAllCandidates() : List<Candidate>
}