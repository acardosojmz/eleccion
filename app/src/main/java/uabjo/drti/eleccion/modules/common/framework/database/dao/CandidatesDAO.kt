package uabjo.drti.eleccion.modules.common.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uabjo.drti.eleccion.modules.common.framework.database.entities.CandidateEntity


@Dao
interface CandidatesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCandidates(candidates: List<CandidateEntity>)

    @Query("SELECT * FROM candidatos")
    suspend fun getSavedCandidates(): List<CandidateEntity>
}
