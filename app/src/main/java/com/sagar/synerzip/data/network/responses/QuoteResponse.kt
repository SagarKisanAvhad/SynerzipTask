package com.sagar.synerzip.data.network.responses

import com.sagar.synerzip.data.db.entities.Quote

data class QuoteResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)