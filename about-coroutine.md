# 코루틴 이해하고 싶다

### 함수 vs 서브루틴

> 함수는 call/return, 코루틴은 suspend/resume

- 둘 다 블록 내부에 정의된 로직을 실행하고 완료되어야 다음으로 넘어가는 구조.
- 함수는 한번 호출하면 다음 코드의 동작이 중지, 함수 내부 로직을 모두 수행할 때 까지 실행이 중지되지 않는다.
- 서브루틴은 **로직 중간 지점에서도 시작이나 종료가 이루어질 수 있다**. 실행을 일시중지하고 다른 코루틴으로 이동하거나, 일시중지된 지점에서 재시작 할 수도 있다.

### 코루틴

- 코루틴은 서브루틴의 확정된 개념, 일종의 경량 쓰레드(저비용 쓰레드)
- 코루틴은 쓰레드의 대안이 아니라, **기존의 쓰레드를 더 작게 쪼개쓰는 개념**
- 코루틴은 크게 stackless와 stackful 두가지 종류로 나뉘는데, 코틀린의 경우 stackless하게 구현되어있다.

### 쓰레드 vs 코루틴
- 참고 링크 : [https://stackoverflow.com/questions/43021816/difference-between-thread-and-coroutine-in-kotlin](https://stackoverflow.com/questions/43021816/difference-between-thread-and-coroutine-in-kotlin)
- 참고 링크2 : [https://stackoverflow.com/questions/1934715/difference-between-a-coroutine-and-a-thread](https://stackoverflow.com/questions/1934715/difference-between-a-coroutine-and-a-thread)
> 쓰레드는 preemptively multitasking, 코루틴은 cooperatively multitasking

- 둘 다 여러 개의 작업을 동시에 처리할 때 **동시성을 보장하기 위한 목적**으로 사용한다.
- 쓰레드는 OS 차원에서 직접 자원을 할당하고 많은 시스템 자원을 사용한다.
- 쓰레드의 Context Switching이 발생할 때도 CPU의 상태 체크가 필요해 비용이 발생한다.
- 코루틴은 전환될 때 Context Switching이 발생하지 않는다. 작업 전환 시 시스템(OS)의 영향을 받지 않으므로 비용 소모가 적다.
- 코루틴은 개발자가 루틴의 실행과 종료 시점을 모두 지정할 수 있다. 실행 중 얼마든지 동작을 취소시킬 수 있다.
- 코루틴이 경량 쓰레드라 칭해지긴 하지만 쓰레드와는 동작 방식이 다름. Task마다 새로운 Thread를 할당하는 것이 아니라 작은 Object(Coroutine)를 할당함.

### 장점
- 간결한 코드로 비동기 처리가 가능하며 멀티 스레드를 사용하는 것보다 훨씬 적은 자원을 소비
- 작업 단위가 Thread -> Object(Coroutine)로 축소되면서 Thread 생성, Context Switching 등에 필요한 자원을 아낄 수 있다.
    - 따라서 코루틴의 장점을 활용하려면 가급적 단일 쓰레드에서 여러 Object를 실행하는 것이 좋음.

### 안드로이드에서 코루틴 사용

- 코틀린 공식 문서 : [https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
- dependency에 추가
```
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1'
```

### 주요 키워드
- CoroutineScope : 코루틴의 실행 범위, 코루틴 블록을 제어할 수 있는 단위
    - GlobalScope : CoroutineScope의 종류 중 하나로 미리 정의된 방식으로 프로그램 전반에 걸쳐 백그라운드에서 동작한다.
- CoroutineContext : 코루틴을 **어떻게 처리할 것인지에** 대한 정보의 집합. 주요 요소로는 Job과 Dispatcher가 있다.
- Dispatcher
    - Dispatchers.Default : 백그라운드에서 처리해야 하는 무거운 작업
    - Dispatchers.IO : 네트워크, 디스크 사용 작업, 파일이나 소켓 작업 다루는데 최적화
    - Dispatcher.Main : UI 쓰레드에서 작업
- launch, async : 코루틴 블록을 실행할 때 사용하는 키워드. 서로 반환 타입이 다르다.
    - launch : Job 반환
    - async : Defferd 반환. 코루틴 블록의 실행 결과 값을 사용하고 싶으면 async를 사용해야 한다.
- runBlocking : 블록 안의 코드를 비동기로 실행, 모든 처리가 끝날 때 까지는 다음 코드가 실행되지 않는다.

### 코루틴 사용 방법

1. Dispatcher(=작업의 종류)를 결정
2. Dispatcher로 CoroutineScope 생성
3. CoroutineScope의 launch/async에 실행할 코드 블록을 넘긴다

- 동일한 표현
```
    // Thread를 생성
    Thread(Runnable {...}).start()
    
    // CoroutineScope를 생성
    GlobalScope.launch(Dispatchers.Default) {...}
    
    CoroutineScope(Dispatchers.Main).launch {
        // 포그라운드에서 처리
    }
    
    CoroutineScope(Dispatchers.Default).launch {
        // 백그라운드에서 처리
    }
```
- 기존 scope의 Dispatcher를 변경하면 CoroutineScope는 유지되고 작업이 처리되는 쓰레드만 바뀜
- 새로운 scope를 만들면 제어 범위 자체가 바뀜
- 비동기 처리
```
    fun main(args: Array<String>) {
        runBlocking {
            println("0")
            val jab = launch {
                delay(1000L)
                println("1") // 1초 후에 출력
            }
            jab.join() // 사용하지 않을 경우 0 -> 2 -> 1 -> 3 순으로 출력됨
            println("2")
        }
    
        println("3") // runBlocking 종료 후 실행됨
    }
```
- 비동기 처리 결과 값을 사용하고 싶을 때
```
    fun main(args: Array<String>) {
        runBlocking {
            println("0")
            val deffered = async {
                delay(1000L)
                println("1") // 1초 후에 출력
                return@async "result value"
            }
            val data = deffered.await() // 비동기 처리 결과 값을 반환
            println("2 $data")
            delay(1000L)
        }
    }
```
### 그외 참고자료
- [withContext vs async-await](https://stackoverflow.com/questions/50230466/kotlin-withcontext-vs-async-await)
- [runBlocking vs coroutineScope](https://eso0609.tistory.com/82)
- [패턴과 안티패턴](https://medium.com/harrythegreat/번역-코틀린-코루틴의-패턴과-안티패턴-6e97f852ea2d)
