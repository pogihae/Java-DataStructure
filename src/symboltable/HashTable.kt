package symboltable

/** Hash Table <br></br>
 *
 * with separate chaining
 * @complexity 1
 *
 * @author pogihae
 */
class HashTable<K, V>(n: Int = 32) {

    private var table: Array<Node<K,V>?>
    private var capacity: Int
    private var size: Int

    init {
        table = arrayOfNulls(n)
        capacity = n
        size = 0
    }

    private fun hashing(key: K) = (key.hashCode() and 0x7fffffff) % capacity

    fun put(key: K, value: V) {
        if (size >= capacity) {
            capacity *= 2
            val newTable = arrayOfNulls<Node<K,V>>(capacity)
            for (e in table) {
                if (e != null) {
                    newTable[hashing(e.key)] = e
                }
            }
            table = newTable
        }

        val hc = hashing(key)
        table[hc] = Node(key, value)
        size++
    }

    fun get(key: K) = table[hashing(key)]?.value

    fun remove(key: K): Boolean {
        val hc = hashing(key)

        if (table[hc] == null) return false

        table[hc] = null
        size--

        return true
    }

    class Node<K, V>(val key: K, val value: V)
}



fun main() {
    val myTable = HashTable<Int, Int>(4)

    for (i in 1..10) {
        myTable.put(i, i)
    }

    for (i in 10 downTo 1) {
        println(myTable.get(i))
    }

    println(myTable.remove(123))
    println(myTable.get(123))
}