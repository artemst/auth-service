package ru.astepanov.authservice.model

import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
class AuthProviderTest {

    @Test
    void "should return value in lowercase"() {
        assert AuthProvider.EMAIL.value() == "email"
    }

    @Test
    void "should create enum from value in lowercase"() {
        assert AuthProvider.fromValue("email") == AuthProvider.EMAIL
    }

    @Test
    void "should create enum from value ignoring its case"() {
        assert AuthProvider.fromValue("eMaIl") == AuthProvider.EMAIL
    }

    @Test
    void "should not create enum from unknown value"() {
        def e = shouldFail {
            AuthProvider.fromValue("unknown")
        }
        assert e.message == "Unknown value for AuthProvider: 'unknown'"
    }
}
