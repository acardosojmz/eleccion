package uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.request

import com.google.gson.annotations.SerializedName

data class VoteItem(
    @SerializedName("candidato_id")
    val candidateId : Int,
    @SerializedName("votos")
    val votes : Int
)