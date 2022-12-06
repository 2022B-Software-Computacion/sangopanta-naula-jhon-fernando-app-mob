import java.util.*


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

    val estadoCivil = "s"
    when(estadoCivil){
        ("S") -> {
            print("Acercarse")
        }
        ("C") -> {
            print("Alejarse")
        }
        ("UN") -> {
            print("Hablar")
        }
        else -> print("No reconocido")
    }

    val  coqueteo = if(estadoCivil == "S") "SI" else "NO"


    println(sum(3,4))
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
}
fun sum(numer1: Int, number2: Int) : Int {
    return numer1 + number2;
}
main()