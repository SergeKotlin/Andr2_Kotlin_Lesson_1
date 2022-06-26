package com.example.andr2_kot_lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fieldA = 5
        val fieldB = 5 // Дефолт по привычке делать val - хороший подход

        val test:Test = Test(1, 2)
        val newTest = NewTest(2 ,4)

        //LifeHack!   Чтобы переименовать переменную везде – зажать Shift+F6

        // в Kotlin у классов вообще нет полей (кроме теневых*) - есть только свойства
        // поля закрыты, чтобы прогеры не стреляли себе в ноги. Все обращения к полям организованы через свойства
        // в исключительных случаях нек. поля, для которых автоматически работают свойства, нужно изменить - теневые поля
        // – см. геттер и сеттер для newField в классе NewТест (это и есть переопрделение - геттер сразу после создания поля)
        val newi = test.varI //в байткоде увидим геттер
        test.varI = newi //в байткоде увидим сеттер

        test.name()
        Log.d("@@@", newTest.newField)
        Log.d("@@@", "${newTest is NewTest}") //проверяем (аналог instanceOf)
        Log.d("@@@", (newTest as NewTest).newField) //кастим в Котлин (cast в Java)
    }
}

open class Test constructor(val valI: Int, var varI: Int) {  //с ключ. словами val(var) мы задаём свойства,
// без них - просто параметры конструктора)

    protected open val protString = ""

    constructor(field: String) : this(-1, -1)  //слово конструктор используется напр. для перегрузки конструктора..
    // иначе можно опустить

    private lateinit var stringVar: String
    fun name(){
        stringVar = "SD"
        Log.d("@@@", stringVar)
    }
}

// Наследование
class NewTest(field0: Int, field2: Int):Test(field0, field2){

    public override val protString:String = ""

    var newField:String = ""
    get() {
        //return "$newField plus"
        return "$field get"
    }
    set(value) {
        field = "$value set"
    }
    init{ //init быстрее конструктора вызывается
        newField = "newField"
    }
}

// Модификаторы видимости:
// private - закрыто на замке -//-
// public - видно всем -//-
// protected - есть нюансы.. В Java - видимость во всез классах, расширяющий данный (+ родитель)
// internal - видимость внутри модуля (package в Kotlin совсем на заднем плане, использование пакетов меньше)