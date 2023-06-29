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































