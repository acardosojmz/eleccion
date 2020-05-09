package uabjo.drti.eleccion.modules.elections.domain.exception

import java.lang.Exception

class WrongVotesException(message : String = "wrong votes") : Exception(message)