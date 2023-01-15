package ru.otus;

public class TestFramework {
    @Before
    public void before() {
        System.out.print("Метод: before. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

    @Test
    void test() {
        System.out.println("Метод: test ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

    @After
    public void after() {
        System.out.println("Метод: after ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }
}
