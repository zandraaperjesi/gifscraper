package com.zandraa.giflandinfinite.gif.scraper.service

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

@Service
class ScraperService {
    fun getPage(): String{
        var gifUrl = ""
        try{
            val connection: Connection = Jsoup.connect("http://gifland.us")
            connection.userAgent("Mozilla/5.0")
            val document: Document = connection.get()
            val image: Element = document.select("img[alt=Super gif]")[0]
            gifUrl = image.attr("src")
        }
        catch (ex: Exception){
            print(ex)
        }
        return gifUrl
    }

    fun getUrls(getThisMany: Int): List<String>{
        var gifUrls = mutableListOf<String>()
        for (i in 1..getThisMany) {
            gifUrls.add(getPage())
        }
        return gifUrls
    }
}