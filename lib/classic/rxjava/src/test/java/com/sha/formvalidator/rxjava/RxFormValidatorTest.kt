package com.sha.formvalidator.rxjava
import org.junit.Test

class RxFormValidatorTest {

    @Test
    fun isValid_allValid() {
        val validator = RxFormValidator(
                FakeValidValidator(),
                FakeValidValidator(),
                FakeValidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(true)
                .assertValueCount(1)
    }

    @Test
    fun isValid_someInValid() {
        val validator = RxFormValidator(
                FakeValidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeValidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

    @Test
    fun isValid_allInValid() {
        val validator = RxFormValidator(
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

    @Test
    fun isValid_empty() {
        val validator = RxFormValidator<com.sha.formvalidator.Validatable>()
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

}

class FakeValidValidator: com.sha.formvalidator.Validatable {
    override fun validate(): Boolean = true
}

class FakeInvalidValidator: com.sha.formvalidator.Validatable {
    override fun validate(): Boolean = false
}