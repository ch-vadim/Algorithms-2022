package lesson4

import java.util.*

/**
 * Префиксное дерево для строк
 */
class KtTrie : AbstractMutableSet<String>(), MutableSet<String> {

    private class Node {
        val children: SortedMap<Char, Node> = sortedMapOf()
    }

    private val root = Node()

    override var size: Int = 0
        private set

    override fun clear() {
        root.children.clear()
        size = 0
    }

    private fun String.withZero() = this + 0.toChar()

    private fun findNode(element: String): Node? {
        var current = root
        for (char in element) {
            current = current.children[char] ?: return null
        }
        return current
    }

    override fun contains(element: String): Boolean =
        findNode(element.withZero()) != null

    override fun add(element: String): Boolean {
        var current = root
        var modified = false
        for (char in element.withZero()) {
            val child = current.children[char]
            if (child != null) {
                current = child
            } else {
                modified = true
                val newChild = Node()
                current.children[char] = newChild
                current = newChild
            }
        }
        if (modified) {
            size++
        }
        return modified
    }

    override fun remove(element: String): Boolean {
        val current = findNode(element) ?: return false
        if (current.children.remove(0.toChar()) != null) {
            size--
            return true
        }
        return false
    }

    /**
     * Итератор для префиксного дерева
     *
     * Спецификация: [java.util.Iterator] (Ctrl+Click по Iterator)
     *
     * Сложная
     */
    override fun iterator(): MutableIterator<String> = KtTrieIterator()

    private inner class KtTrieIterator : MutableIterator<String> {
        var word: String? = null
        val stack = Stack<String>()

        init {
            stackPush(root, "")
        }

        private fun stackPush(node: Node, element: String) {
            for (child in node.children)
                if (child.key == 0.toChar()) stack.add(element)
                else stackPush(child.value, element + child.key)
        }

        //T = O(1)
        //R = O(1)
        override fun hasNext(): Boolean = !stack.isEmpty()

        //T = O(1)
        //R = O(1)
        override fun next(): String {
            if (!hasNext()) throw NoSuchElementException()
            word = stack.pop()
            return word!!

        }

        //T = O(N),
        //R = O(N), N - длина слова
        override fun remove() {
            if (word == null) throw IllegalStateException()
            remove(word)
            word = null
        }
    }

}