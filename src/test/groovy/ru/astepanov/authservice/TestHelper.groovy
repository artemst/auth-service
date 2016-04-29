package ru.astepanov.authservice

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (29.04.16)
 */
class TestHelper {

     def static <T> T findFieldAnnotation(Class clazz, String fieldName, Class<T> annotClass) {
        return clazz.declaredFields
            .find { it.name == fieldName }
            .collect {
                it.annotations.find { it.annotationType() == annotClass }
            } [0] as T
    }
}
