package ru.gb;

public class Tester {

    @BeforeSuite
    public void testBeforeSuite(){
        System.out.println("Тест BeforeSuite");
    }

    @Test(priority = 1)
    public void test1(){
        System.out.println("Тест 1");
    }

    @Test(priority = 2)
    public void test2(){
        System.out.println("Тест 2");
    }

    @Test(priority = 3)
    public void test3(){
        System.out.println("Тест 3");
    }

    @Test(priority = 4)
    public void test4(){
        System.out.println("Тест 4");
    }

    @Test(priority = 5)
    public void test5(){
        System.out.println("Тест 5");
    }

    @Test(priority = 6)
    public static void test6_1(){
        System.out.println("Тест 6.1");
    }

    @Test(priority = 6)
    public void test6_3(){
        System.out.println("Тест 6.3");
    }

    @Test(priority = 6)
    public void test6_2(){
        System.out.println("Тест 6.2");
    }

    @Test(priority = 7)
    public void test7(){
        System.out.println("Тест 7");
    }

    @Test(priority = 8)
    public void test8(){
        System.out.println("Тест 8");
    }

    @Test(priority = 9)
    public void test9(){
        System.out.println("Тест 9");
    }

    @AfterSuite
    public void testAfterSuite(){
        System.out.println("Тест AfterSuite");
    }

}
