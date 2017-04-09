package com.rantolin.marvelcomics.data.cloud.responsemodels

/**
 * Created by ricar on 8/4/17.
 */
data class ComicsResponse<T> (var data: DataEntity<T>)

data class DataEntity<T> (var results: List<T>)
