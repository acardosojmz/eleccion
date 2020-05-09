package uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.request

import com.google.gson.annotations.SerializedName

data class VotesRequest(
    @SerializedName("imei")
    val imei : String,
    @SerializedName("votos")
    val votes : List<VoteItem>
)