import java.util.*
import kotlin.collections.ArrayList


fun main(){
    println("Hola")
    // Tipos de variables

    // Inmutabilidad: Es inmutable cuando no se puede reasignar pero si podemos cambiar el contenido
    val inmutable: String = "Fernando"
    //inmutable = "Vicente"  => no se puede reasignar.

    //Mutables (Reasignar == true )
    var mutable: String = "Jhon"
    mutable = "Fernando"

    //val > var

    // Sintaxis Duck typing

    val ejemploVariable  = "Ejemplo"
    val edadEjemplo : Int = 12
    // Se puede hacer uso de los metodos en los strings de JAVA
    ejemploVariable.trim()

    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 's'
    val mayorEdad: Boolean = true
    // clases Java
    val fechaNacimiento: Date = Date()

    if (true){
    }else{}

    val estadoCivi = 's'
    when(estadoCivi){
        ('S') -> {
            print("Acercarse")
        }
        ('C') -> {
            print("Alejarse")
        }
        ('N') -> {
            print("Hablar")
        }
        else -> println("No reconocido")
    }

    val  coqueteo = if(estadoCivil == 'S') "SI" else "NO"


    println(sum(3,4))

    val sumaUno = Suma(3,4)
    val sumaDos = Suma(5,8)
    sumaUno.sumar()
    sumaDos.sumar()
    Suma.agregarHistorial(Suma.historialSumas)
}

fun imprimirNombre(nombre: String): Unit{
    println("Nombre: $nombre")
}
fun calcularSueldo(sueldo: Double,
                   tasa: Double = 12.00, // Es opcional porque esta definido por defecto
                   bonoEspecial: Double? = null // El signo de pregunta al final de la variable quiere deir que puede ser null
): Double{
    // String -> String?
    // Int -> Int?
    // Date -> Date?

    if(bonoEspecial == null){
        return sueldo*(100/tasa)
    }else{
        return sueldo*(100/tasa) + bonoEspecial
    }


    /*  CLASES */


}
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno : Int, dos: Int){
        this.numeroUno = uno
        this.numeroDos = dos
        print(("Inicializando"))
    }
}

abstract class Numeros ( //m Constructor Primario
   // uno: Int, // Parametro
    protected val numeroUno: Int, //Public es opcional
    protected val numeroDos: Int
        ){

   // protected  val numeroUno: Int
     //var cedula: String = ""
    init { // Bloque de codigo del constructor primario // La palabra public en Kotlin es opcional
       this.numeroUno// this es opcional, pero existe dentro de la clase
        this.numeroDos
        print("Inicializando")
    }
}

class Suma(
    uno: Int,
    dos: Int,
): Numeros(uno, dos){
   init {
       this.numeroUno,
       this.numeroDos
   }
    constructor(uno: Int?, dos: Int):this(
        if(uno == null) 0 else uno
        dos
    )
    constructor(uno: Int?, dos: Int?):this(
        if(uno == null) 0 else uno
                dos
    )

    public fun sumar():Int{ //No es necesario colocar el modificador de0 acceso public
        return this.numeroUno + numeroDos
    }
    companion object  { //Atributos y metodos compartidos entre las instancias
        val phi = 3.1416

        fun elevarAlCuadrado(num:Int): Int{
            return num*num
        }
        val historialSumas = ArrayList<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
     }
}


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




main()