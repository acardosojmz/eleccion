package uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.candidates

import com.google.gson.annotations.SerializedName

data class CandidatesResponse(

	@field:SerializedName("respuesta")
	val response: Response
)