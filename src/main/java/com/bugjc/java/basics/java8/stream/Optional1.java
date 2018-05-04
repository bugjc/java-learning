package com.bugjc.java.basics.java8.stream;

import lombok.*;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 理解、学习与使用 JAVA 中的 OPTIONAL
 * https://www.cnblogs.com/zhangboyu/p/7580262.html
 * @author aoki
 */
@Log
public class Optional1 {

    /************************创建对象*********************************/
    /**emptyOpt 变量的值会导致 NoSuchElementException**/
    @Test
    public void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    /**对象不为空用of(),null值会抛出NullPointerException**/
    @Test
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        Optional<User> opt = Optional.of(new User("aoki",18));
    }

    /**如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法**/
    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);

        assertEquals("John", opt.get());
    }

    /**isPresent()是否有值**/
    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", 20);
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());

        assertEquals(user.getAge(), opt.get().getAge());


        opt.ifPresent( u -> assertEquals(user.getAge(), u.getAge()));
    }



    /************************返回默认值*********************************/

    /**Optional 类提供了 API 用以返回对象值，或者在对象为空的时候返回默认值。**/
    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", 22);
        //orElse()如果有值则返回该值，否则返回传递给它的参数值
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals(user2.getAge(), result.getAge());
    }

    /**如果对象的初始值不是 null，那么默认值会被忽略**/
    @Test
    public void whenValueNotNull_thenIgnoreDefault() {
        User user = new User("john@gmail.com",1);
        User user2 = new User("anna@gmail.com", 2);
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals("john@gmail.com", result.getAge());
    }

    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        log.info("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.info("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    public User createNewUser(){
        return new User("aoki",2222);
    }


    /************************返回异常*********************************/

    @Test
    public void whenThrowException_thenOk() {
        User result = Optional
                .ofNullable(createNewUser())
                //如果 user 值为 null，会抛出 IllegalArgumentException
                .orElseThrow( () -> new IllegalArgumentException());
    }



    /************************转换值*********************************/
    @Test
    public void whenMap_thenOk() {
        //map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中。
        // 这就使对返回值进行链试调用的操作成为可能 —— 这里的下一环就是 orElse()。
        User user = new User("anna@gmail.com", 22);
        int age = Optional.ofNullable(user)
                .map(u -> u.getAge()).orElse(21);

        assertEquals(age, user.getAge());
    }


    @Test
    public void whenFlatMap_thenOk() {
        User user = new User("anna@gmail.com", 21,"test");
        user.setPosition("Developer");
        String position = Optional.ofNullable(user)
                .flatMap(u -> u.getPosition()).orElse("default");

        assertEquals(position, user.getPosition().get());
    }


    /*************************过滤值********************************/
    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", 20);
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getUsername() != null && u.getUsername().contains("@"));

        //如果通过过滤器测试，result 对象会包含非空值。
        assertTrue(result.isPresent());
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @RequiredArgsConstructor
    @AllArgsConstructor
    class User{
        @NonNull
        private String username;
        @NonNull
        private int age;
        private String position;


        public Optional<String> getPosition() {
            return Optional.ofNullable(position);
        }
    }

}