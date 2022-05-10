@file:Suppress("UNUSED_PARAMETER")

package lesson7

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * T = O(N*M)
 * R = O(N*M)
 */
fun longestCommonSubSequence(first: String, second: String): String {
    val table = Array(first.length + 1) { IntArray(second.length + 1) { 0 } }

    for (i in first.indices) {
        for (j in second.indices) {
            if (first[i] == second[j]) table[i + 1][j + 1] = table[i][j] + 1
            else table[i + 1][j + 1] = maxOf(table[i][j + 1], table[i + 1][j])
        }
    }

    var i = first.length
    var j = second.length
    val answer = StringBuilder()
    while (table[i][j] > 0) {
        when {
            table[i][j] == table[i - 1][j] -> i--
            table[i][j] == table[i][j - 1] -> j--
            else -> {
                answer.append(first[i - 1])
                i--
                j--
            }
        }
    }
    return answer.reverse().toString()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 * T = O(N^2)
 * R = O(N)
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty()) return emptyList()
    val prev = Array(list.size) { -1 }
    val d = Array(list.size) { 1 }
    for (i in list.indices) {
        for (j in 0 until i) {
            if (list[j] < list[i] && d[j] + 1 > d[i]) {
                d[i] = d[j] + 1
                prev[i] = j
            }
        }
    }

    var position = 0
    var lenght = d[0]
    for (i in list.indices) {
        if (d[i] > lenght) {
            position = i
            lenght = d[i]
        }
    }
    val answer = mutableListOf<Int>()
    while (position != -1) {
        answer.add(0, list[position])
        position = prev[position]
    }
    return answer
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}
