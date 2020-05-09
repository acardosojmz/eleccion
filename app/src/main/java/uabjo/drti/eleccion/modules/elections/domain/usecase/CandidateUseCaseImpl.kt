package uabjo.drti.eleccion.modules.elections.domain.usecase


import uabjo.drti.eleccion.modules.elections.domain.repository.CandidateRepository
import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted
import uabjo.drti.eleccion.modules.elections.domain.model.Votes

class CandidateUseCaseImpl(private val candidateRepository: CandidateRepository)
    : CandidateUseCase {
    override suspend fun retrieveCandidates(): List<Candidate> =
        candidateRepository.retrieveCandidates()

    override suspend fun attachVotesToCandidate(votes: Votes): CandidatesVoted =
        candidateRepository.attachVotesToCandidate(votes)
}