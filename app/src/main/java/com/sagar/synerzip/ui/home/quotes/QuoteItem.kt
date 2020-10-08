package com.sagar.synerzip.ui.home.quotes

import com.sagar.synerzip.R
import com.sagar.synerzip.data.db.entities.Quote
import com.sagar.synerzip.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_quote
    }

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.quote = quote
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as QuoteItem
        if (quote != other.quote) return false
        return true
    }

    override fun hashCode(): Int {
        return quote.hashCode()
    }
}