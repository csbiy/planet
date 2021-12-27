---
title: JPA composite key mapping 방식 - @EmbeddedId,@IdClass
---

## 복합키 매핑 방법

JPA는 복합키 매핑을 다음과 같은 2가지 annotation으로 지원해준다. 

1.@IdClass
2.@Embeddable

<!-- more -->

## 복합키를 매핑하는 class의 전제조건 

1. composite primary key class 는 public 이여야만 한다.
2. no-arg constructor 가 존재해야만 한다. 
3. equals,hashCode method를 overriding해야만 한다.
4. Serilizable interface 를  구현해야 한다. 



## @IdClass

@IdClass 방식은 class의 필드를 entity에도 중복 보관한다. 

```java
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@IdClass(UserId.class)
public class User {

    @Id
    private String userId;

    @Id
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
```
@AllArgsConstructor annotation이 entity에 추가된 이유는 lombok 빌더 떄문이다.(@Builder)
빌더 패턴은 모든 멤버변수를 생성자로 받을 수 있어야 한다, 자세한 내용은 이펙티브 자바 , builder pattern 내부 구현을 보면 알수 있는데 간략하게 정리하면 아래와 같이 빌더내 멤버변수를 생성하고자 하는 객체에 넘겨줄떄 생성자가 필요하기 떄문이다. 또는 빌더 자체를 넘겨주어서 빌더로부터 필드를 가져오는 방식도 존재한다. 
```java
// lombok builder 구현방식
public build(){
    new User(builder.userId , builder.password ... );
}
```
```java
User{
    //builder 자체를 넘겨주는 방식 
    User(Builder builder){
        this.userId = builder.getUserId;
    }

    public static class Builder{

        private String userId;

        public Builder userId(){
            this.userId = userId;
            return this;
        }

        //...

        public build(){
            new User(builder);
        }
    }
}

```

UserId를 보면 복합키에 사용될 필드를 보관하고 있고, serializable interface를 구현하고 equals & hascode를 overriding했다. 

```java
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserId implements Serializable {

    private String userId;

    private String password;

}
```
repository 에서는 복합키 클래스를 Id Class로 명시해주면 된다.
```java
@Repository
public interface UserRepository extends JpaRepository<User, UserId> {}
```
이를 테스트해보면 정상작동여부를 확인할 수 있다. 
```java
@Test
    @DisplayName("@IdClass 정상작동여부 확인")
    public void user(){
        //given
        UserId userId = new UserId("tester","1234");
        User user = new User.UserBuilder()
                .userId(userId.getUserId())
                .password(userId.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);

        //when
        Optional<User> foundUser = userRepository.findById(userId);

        //then
        Assertions.assertThat(foundUser.isPresent());
        Assertions.assertThat(foundUser.get() ).isEqualTo(savedUser);
    }
```

## @EmbeddedClass

@EmbeddedClass 방식은 필드를 복합키 클래스에서만 보관하고, entity에는 복합키 클래스만을 보관한다. 

```java
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable //추가 
public class UserId implements Serializable {

    private String userId;

    private String password;

}

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @EmbeddedId //추가 
    private UserId userId;
}
```

이를 테스트해보면 마찬가지로 정상작동여부를 확인할수 있다.

```java
    @Test
    @DisplayName("@EmbeddedId 정상작동여부 확인")
    public void user(){
        //given
        UserId userId = new UserId("tester","1234");
        User user = new User.UserBuilder()
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);

        //when
        Optional<User> foundUser = userRepository.findById(userId);

        //then
        Assertions.assertThat(foundUser.isPresent());
        Assertions.assertThat(foundUser.get() ).isEqualTo(savedUser);
    }
```

## @IdClass와  @EmbeddedClass간의 차이점 

@EmbeddedClass에서는 entity class에 복합키 클래스만 명시해주는 반면, @IdClass는 복합키 클래스의 필드를 중복으로 다시 명시해주어야 한다
jpql query가 나갈떄 차이점도 존재한다. 
@IdClass 
```sql
SELECT user.userId,user.password from User user
```
이와달리 @EmbeddedClass에서는 추가적인 alias가 사용되어 jpql query가 나간다. 
```sql
SELECT user.userId.id , user.userId.password from User user
```
