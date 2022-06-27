package com.example.andr2_kot_lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.btn).setOnClickListener (object : View.OnClickListener {
            override fun onClick(p0: View?) {
            }
        })

        val dataClass1 = Note("some_name", "note_body", R.color.black)
        val dataClass2 = dataClass1.copy(color = R.color.teal_200) // Полное копирование, с созданием и нового экземпляра, и ссылки на него

        // анонимный класс (напоминание) - получение сущности(экземпляра) без реализации класса
        val andrej = object {
            val name = "Andrej"
            var age = 20
        }
        andrej.age = 21

        // Switch переимнован в when, изменен синтаксис
        val enumEl = WeatherType.RAINY
        val todayCondition = when(enumEl){
            WeatherType.RAINY -> "дождиво"
            else -> {}
        }

        myToStringJava(andrej.age)
        myToStringKotlin(andrej.age)

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

        NewTest.Name.staticField // обращение к статике (через "псевдоним этой тюрьмы" :D) #статика
        // <- используется, если единожды в классе надо иметь статичное поле
        NewTestSingleTone.Name // второй вариант статики - через синглтон. Целый статический класс #статика

        // Циклы
        val daysOfWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        Log.d("@@@", "----------------")
        daysOfWeek.forEach { // Самое распространённое
            Log.d("@@@", "$it")
        }

        Log.d("@@@", "----------------")
        for (i in daysOfWeek) { // Аналог forEach
            Log.d("@@@", "$i")
        }

        Log.d("@@@", "----------------")
        repeat(daysOfWeek.size) {
            Log.d("@@@", "${daysOfWeek[it]}")
        }

        // Менее популярные:
        Log.d("@@@", "----------------")
        for (i in daysOfWeek.indices) {
            Log.d("@@@", "${daysOfWeek[i]}")
        }

        Log.d("@@@", "----------------")
        for (i in 0..daysOfWeek.size-1) {
            Log.d("@@@", "${daysOfWeek[i]}")
        }

        Log.d("@@@", "----------------")
        for (i in daysOfWeek.size-1 downTo 0 step 1) { // опциональное управление шагом step
            Log.d("@@@", "${daysOfWeek[i]}")
        }
        Log.d("@@@", "----------------")
        for (i in 0 until daysOfWeek.size) {
            Log.d("@@@", "${daysOfWeek[i]}")
        }
        Log.d("@@@", "--------while--------")
        var counter = daysOfWeek.size
        while (--counter >= 0) {
            Log.d("@@@", "${daysOfWeek[counter]}")
        }
        Log.d("@@@", "--------do-while--------")
        counter = daysOfWeek.size-1
        do {
            Log.d("@@@", "${daysOfWeek[counter]}")
        } while (--counter >= 0)
    }

    // Оператор if, ветвления
    private fun myToStringJava(age:Int):String{ // Java-подход
        var result = ""
        if (age == 20){
            result = "двадцать"
        }else{
            result = "не двадцать"
        }
        return result
    }
    private fun myToStringKotlin(age:Int):String{ // Kotlin-подход
        return if (age == 20){
            "двадцать"
            "двадцать" //возвращает последнюю строку
        }else{
            "не двадцать"
        }
    }

    // Switch переимнован в when, изменен синтаксис
    val field3 = 1..100
    val field4 = 5
    val WTV = when(field4){
        in field3 -> {
            "входит"
        }
        else -> {
            "не входит"
        }
    }

    enum class WeatherType {
        SUNNY, RAINY, SNOWY
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

    // статичные поля. Правила обращения. #статика
// Избегаем спагетти-код - заточаем всю статику "в тюрьму"
    companion object Name{ // выдялем резервацию, можно дать ей псевдоним, заключаем статику сюда
        const val staticField = "statica99!"
    }
}

object NewTestSingleTone{// Синглтон. Сокращен весь бойлерплейт-код
    var newField:String = ""
     object Name{
        const val staticField = "statica99!"
    }

}

// Модификаторы видимости:
// private - закрыто на замке -//-
// public - видно всем -//-
// protected - есть нюансы.. В Java - видимость во всез классах, расширяющий данный (+ родитель)
// internal - видимость внутри модуля (package в Kotlin совсем на заднем плане, использование пакетов меньше)