package com.zandraa.giflandinfinite.gif.scraper.frontend

import com.vaadin.server.ExternalResource
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.zandraa.giflandinfinite.gif.scraper.service.ScraperService

@SpringUI
open class MainUI(private val scraperService: ScraperService) : UI() {
    override fun init(request: VaadinRequest?) {
        val panel = Panel("Test Panel")
        val panelContent = VerticalLayout()
        val imgResource = ExternalResource(scraperService.getUrls()[0])
        var img = Image("first", imgResource)
        panelContent.addComponent(img)
        panelContent.addComponent(Label(scraperService.getUrls()[1]))
        panelContent.addComponent(Label(scraperService.getUrls()[2]))
        panel.content = panelContent
        content = panel
    }
}