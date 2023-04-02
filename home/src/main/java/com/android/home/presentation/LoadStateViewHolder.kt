package com.android.home.presentation

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.android.core.constants.Constants
import com.android.home.databinding.ItemProgressFooterBinding

class LoadStateViewHolder(private val view: ItemProgressFooterBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bindData(loadState: LoadState) {
        view.textLoadStateErrorMessage.isVisible = loadState !is LoadState.Loading
        view.progressLoadState.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error &&
            loadState.error.localizedMessage != Constants.Error.CONTENT_UNAVAILABLE
        ) {
            view.textLoadStateErrorMessage.text = loadState.error.localizedMessage
        }
    }

}
