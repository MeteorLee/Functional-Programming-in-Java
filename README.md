# 함수형 프로그래밍 연습 프로젝트 By Java

# 함수형 프로그래밍

## 함수

- f(x) 함수는 input을 받아 output을 리턴하는 하나의 동사에 가깝다.
- 반대로 객체는 동사가 아닌 명사에 가깝다.
- 자바는 객체지향적 프로그래밍이기에 함수형 프로그래밍을 사용하기 껄끄럽다.
- 함수는 역할을 수행하는 직관적인 존재이다.

#### 명령형 프로그래밍
Imperative Programming

- OOP 객체 지향 프로그래밍
- How to do?
- 어떻게 하여야 하는가?

#### 선언형 프로그래밍
Declarative Programming

- Functional Programming 함수형 프로그래밍
- What to do?
- 무엇을 하여아 하는가?


#### 문제해결 방식

어떤 문제가 있을 때 동사 형태의 행동을 추상화하는 것이 쉬울 때가 있다.
하지만 자바는 객체지향적 명사의 형태를 사용하기에 함수형 프로그래밍을 이용하여 쉽게 구현하고자 한다.

## 1급 시민으로서 함수

#### First-Class Citizen 조건

- 함수/메서드의 매개변수(parameter)로서 전달할 수 있는가?
- 함수/메서드의 반환값(return)이 될 수 있는가?
- 변수에 담을 수 있는가?

객체는 3가지 조건을 가지고 있기에 1급 시민이다. 하지만 일반적인 함수는 3개의 조건을 만족할 수 없다. 

따라서 우리는 함수형 프로그래밍을 통해서 함수를 1급 시민으로 만들 것이다.

## 알아야 하는 이유

#### 역할에 충실한 코드
- 가독성 좋은 코드를 통해 유지/보수에 용이함
- 버그로부터 안점함
- 확정성에 용이함

#### 패러다임 전환
- Stream, Optional 함수형 프로그래밍을 사용하는 다양한 기능을 사용 가능
- 다양한 활용 가능성

# Lambda Expression

## Function Interface

함수를 1급 시민으로 만들기위해 Object 형태로 나타내기로 함
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply (T t);
}
```

### @Funcional Interface의 뼈대

- 단 하나의 abstract method만을 가지는 인터페이스로 Single Abstract Method interface(SAM)이라 한다.
- Default method 와 static method는 이미 구현되어 있으므로 괜찮다
- java.lang.Runnable, java.util.Comparator, java.util.concurrent.Callabe ..


## Lambda Expression

함수형 인터페이스를 구현하는 가장 간단한 방법

#### 이름없는 함수 (Anonymous Function)
- Function의 구현체 클래스가 많아 좋지 않기에 함수의 이름을 없앤 함수 

```java
(Integer x) -> {
    return x + 10;
}
```

#### 더 단순하게

- 매개변수의 타입이 유추 가능할 경우 타입 생략 가능
- 매개변수가 하나일 경우 괄호 생략 가능
- 바로 리턴하는 경우 중괄호 생략 가능

```java
x -> x +10;
```

#### 2개의 파라미터

```java
(Integer x, Integer y) -> {
    return x + y);
}

(x, y) -> x + y;
```

3개부터는 사용자 직접 만들어야함

## Functional Interface

#### Supplier
- 입력을 받지 않고 리턴만 하는 함수

```java
@FunctionalInterface
public interface Supplier<T> {

    T get();
}
```

#### Consumer, BiConsumer
- 입력은 받고 리턴은 하지 않는 함수
```java
@FunctionalInterface
public interface Consumer<T> {

    void accept(T t);
}

```

```java
@FunctionalInterface
public interface BiConsumer<T, U> {

    void accept(T t, U u);
}

```

#### Predicate

- 입력을 받아 boolean을 리턴하는 함수로 입력을 받아 참, 거짓 구분 시 사용

```java
@FunctionalInterface
public interface Predicate<T> {
    
    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }
}

```

#### Comparator

- 비교를 위한 인터페이스로 음수, 0, 양수로 구분함

```java
@FunctionalInterface
public interface Comparator<T> {
    
    int compare(T o1, T o2);
}
```

- 음수면 o1 < o2
- 0면 o1 = o2
- 양수면 o1 > o2

## Method Reference

- 기존에 이미 선언되어있는 메서드를 지정하고 싶을 때
- :: 오퍼레이터 사용
- 생략이 많기 때문에 사용할 메서드의 매개변수의 타입과 리턴 타입을 미리 숙지해야함

### 4가지 케이스

- 클래스의 static method를 지정할 때
  - ClassName::staticMethodName
- 선언된 객체의 instance method를 지정할 때
  - objectName::instanceMethodName
- 객체의 instatnce method를 지정할 때
  - ClassName::instanceMethodName
- 클래스의 constructor를 지정할 때
  - ClassName::new

## Stream

- 데이터의 흐름
- 컬렉션(Collection) 형태로 구성된 데이터를 람다를 이용해 간결하고 직관적으로 프로세스하게 해줌
- For, While 등을 이용하던 기존 loop을 대체
- 손쉽게 병렬 처리를 할 수 있게 해줌

### Filter

- 만족하는 데이터만 걸러내는데 사용
- Predicate에 true를 반환하는 데이터만 존재하는 stream을 리턴
```java
Stream<T> filter(Predicate<? super T> predicate);
```

### Map

- 데이터를 변형하는데 사용
- 데이터에 해당 함수가 적용된 결과물을 제공하는 stream을 리턴

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

### Stream의 구성요소

Stream은 3가지의 구성요소로 이루어져 있음

1. Source (소스)

컬렉션, 배열

2. Intermediate Operations (중간 처리)

0개 이상의 filter, map 등의 중간 처리

3. Terminal Operation (종결 처리)

Collect, reduce 등

- 여러가지 중간 처리를 이어 붙이는 것이 가능

### Sorted 데이터 정렬

- 데이터가 순서대로 정렬된 stream을 리턴
- 데이터의 종류에 따라 Comparator가 필요할 수 있음

```java
Stream<T> sorted();

Stream<T> sorted(Comparator<? super T> comparator);
```

### Distince 중복 제거

- 중복되는 데이터가 제거된 stream을 리턴

```java
Stram<T> distinct();
```

### FlatMap

- 스트림의 스트림을 납작하게 만듬
- Map + Flatten
- 데이터에 함수를 적용한 후 중첩된 stream을 연결하여 하나의 stream으로 리턴

```java
<R> Stream<R> flatMap(
        Function<? super T, 
        ? extends Stream<? extends R>> mapper);
```

## Optional

### NPE - NullPointerException

- Null 상태인 오브젝트를 레퍼런스할 때 발생
- Runtime error이기 때문에 실행 전 까지는 발생 여부를 알기 쉽지 않음

### Optional

- Null일수도, 아닐 수도 있는 오브젝트를 담은 상자
```java
java.util.Optional<T>

Optional<String> maybeString = Optional.of("Hello world");
String string = maybeString.get();
```
#### 만드는 법

```java
public static <T> Optional<T> of(T value)
public static <T> Optional<T> empty()
public static <T> Optional<T> ofNullable(T value)
```

- of : Null이 아닌 오브젝트를 이용해 Optional을 만들 때
- Empty : 빈 Optional을 만들 때
- ofNullable : Null인지 아닌지 알 지 못하는 오브젝트로 Optional을 만들 때

#### 안에 있는 값을 확인하고 꺼내는 방법

```java
public boolean isPresent()
public T get()
public T orElse(T other)
public T orElseGet(Supplier<? extends T> supplier)
public <X extends Throwable> T orElseThrow(
        Supplier<? extends X> exceptionSupplier) throws X
```

- isPresent : 안의 오브젝트가 null인지 아닌지 체크 Null이 아닐 시 true
- get : Optional 안의 값을 추출. Null이라면 에러
- orElse : Optional이 null이 아니라면 Optional 안의 값을, null이라면 other로 공급된 값을 리턴
- orElseGet : Optional이 null이 아니라면 Optional 안의 값을, null이라면 supplier로 공급되는 값을 리턴
- orElseThrow : Optional이 null이 아니라면 Optional 안의 값을, null이라면 exceptionSupplier로 공급되는 exception을 던짐


#### Optional 응용

```java
public void ifPresent(Consumer<? super T> action)
public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
public <U> Optional<U> flatMap(
        Function<? super t, ? extends Optional<? extends U>> mapper)
```

- ifPresent : Optional이 null이 아니라면 action을 실행
- map : Optional이 null이 아니라면 mapper를 정용
- flatMap - mapper의 리턴 값이 또 다른 Optional이라면 한 단계의 Optional이 되도록 납작하게 해줌



## Advanced Stream

### Max / Min / Count

```java
Optional<T> max(Comparator(? super T) comparator);
Optional<T> min(Comparator(? super T) comparator);
long count();
```

- max : Stream 안의 데이터 중 최대값을 반환. Stream이 비어있다면 빈 Optional을 반환
- min : Stream 안의 데이터 중 최소값을 반환. Stream이 비어있다면 빈 Optional을 반환
- count : Stream 안의 데이터의 개수를 반환

### All Match / Any Match

```java
boolean allMatch(Predicate<? Super T> predicate);
boolean anyMatch(Predicate<? Super T> predicate);
```

- allMatch : Stream 안의 모든 데이터가 predicate을 만족하면 true
- anyMatch : Stream 안의 데이터 중 하나라도 predicate을 만족하면 true


### Find First Find Any

```java
Optional<T> findFirst();
Optional<T> findAny();
```

- findFirst : Stream 안의 첫번째 데이터를 반환 Stream이 비어있다면 비어있는 Optional을 반환
- findFirst : Stream 안의 아무 데이터나 리턴. 순서가 중요하지 않고 Parallel Stream을 사용할 때 
최적화를 할 수 있다. 마찬 가지로 Stream이 비어있다면 빈 Optional을 반환


### Reduce

- 주어진 함수를 반복 적용해 Stream 안의 데이터를 하나의 값으로 합치는 작업
- Max / Min / Count 도 사실 reduce의 일종

```java
Optional<T> reduce(BinaryOperator<T> accumulator);
T reduce(T identity, BinaryOperator<T> accumulator);
<U> U reduce(U identity,
            BiFunction<U, ? super T, U> accumulator,
            BinaryOperator<U> combiner);
```

- reduce1 : 주어진 accumulator를 이용해 데이터를 합침. Stream이 비어있을 경우 빈 Optional을 반환 
- reduce2 : 주어진 초기값과 accumulator를 이용. 초기값이 있기 때문에 항상 반환값이 존재
- reduce3 : 합치는 과정에서 타입이 바뀔 경우 사용. Map + reduce로 대체 가능


### collect (Collectors)

```java
<R, A> R collect(Collector<? super T, A, R> collector);

java.util.stream.Collectors
Collector<T, ?, List<T>> toList();
Collector<T, ?, Set<T>> toSet();
```

- collect – 주어진 collector를 이용해 Stream안의 데이터를 합침.
- 일반적으로 특정 data structure로 데이터를 모을 때 사용.
- Collectors – 자주 쓰일법한 유용한 collector들을 모아놓은 util class.
- java.util.stream 패키지에서 제공.

### toList / toSet (Collectors)

```java
<R, A> R collect(Collector<? super T, A, R> collector);

java.util.stream.Collectors
Collecotr<T, ?, List<T>> toList();
Collecotr<T, ?, Set<T>> toSet();
```

### mapping / reducing (Collectors)

```java
public static <T, U, A, R> Collector<T, ?, R> mapping(
        Function<? super T, ? extends U> mapper,
        Collector<? super U, A, R> downstream)
        )
public static <T> Collector<T, ?, T> reducing(
        T identity, BinaryOperator<T> op)
        )
```
- mapping : Map과 collect를 합쳐놓은 역할을 해주는 collector.
  - 일반적으로 map을 한 후 collect를 해도 되지만 groupingBy 등 필요할 때가 있다.
- reducing : reduce를 해주는 collecor.
- 이외에도 filtering, flatMapping, counting, minBy, maxBy 등도 있다.

### ToMap

```java
public static <T, K, U> Collector<T, ?, Map<K,U>> toMap(
        Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends U> valueMapper
        )
```

- Stream 안의 데이터를 map의 형태로 변환해주는 collector
- keyMapper : 데이터를 map의 key로 변환해주는 Function
- valueMapper : 데이터를 map의 value로 변환하는 Function

### Grouping By

```java
public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(
        Function<? super T, ? extends T> classifier
        )
```

- Stream 안의 데이터에 classifier를 적용했을 때 결과값이 같은 값끼리 List로 모아서 Map의 형태로
반환해주는 collecotr.
  - 이 때 key는 classifier의 결과값, value는 그 결과값을 갖는 데이터들
- 예를 들어 stream에 {1, 2, 5, 7, 9, 12, 13}이 있을 때 classifier가
x -> x % 3 이라면 반환되는 map은 {0 = [9, 12], 1 = [1, 7, 13], 2= [2, 5]}
  
```java
public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(
        Function<? super T, ? extends T> classifer,
        Collector<? super T, A, D> dounstream)
        )
```

- 두번째 매개변수로 downstream collector를 넘기는 것도 가능
- 그 경우 List대신 collecotr를 적용시킨 값으로 map의 value가 만들어짐
- 이 때 자주 쓰이는 것이 mapping / reducing 등의 collector

### Partitioning By

```java
public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(
        Predicate<? super T> predicate
        )
public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy(
        Predicate<? super T> predicate,
        Collector<? super T, A, D> downstream
        )
```

- GroupingBy와 유사하지만 Function 대신 Predicate을 받아
true와 false 두 key가 존재하는 amp을 반환하는 Collector
- 마찬가지로 downstream collector를 넘겨 List이외의 형태로 map의 value를 만드는 것 역시 가능

### For Each

```java
void forEach(Consumer<? super T> action);
```

- 제공된 action을 Stream의 각 데이터에 적용해주는 종결 처리 메서드
- java의 iterable 인터페이스에도 forEach가 있기 때문에 Stream의 중간 처리가 필요없다면
iterable collection(Set, List 등)에서 바로 쓰는 것도 가능


### Parallel Stream (Strema을 병렬로)

```java
List<Integer> numbers = Arrays.asList(1, 2, 3);
Stream<Integer> parallelStream = numbers.parallelStream();
Stream<Integer> parallelStream2 = numbers.stream().parallel();
```

- Sequential vs Parallel
- 여러개의 스레드를 이용하여 stream의 처리 과정을 병렬화 (parallelize)
- 중간 과정은 병렬 처리 되지만 순서가 있는 Stream의 경우 종결 처리 했을 때의 결과물이 기존의 순차적인 처리와
일치하도록 종결 처리 과정에서 조정된다.
  - 즉 List로 collect한다면 순서가 항상 올바르게 나온다는 것


#### 명확한 장단점

- 장점
  - 굉장히 간단하게 병령처리를 사용할 수 있게 해준다.
  - 속도가 비약적으로 빨라질 수 있다.
- 단점
  - 항상 속도가 빨라지는 것은 아니다
  - 공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나 아예 오류가 날 수도 있다 (deadlock)
  - 이를 막기 위해 mutex, semaphore등 병렬 처리 기술을 이용하면 순차 처리보다 느려질 수도 있다


### 정리

- Stream의 다양한 종결 처리들
  - max / min / count / allMatch / anyMatch / findFirst / findAny / reduce / forEach
- Collector를 이용한 종결 처리들
  - toList / toSet / mapping / reducing / toMap / groupingBy / partitioningBy
- Parallel Stream의 장단점




## 함수형 프로그래밍의 응용

### Scope Closure Curry

```java
public static Supplier<String> getStringSupplier() {
    String hello = "Hello";
    Supplier<String> supllier = () -> {
        String world = "world";
        return hello + world;
    };
    return supplier;
}
```

#### Scope

- Scope (스코프/유효범위) : 변수에 접근할 수 있는 범위
  - 함수안에 함수가 있을 때 내부 함수에서 외부 함수에 있는 변수에 접근이 가능하다(lexical scope). 그 반대는 불가능하다.

#### Closure

- Closure : 내부 함수가 존재하는 한 내부 함수가 사용한 외부 함수의 변수들 역시 계속 존재한다. 이렇게
lexical scope를 포함하는 함수를 closure라 한다.
- 이 때 내부 함수가 사용한 외부 함수의 변수들은 내부 함수 선언 당시로부터 변할 수 없기 때문에 final로 선언되지
않더라도 암묵적으로 final로 취급된다.


#### Curry

- 여러 개의 매개변수를 받는 함수를 중첩된 여러 개의 함수로 쪼개어 매개 변수를 한 번에 받지 않고 여러 단계에 걸쳐
나눠 받을 수 있게 하는 기술
- Closure의 응용
```java
BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
=>
Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
```

### Lazy Evaluation

- Lambda의 계산은 그 결과값이 필요할 때가 되어서야 계산된다.
- 이를 이용하여 불필요한 계산을 줄이거나 해당 코드의 실행 순서를 의도적으로 미룰 수 있다.



### Function Composition (함수 합성)

- 여러 개의 함수를 합쳐 하나의 새로운 함수로 만드는 것

```java
java.util.function.Function

<V> Function<V, R> compose(Function<? super V, ? extends T> before)
<V> Function<T, V> andThen(Function<? super R, ? extends V> after)
```





























