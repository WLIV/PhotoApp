package com.example.photoapp.utils

import xyz.klinker.android.article.data.Article
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


class ArticleContentParser {

    private val SELECTOR = "p, h1, h2, h3, h4, h5, h6, img, blockquote, pre, li, a"
    fun parseContent(article : Article) : String {
        val doc: Document = Jsoup.parse(article.content)
        val elements: Elements = removeUnnecessaryElements(doc.select(SELECTOR), article)
        return elements.text()

    }

    private fun removeUnnecessaryElements(elements: Elements, article: Article): Elements {
        var elements: Elements? = elements
        var i = 0
        while (i < elements!!.size) {
            val element: Element = elements[i]
            if (i == 0 && (!element.tagName()
                    .equals("p") || element.text() == null || article.title == null || element.text()
                    .contains(article.title))
            ) {
                elements.removeAt(i--)
                i++
                continue
            }
            if (element.tagName().equals("img")) {
                val src: String = element.attr("src")
                if (src == null || src.length == 0 || !isImageUrl(src) || src == article.image) {
                    elements.removeAt(i--)
                }
            } else {
                val text: String = element.text().trim()
                if (text.length == 0 || text == "Advertisement" || text == "Sponsored") {
                    elements.removeAt(i--)
                } else if (i > 0 && text == elements[i - 1].text().trim { it <= ' ' }) {
                    elements.removeAt(i--)
                }
            }
            i++
        }
        if (elements.size > 0) {
            var lastTag = elements.last().tagName()
            while (lastTag != "p" && lastTag != "img") {
                elements.removeAt(elements.size - 1)
                lastTag = elements.last().tagName()
            }


        }
        return elements
    }

    private fun isImageUrl(src: String): Boolean {
        return src.contains("jpg") || src.contains("png") || src.contains("gif")
    }

}