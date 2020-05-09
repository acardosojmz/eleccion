package uabjo.drti.eleccion.modules.elections.framework.datasource.local


import uabjo.drti.eleccion.modules.common.framework.database.entities.CandidateEntity
import uabjo.drti.eleccion.modules.common.framework.database.dao.CandidatesDAO
import uabjo.drti.eleccion.modules.elections.data.datasource.CandidateLocalDataSource
import uabjo.drti.eleccion.modules.elections.domain.model.Candidate

class CandidateLocalDataSourceImpl(private val candidatesDao: CandidatesDAO)
    :
    CandidateLocalDataSource {
    override suspend fun saveCandidates(candidates: List<Candidate>) {
        val candidateEntities = candidates.map { remoteCandidate ->
            CandidateEntity(
                id =  remoteCandidate.id,
                fullName = remoteCandidate.fullName,
                orientation = remoteCandidate.orientation,
                votes = remoteCandidate.votes,
                profile = remoteCandidate.profile,
                photo = remoteCandidate.photo
            )
        }
        candidatesDao.saveCandidates(candidateEntities)
    }

    override suspend fun listAllCandidates(): List<Candidate> =
        candidatesDao.getSavedCandidates().map { localCandidate ->
            Candidate(
                id =  localCandidate.id,
                fullName = localCandidate.fullName,
                orientation = localCandidate.orientation,
                votes = localCandidate.votes,
                profile = localCandidate.profile,
                photo = localCandidate.photo
            )
        }
}