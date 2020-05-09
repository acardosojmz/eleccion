package uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.candidates
import com.google.gson.annotations.SerializedName

data class Response(

	@SerializedName("data")
	val data: List<CandidateItem>,

	@SerializedName("success")
	val success: Boolean,

	@SerializedName("message")
	val message: String
)