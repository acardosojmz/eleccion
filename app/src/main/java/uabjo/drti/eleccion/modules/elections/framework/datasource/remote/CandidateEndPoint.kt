package uabjo.drti.eleccion.modules.elections.framework.datasource.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.candidates.CandidatesResponse
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.request.VotesRequest
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.response.VotesResponse


interface CandidateEndPoint {

    @POST("voto")
    suspend fun addVotesToCandidate(@Body votesRequest: VotesRequest) : VotesResponse

    @GET("candidato")
    suspend fun getAllCandidates() : CandidatesResponse
}