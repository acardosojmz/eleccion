package uabjo.drti.eleccion.modules.elections.framework.datasource.remote
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.withContext
import uabjo.drti.eleccion.modules.elections.data.datasource.CandidateRemoteDataSource
import uabjo.drti.eleccion.modules.elections.domain.exception.CandidatesNotFoundException
import uabjo.drti.eleccion.modules.elections.domain.exception.WrongVotesException
import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted
import uabjo.drti.eleccion.modules.elections.domain.model.Vote
import uabjo.drti.eleccion.modules.elections.domain.model.Votes
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.request.VoteItem
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.request.VotesRequest


class CandidateRemoteDataSourceImpl(private val candidateEndPoint: CandidateEndPoint) :
    CandidateRemoteDataSource {
    override suspend fun listAllCandidates(): List<Candidate> {
        val candidateResponse = candidateEndPoint.getAllCandidates()
        if (candidateResponse.response.success) {
           val conversion =  candidateResponse.response.data.map { candidateItem ->
                    Candidate(
                        id = candidateItem.id,
                        fullName = candidateItem.fullName,
                        photo = candidateItem.photo.orEmpty(),
                        orientation = candidateItem.orientation,
                        profile = candidateItem.profile.orEmpty(),
                        votes = candidateItem.votes
                    )
                }
            return (conversion)
        } else {
            throw CandidatesNotFoundException(candidateResponse.response.message)
        }
    }

    override suspend fun sendVotes(votes: Votes): CandidatesVoted {
        val votesSent = candidateEndPoint.addVotesToCandidate(
            VotesRequest(
                imei = votes.imei,
                votes = votes.candidates.map { vote ->
                    VoteItem(candidateId = vote.candidateId, votes = vote.numberOfVotes)
                }
            )
        )
        if (votesSent.response.success) {
            val lastVotesSent = votesSent.response.data ?: emptyList()
            return CandidatesVoted(
                message = votesSent.response.message,
                candidatesVoted = lastVotesSent.map { voteItem ->
                    Vote(candidateId = voteItem.candidateId, numberOfVotes = voteItem.votes)
                }
            )
        } else {
            throw WrongVotesException(votesSent.response.message)
        }
    }
}