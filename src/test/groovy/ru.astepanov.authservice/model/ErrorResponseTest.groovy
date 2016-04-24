package ru.astepanov.authservice.model

import org.junit.Test

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
class ErrorResponseTest {

    @Test
    void "should create response with correct code from error message"() {
        def resp = ErrorResponse.create("ERROR_CODE|Error message")
        assert resp.code == "ERROR_CODE"
    }

    @Test
    void "should create response with correct message from error message"() {
        def resp = ErrorResponse.create("ERROR_CODE|Error message")
        assert resp.message == "Error message"
    }

    @Test
    void "should trim code from error message"() {
        def resp = ErrorResponse.create(" ERROR_CODE |Error message")
        assert resp.code == "ERROR_CODE"
    }

    @Test
    void "should trim message from error message"() {
        def resp = ErrorResponse.create("ERROR_CODE| Error message  ")
        assert resp.message == "Error message"
    }

    @Test
    void "should create response with code from error message without message"() {
        def resp = ErrorResponse.create("ERROR_CODE|")
        assert resp.code == "ERROR_CODE"
    }

    @Test
    void "should create response with message from error message without code"() {
        def resp = ErrorResponse.create("|Error message ")
        assert resp.message == "Error message"
    }

    @Test
    void "should create response from error message without code and message"() {
        def resp = ErrorResponse.create("|")
        assert resp.code == ""
        assert resp.message == ""
    }

    @Test
    void "should create response from empty error message without delimiter"() {
        def resp = ErrorResponse.create("")
        assert resp.code == ""
        assert resp.message == ""
    }

    @Test
    void "should create response from error message with few delimiters"() {
        def resp = ErrorResponse.create("CODE|Message|Message2")
        assert resp.code == "CODE"
        assert resp.message == "Message|Message2"
    }
}
