package uabjo.drti.eleccion.modules.common.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uabjo.drti.eleccion.modules.common.framework.database.entities.CandidateEntity
import uabjo.drti.eleccion.modules.common.framework.database.dao.CandidatesDAO
import uabjo.drti.eleccion.modules.common.framework.database.entities.UserEntity


@Database(entities = [CandidateEntity::class, UserEntity::class], version = 1)
abstract class ElectionDB : RoomDatabase() {

    abstract fun candidatesDao() : CandidatesDAO
    //abstract fun usersDao(): UserDAO

    companion object {
        fun create(context: Context): ElectionDB {
            return Room.databaseBuilder(
                context,
                ElectionDB::class.java,
                "elections"
            ).build()
        }
    }
}
