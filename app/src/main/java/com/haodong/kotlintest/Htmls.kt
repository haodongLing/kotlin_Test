package com.haodong.kotlintest

import java.util.concurrent.ThreadPoolExecutor

/**
 * created by linghaoDo on 2020-05-11
 * description:
 *
 * version:
 */
interface Node {
    fun render(): String
}

class StringNode(val content: String) : Node {
    override fun render(): String {

        return content
    }
}

class BlockNode(val name: String) : Node {
    val children = ArrayList<Node>()
    val properties = HashMap<String, Any>()
    override fun render(): String {
        return """<$name${properties.map { "${it.key}='${it.value}'" }
            .joinToString(" ")}>${children.joinToString("") { it.render() }}</$name>"""
    }

    operator fun String.invoke(block: BlockNode.() -> Unit): BlockNode {
        val node = BlockNode(this)
        node.block()
        this@BlockNode.children += node
        return node
    }

    operator fun String.invoke(value: Any) {
        this@BlockNode.properties[this] = value

    }

    operator fun String.unaryPlus() {
        this@BlockNode.children += StringNode(this)
    }
}

fun html(block: BlockNode.() -> Unit): BlockNode {
    val html = BlockNode("html")
    html.block()
    return html
}

fun BlockNode.head(block: BlockNode.() -> Unit): BlockNode {
    val head = BlockNode("head")
    head.block()
    this.children += head
    return head

}

fun BlockNode.body(block: BlockNode.() -> Unit): BlockNode {

    val body = BlockNode("body")
    body.block()
    this.children += body
    return body

}

class Person2(var name: String, age: Int) {
    init {
        val defaultAge = age
    }

}