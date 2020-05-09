package uabjo.drti.eleccion.modules.elections.domain.exception

class CandidatesNotFoundException(error: String = "Candidates not found")
    : Exception(error)