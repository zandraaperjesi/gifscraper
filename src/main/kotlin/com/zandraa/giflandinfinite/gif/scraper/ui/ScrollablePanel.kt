package com.zandraa.giflandinfinite.gif.scraper.ui

import com.vaadin.server.Page
import com.vaadin.ui.Panel
import java.util.*

open class ScrollablePanel : Panel() {

    private var scrollListener: ScrollListener? = null

    init {
        id = UUID.randomUUID().toString().replace("-", "")
        Page.getCurrent().javaScript.execute(
                """
                    var scrollTimer$id = null;

                    document.addEventListener('scroll',
                        (event) => {
                            if (event.target.parentElement.id === '$id') {
                                if (scrollTimer$id) {
                                    clearTimeout(scrollTimer$id);
                                }

                                scrollTimer$id = setTimeout(() => scrollFinished(event), 200);
                            }
                        },
                        true
                    )

                    function scrollFinished(event) {
                        const top = event.target.scrollTop;
                        const bottom = event.target.scrollHeight - event.target.clientHeight - top;
                        const left = event.target.scrollLeft;
                        const right = event.target.scrollWidth - event.target.clientWidth - left;

                        com.example.demo.ui.scrollFinishedCallback$id(top, bottom, left, right);
                    }
                """.trimIndent())
        createScrollListenerCallback()
    }

    fun addScrollListener(listener: ScrollListener) {
        scrollListener = listener
    }

    private fun createScrollListenerCallback() {
        Page.getCurrent().javaScript.addFunction("com.example.demo.ui.scrollFinishedCallback$id") {
            val top = it.getNumber(0).toInt()
            val bottom = it.getNumber(1).toInt()
            val left = it.getNumber(2).toInt()
            val right = it.getNumber(3).toInt()

            scrollListener?.invoke(ScrollEvent(top, bottom, left, right))
        }
    }

}

data class ScrollEvent(
        val top: Int,
        val bottom: Int,
        val left: Int,
        val right: Int
)

typealias ScrollListener = (ScrollEvent) -> Unit
