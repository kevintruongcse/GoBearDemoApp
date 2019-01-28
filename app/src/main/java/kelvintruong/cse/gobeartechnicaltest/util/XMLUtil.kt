package kelvintruong.cse.gobeartechnicaltest.util

import kelvintruong.cse.gobeartechnicaltest.model.Article
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

object XMLUtil {

    fun getDocumentFromStream(inputStream: InputStream): Document? {
        try {
            val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            return builder.parse(InputSource(inputStream))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getArticleList(document: Document): ArrayList<Article> {
        val articleList = ArrayList<Article>()
        val xPath = XPathFactory.newInstance().newXPath()
        val expression = "//channel/item"

        val itemNodeList = xPath.compile(expression).evaluate(document, XPathConstants.NODESET) as NodeList

        for (i in 0 until itemNodeList.length) {
            val nodeItem = itemNodeList.item(i)

            var title = ""
            var description = ""
            var imgUrl = ""
            var pubDate = ""
            var link = ""

            for (j in 0 until nodeItem.childNodes.length) {
                val node = nodeItem.childNodes.item(j)

                if (node.nodeType == Node.ELEMENT_NODE) {
                    if (node.hasChildNodes()) {

                        // Title
                        if (node.nodeName == "title") {
                            title = node.firstChild.nodeValue
                        }

                        // Description
                        if (node.nodeName == "description") {
                            description = node.firstChild.nodeValue
                        }

                        // Publish date
                        if (node.nodeName == "pubDate") {
                            pubDate = node.firstChild.nodeValue
                        }

                        // Link
                        if (node.nodeName == "link") {
                            link = node.firstChild.nodeValue
                        }
                    }

                    // Thumbnail
                    if (node.nodeName == "media:thumbnail") {
                        imgUrl = (node.attributes.getNamedItem("url") as Attr).value
                    }

                }
            }
            articleList.add(Article(title, pubDate, description, imgUrl, link))
        }


        return articleList
    }
}