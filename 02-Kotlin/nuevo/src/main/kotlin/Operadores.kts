fun main(){

    //ARREGLOS

    //Tipos de arreglos


//Arreglo estatico
    val arregloEstatico:  Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglos dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

// Operadores -> Sirven para los arreglos estáticos y dinámicos

//For Each -> Unit
//Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor Actual: $valorActual")
        }

    arregloEstatico
        .forEachIndexed{indice: Int, valorActual:Int ->
          println("Valor actual ${valorActual} Indice: ${indice}")
        }

    /*  Map  */
    val respuestaMap:List<Double> = arregloDinamico
        .map{valorActual:  Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map {it +15}
       /* .map{valorActual: Int ->
            return@map valorActual + 15
        }*/
    println(respuestaMapDos)
}


main()