package com.rantolin.marvelcomics.domain.usecases

import com.rantolin.marvelcomics.domain.executor.PostExecutionThread
import com.rantolin.marvelcomics.domain.executor.ThreadExecutor
import com.rantolin.marvelcomics.domain.model.CharacterModel
import com.rantolin.marvelcomics.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ricar on 9/4/17.
 */
class GetCharacterDetailsUseCase @Inject
constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
            private val repository: Repository) : UseCase<CharacterModel, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<CharacterModel> {
        return repository.getCharacterDetails()
    }
}