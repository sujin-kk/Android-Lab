package com.example.siolab.presentation.ui.main

interface TestRepository {
    fun launchTest(id: String) {
        val a = 1.plus(2)
        val b = 1 + 2
    }
}

sealed class Test<out T> {
    data class Empty(val a: Int): Test<Int>()
}

fun infiniteLoop(): Nothing {
    while (true) {
        println("Hi there!")
    }
}

fun test() {
    infiniteLoop()
}

sealed class Result<out T: Any>

data class Success<out T: Any>(val data: T): Result<T>()
data class Failed(val exception: java.lang.Exception): Result<Nothing>()
object Loading: Result<Nothing>()

//- Sealed Class
//- 스스로가 추상 클래스 → enum과 다르게 상속을 지원
//- Sealed의 자식 클래스가 어떤 것이 있는지 알 수 있음 → else branch가 필요 없는 이유
//- AAA<Int>: AAA<Any> → 상속이 되지 않을것
//- Super에 Sub를 넣을 수 있는 것이 out
//- Sub에 Super를 넣을 수 있는 것이 in
//
//- Nothing, Unit, Any
//- Any → 모든 타입의 조상
//- Unit → 자바의 void
//- Nothing → 모든 타입의 자식, Unit과는 다르게 어떤 값도 포함하지 않는 타입,, 코드 실행흐름이 도착할 수 없는 영역 → 예외를 던질때 사용 ,,