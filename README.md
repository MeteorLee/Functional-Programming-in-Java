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