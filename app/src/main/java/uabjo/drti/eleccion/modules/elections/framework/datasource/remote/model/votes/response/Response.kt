package uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.response

import com.google.gson.annotations.SerializedName
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.model.votes.request.VoteItem


data class Response(

	@SerializedName("data")
	val data: List<VoteItem>? = null,

	@SerializedName("success")
	val success: Boolean,

	@SerializedName("message")
	val message: String
)