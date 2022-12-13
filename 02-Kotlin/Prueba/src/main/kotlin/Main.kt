fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    // Filter
    // 1) Devolver una expresión (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
                val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilter2 = arregloDinamico.filter {it <= 5}
    println(respuestaFilter)
    println(respuestaFilter2)


    // OR AND
    // OR ==>  Any (Alguno Cumple?)
    // AND ==> All (Todos Cumplen)

    val respuestaAny: Boolean = arregloDinamico
        .any{
            valorActual:Int ->
            return@any(valorActual > 5)
        }
    println(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico
        .all{
            valorActual: Int ->
            return@all(valorActual > 5)
        }

    // Operador reduce

    //REDUCE -> Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1,2,3,4,5] -> Siempre todos los valoes del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce{ // Acumulado = 0 -> Siempre empieza en 0
            acumulado: Int, valorActual:Int ->

            return@reduce(acumulado+valorActual)
        }



    println(respuestaAll)
}

fun sumar(num1: Int, num2: Int): Int{
    return num1+num2
}