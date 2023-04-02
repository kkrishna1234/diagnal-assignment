package com.android.data.mappers

import com.android.core.constants.Constants
import com.android.data.constants.DataConstants

class FileJsonMapper {

    fun getJsonFileName(pageNumber: Int): String {
        return when (pageNumber) {
            NUMBER_1 -> DataConstants.JsonFileNames.PAGE_1
            NUMBER_2 -> DataConstants.JsonFileNames.PAGE_2
            NUMBER_3 -> DataConstants.JsonFileNames.PAGE_3
            else -> throw Exception(Constants.Error.CONTENT_UNAVAILABLE)
        }
    }

    fun getAllJsonFileNames() = listOf(
        DataConstants.JsonFileNames.PAGE_1,
        DataConstants.JsonFileNames.PAGE_2,
        DataConstants.JsonFileNames.PAGE_3
    )

    companion object {
        private const val NUMBER_1 = 1
        private const val NUMBER_2 = 2
        private const val NUMBER_3 = 3
    }
}
