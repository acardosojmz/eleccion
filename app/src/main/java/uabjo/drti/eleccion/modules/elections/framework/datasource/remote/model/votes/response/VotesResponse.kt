package uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.response
import com.google.gson.annotations.SerializedName

data class VotesResponse(
	@SerializedName("respuesta")
	val response: Response
)