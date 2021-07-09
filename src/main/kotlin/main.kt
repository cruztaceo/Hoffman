import java.util.*

fun main() {
    val charArray = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
    val charFreq = intArrayOf(5, 12, 35, 3, 8, 14, 21, 1, 39)

    val rootNode: HuffmanNode? = HuffmanNode.encode(charArray, charFreq)
    HuffmanNode.printCode(rootNode!!)
}

class HuffmanNode : Comparable<HuffmanNode> {
    var frequency = 0
    var char = ' '

    var left: HuffmanNode? = null
    var right: HuffmanNode? = null

    companion object {
        fun printCode(root: HuffmanNode) = printCode(root, "")

        fun encode(charArray: CharArray, charFreq: IntArray): HuffmanNode? {
            val numCharacters = charArray.size

            val priorityQueue = PriorityQueue(numCharacters, HuffmanNode::compareTo)

            charArray.forEachIndexed { index, c ->
                val node = HuffmanNode()
                node.char = c
                node.frequency = charFreq[index]

                node.left = null
                node.right = null

                priorityQueue.add(node)
            }

            var rootNode: HuffmanNode? = null

            while (priorityQueue.size > 1) {

                val first = priorityQueue.poll()
                val second = priorityQueue.poll()
                val newHuffmanNode = HuffmanNode()

                newHuffmanNode.frequency = first.frequency + second.frequency
                newHuffmanNode.char = '-'

                newHuffmanNode.left = first
                newHuffmanNode.right = second

                rootNode = newHuffmanNode
                priorityQueue.add(newHuffmanNode)
            }
            return rootNode
        }

        private fun printCode(root: HuffmanNode, s: String) {
            if (root.left == null && root.right == null && Character.isLetter(root.char)) {
                println("${root.char}: $s")
                return
            }
            printCode(root.left!!, "${s}0")
            printCode(root.right!!, "${s}1")
        }
    }

    override fun compareTo(other: HuffmanNode) = this.frequency - other.frequency
}