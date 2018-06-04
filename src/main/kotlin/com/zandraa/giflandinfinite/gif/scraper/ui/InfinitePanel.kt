package com.zandraa.giflandinfinite.gif.scraper.ui

import com.vaadin.server.ExternalResource
import com.vaadin.ui.Image
import com.vaadin.ui.VerticalLayout
import com.zandraa.giflandinfinite.gif.scraper.service.ScraperService
import org.vaadin.addons.scrollablepanel.ScrollablePanel


class InfinitePanel(val scraper: ScraperService): ScrollablePanel() {

    private var lastOffset = 0
    private val layout = VerticalLayout()

    init {
        setHeight("70em")
        updateContents( 5)

        addScrollListener {
            if (it.bottom < 100) {
                updateContents(3)
            }
        }

        content = layout
    }

    private fun updateContents(offset: Int) {
        scraper.getUrls(offset).forEach {
            val imgResource = ExternalResource(it)
            var img = Image(it, imgResource)
            layout.addComponent(img)
        }
        lastOffset += offset
    }
}