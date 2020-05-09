package uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.candidates
import com.google.gson.annotations.SerializedName

data class CandidateItem(

	@SerializedName("foto")
	val photo: String,

	@SerializedName("nombrecompleto")
	val fullName: String,

	@SerializedName("id")
	val id: Int,

	@SerializedName("sexo")
	val orientation: String,

	@SerializedName("votos")
	val votes: Int,

	@SerializedName("perfil")
	val profile: String
)