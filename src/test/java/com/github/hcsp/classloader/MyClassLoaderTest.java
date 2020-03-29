package com.github.hcsp.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyClassLoaderTest {
    @Test
    public void test() throws Exception {
        File projectRoot = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        MyClassLoader myClassLoader = new MyClassLoader(projectRoot);

        Class testClass = myClassLoader.loadClass("com.github.hcsp.MyTestClass");
        Object testClassInstance = testClass.getConstructor().newInstance();
        String message = (String) testClass.getMethod("sayHello").invoke(testClassInstance);

        Assertions.assertEquals("Hello", message);
    }

    @Test
    public void testInstanceof() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File projectRoot = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        MyClassLoader myClassLoader = new MyClassLoader(projectRoot);

        Class testClass = myClassLoader.loadClass("com.github.hcsp.classloader.Hello");
        Object testClassInstance = testClass.getConstructor().newInstance();
        System.out.println(testClassInstance instanceof com.github.hcsp.classloader.Hello);
    }


    @Test
    public void classNotFoundTest() {
        Assertions.assertThrows(
                ClassNotFoundException.class,
                () -> {
                    File projectRoot =
                            new File(System.getProperty("basedir", System.getProperty("user.dir")));
                    MyClassLoader myClassLoader = new MyClassLoader(projectRoot);

                    myClassLoader.loadClass("com.github.hcsp.NoSuchClass");
                });
    }
}
