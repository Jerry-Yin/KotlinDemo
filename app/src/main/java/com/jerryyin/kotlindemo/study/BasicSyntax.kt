package com.jerryyin.kotlindemo.study

import java.io.File


/**
 * Created by jerryyin on 27/04/2018.
 */
//class BasicSyntax {


val a = 1;  //常量
var b = 2;  //变量

fun sum(): Int {
    return a + b
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf1(a: Int, b: Int): Int = if (a > b) a else b

//fun parseInt(s:String): Int? {
//    compareValuesBy()
//}

//主函数
fun main(args: Array<String>) {
    println("Hello, world!")
    println(args.size)
    print(maxOf(a, b))

    print(c.name + c.email)
    print(c.toString())


    println(files?.size ?: "empty")

    forStep()
}


val s1 = "a is $a"
val s = "${s1.replace("is", "was")}, but now is $a"


var c = Customer("SB", "SSS@XX.COM")


val list = listOf<String>("a", "b", "c")
val list2 = listOf<Int>(1, 2, 3)
val map = mapOf<String, Int>("a" to 1)

val files = File("/test.txt").listFiles()


data class Customer(val name: String, val email: String) {

    override fun toString(): String {
        return "Customer(name='$name', email='$email')"
    }


}


fun describe(obj: Any): String? =
        when (obj) {
            1 -> "One"
            "Hello" -> "Nice to meet u"
            is Long -> "Long"
            !is Int -> "Not a int"
            else -> "Unknown"
        }

fun forStep() {
    for (i in 1..10 step 2) {
        print(i)
    }
}

fun whenFun(){
    var items = listOf("", 1, "dsad", "apple", "orange")
    when{
        "orange" in items -> println("juicy")
        "apple" in items -> println("appleis fine too")
    }
}





//}




