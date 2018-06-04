package com.zandraa.giflandinfinite.gif.scraper.ui

import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.zandraa.giflandinfinite.gif.scraper.service.ScraperService

@SpringUI
class MainUI(private val scraperService: ScraperService) : UI() {
    override fun init(request: VaadinRequest?) {
        val layout = VerticalLayout(
                InfinitePanel(scraperService)
        )

        content = layout
    }
}