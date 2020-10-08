package com.sagar.synerzip.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.sagar.synerzip.data.repository.QuoteRepository
import com.sagar.synerzip.util.lazyDeferred

class QuotesViewModel(
    val repository: QuoteRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}

