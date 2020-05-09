package uabjo.drti.eleccion.modules.elections.data.repository

import android.util.Log
import android.widget.Toast
import uabjo.drti.eleccion.modules.elections.data.datasource.CandidateLocalDataSource
import uabjo.drti.eleccion.modules.elections.data.datasource.CandidateRemoteDataSource
import uabjo.drti.eleccion.modules.elections.domain.exception.CandidatesNotFoundException
import uabjo.drti.eleccion.modules.elections.domain.repository.CandidateRepository
import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted
import uabjo.drti.eleccion.modules.elections.domain.model.Votes
import java.lang.Exception


class CandidateRepositoryImpl(
    private val candidateRemoteDataSource: CandidateRemoteDataSource,
    private val candidateLocalDataSource: CandidateLocalDataSource
) : CandidateRepository {

    override suspend fun retrieveCandidates(): List<Candidate> {
        val localCandidates = candidateLocalDataSource.listAllCandidates()
        Log.d("CANDIDATOS_LOCALES", localCandidates.toString())
        val remoteCandidates = try {
            candidateRemoteDataSource.listAllCandidates()
        } catch (exception: Exception) {
            when (exception) {
                is CandidatesNotFoundException -> throw exception
                else -> null
            }
        }
        if (remoteCandidates != null) {
            Log.d("GUARDADO ", remoteCandidates.toString())
            candidateLocalDataSource.saveCandidates(remoteCandidates)
        }
        return remoteCandidates ?: localCandidates
    }
    
    override suspend fun attachVotesToCandidate(votes: Votes): CandidatesVoted =
        candidateRemoteDataSource.sendVotes(votes)
}