package com.android.data.repository.datasource

import com.android.data.constants.DataConstants
import com.android.data.entity.movies.ContentEntity
import com.android.data.entity.movies.MovieEntity
import com.android.data.extensions.fromJson
import com.android.data.mappers.FileJsonMapper
import com.android.data.utils.FileUtils
import com.google.gson.Gson

class MoviesLocalDataSource(
    private val fileUtils: FileUtils,
    private val gson: Gson,
    private val fileJsonMapper: FileJsonMapper
) : MoviesDataSource {

    override suspend fun getMovies(pageNumber: Int): MovieEntity {
        val fileName = fileJsonMapper.getJsonFileName(pageNumber = pageNumber)
        return getMovieFromJson(fileName)
    }

    override suspend fun searchMovies(query: String): List<ContentEntity> {
        val contentEntityList = arrayListOf<ContentEntity>()
        fileJsonMapper.getAllJsonFileNames().forEach { fileName ->
            contentEntityList.addAll(
                getMovieFromJson(fileName).page.contentItems.content
                    .filter { contentEntity ->
                        contentEntity.name.contains(query, ignoreCase = true)
                    }
            )
        }
        return contentEntityList
    }

    private fun getMovieFromJson(fileName: String): MovieEntity {
        fileUtils.getJsonDataFromAsset(fileName)?.run {
            return gson.fromJson<MovieEntity>(this)
                ?: throw Exception(DataConstants.Error.JSON_PARSE_ERROR)
        } ?: throw Exception(DataConstants.Error.ASSET_READ_ERROR)
    }
}
