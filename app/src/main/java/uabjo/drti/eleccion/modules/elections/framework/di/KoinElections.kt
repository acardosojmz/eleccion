package uabjo.drti.eleccion.modules.elections.framework.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import uabjo.drti.eleccion.modules.common.framework.database.ElectionDB
import uabjo.drti.eleccion.modules.common.framework.database.dao.CandidatesDAO
import uabjo.drti.eleccion.modules.elections.data.datasource.CandidateLocalDataSource
import uabjo.drti.eleccion.modules.elections.data.datasource.CandidateRemoteDataSource
import uabjo.drti.eleccion.modules.elections.data.repository.CandidateRepositoryImpl
import uabjo.drti.eleccion.modules.elections.domain.repository.CandidateRepository
import uabjo.drti.eleccion.modules.elections.domain.usecase.CandidateUseCase
import uabjo.drti.eleccion.modules.elections.domain.usecase.CandidateUseCaseImpl
import uabjo.drti.eleccion.modules.elections.framework.datasource.local.CandidateLocalDataSourceImpl
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.CandidateEndPoint
import uabjo.drti.eleccion.modules.elections.framework.datasource.remote.CandidateRemoteDataSourceImpl
import uabjo.drti.eleccion.modules.elections.presentation.CandidatesViewModel


fun providesCandidateEndPoint(retrofit: Retrofit): CandidateEndPoint =
    retrofit.create(CandidateEndPoint::class.java)

fun providesCandidateRemoteDataSource(candidateEndPoint: CandidateEndPoint): CandidateRemoteDataSource =
    CandidateRemoteDataSourceImpl(candidateEndPoint)

fun providesCandidatesDao(candidatesDatabase: ElectionDB): CandidatesDAO =
    candidatesDatabase.candidatesDao()

fun providesCandidateLocalDataSource(candidatesDao: CandidatesDAO): CandidateLocalDataSource =
    CandidateLocalDataSourceImpl(candidatesDao)

fun providesCandidateRepository(
    candidateRemoteDataSource: CandidateRemoteDataSource,
    candidateLocalDataSource: CandidateLocalDataSource
): CandidateRepository =
    CandidateRepositoryImpl(candidateRemoteDataSource, candidateLocalDataSource)

fun providesCandidateUseCase(candidateRepository: CandidateRepository): CandidateUseCase =
    CandidateUseCaseImpl(candidateRepository)

val candidatesModule = module {
    single { providesCandidateEndPoint(get()) }
    single { providesCandidateRemoteDataSource(get()) }
    single { providesCandidatesDao(get()) }
    single { providesCandidateLocalDataSource(get()) }
    single { providesCandidateRepository(get(), get()) }
    single { providesCandidateUseCase(get()) }
    viewModel { CandidatesViewModel(get()) }
}
