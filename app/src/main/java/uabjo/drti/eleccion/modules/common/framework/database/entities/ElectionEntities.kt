package uabjo.drti.eleccion.modules.common.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "candidatos")
data class CandidateEntity(
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "nombrecompleto")
    val fullName : String,
    @ColumnInfo(name = "sexo")
    val orientation: String,
    @ColumnInfo(name = "votos")
    val votes: Int,
    @ColumnInfo(name = "perfil")
    val profile: String,
    @ColumnInfo(name = "foto")
    val photo : String
)

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "nombrecompleto")
    val nombre : String,
    @ColumnInfo(name = "cuenta")
    val cuenta: String,
    @ColumnInfo(name = "password")
    val passord: String
)
