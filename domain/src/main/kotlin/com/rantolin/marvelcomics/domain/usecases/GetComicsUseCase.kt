package com.rantolin.marvelcomics.domain.usecases

import com.rantolin.marvelcomics.domain.executor.PostExecutionThread
import com.rantolin.marvelcomics.domain.executor.ThreadExecutor
import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.domain.repository.Repository
import com.rantolin.marvelcomics.domain.usecases.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetComicsUseCase @Inject
constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
            private val repository: Repository) : UseCase<List<ComicModel>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<List<ComicModel>> {
            return repository.getComics()
    }

}