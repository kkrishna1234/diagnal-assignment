package com.android.data.mappers

import com.android.data.entity.movies.ContentEntity
import com.android.data.entity.movies.ContentItemsEntity
import com.android.data.entity.movies.MovieEntity
import com.android.data.entity.movies.PageEntity
import com.android.domain.model.Content
import com.android.domain.model.ContentItems
import com.android.domain.model.Movie
import com.android.domain.model.Page

internal val movieEntityMapper: (MovieEntity) -> Movie = { movieEntity ->
    with(movieEntity) {
        Movie(page = pageEntityMapper(page))
    }
}

internal val pageEntityMapper: (PageEntity) -> Page = { pageEntity ->
    with(pageEntity) {
        Page(
            title = title,
            totalItems = totalItems,
            pageNumber = pageNumber,
            pageSize = pageSize,
            contentItems = contentItemsEntityMapper(contentItems)
        )
    }
}

internal val contentItemsEntityMapper: (ContentItemsEntity) -> ContentItems =
    { contentItemsEntity ->
        with(contentItemsEntity) {
            ContentItems(content = content.map(contentEntityMapper))
        }

    }

internal val contentEntityMapper: (ContentEntity) -> Content = { contentEntity ->
    with(contentEntity) {
        Content(name = name, posterImage = posterImage)
    }
}
