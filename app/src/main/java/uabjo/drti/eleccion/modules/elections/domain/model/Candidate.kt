package uabjo.drti.eleccion.modules.elections.domain.model

data class Candidate(
    val id: Int,
    val fullName: String,
    val photo: String,
    val orientation: String,
    val profile: String,
    var votes: Int
)