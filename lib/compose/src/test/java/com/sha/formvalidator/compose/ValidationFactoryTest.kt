package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.MandatoryValidator
import com.sha.formvalidator.core.validator.Validator
import org.junit.Before
import org.junit.Test

class MandatoryValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    class FakeValidation: AbsValidationModel<String>() {
        var isRecomposeInvoked = false
        override var recompose: () -> Unit = { isRecomposeInvoked = true }
        override val validator: Validator<String> by lazy { MandatoryValidator<String>() }
        override var errorMessage: String = "x"
    }

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.mandatory(formValidation) {  }
        model.value = "x"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.mandatory(formValidation) {  }
        model.value = ""
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `test showError`() {
        val model = FakeValidation()
        model.showError("error")
        assert(!model.isValid)
        // should be true, and be false after calling createErrorText()
        assert(model.overrideValidateOnChangeOnce)
        // will be empty after calling createErrorText()
        assert(model.tmpError == "error")
        // must be called to recompose the view
        assert(model.isRecomposeInvoked)

        model.value = "x"

        model.createErrorText()

        // valid as we assigned value x
        assert(model.isValid)
        assert(!model.overrideValidateOnChangeOnce)
        assert(model.tmpError == "")
    }
}

class OptionalValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid if true and added to compositeValidation`() {
        val model = Validation.optional(formValidation) {  }
        model.value = "x"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be valid if false and added to compositeValidation`() {
        val model = Validation.optional(formValidation) {  }
        model.value = ""
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class PersonNameValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.personName(formValidation) {  }
        model.value = "Shaban"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.personName(formValidation) {  }
        model.value = "sha21"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class PersonFullNameValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.personFullName(formValidation) {  }
        model.value = "Shaban Kamel"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.personFullName(formValidation) {  }
        model.value = "dd33"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class DomainValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.domain(formValidation) {  }
        model.value = "google.com"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.domain(formValidation) {  }
        model.value = "x"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class AlphaValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.alpha(formValidation) {  }
        model.value = "xyz"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.alpha(formValidation) {  }
        model.value = "11_-"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class AlphaNumericValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.alphaNumeric(formValidation) {  }
        model.value = "x1"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.alphaNumeric(formValidation) {  }
        model.value = "x&"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class ValueMatchValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model1 = Validation.mandatory().apply { value = "x" }
        val model2 = Validation.mandatory().apply { value = "x" }
        val model = Validation.valueMatch(listOf(model1, model2), "error")
        model.value = "x"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model1 = Validation.mandatory().apply { value = "x" }
        val model2 = Validation.mandatory().apply { value = "y" }
        val model = Validation.valueMatch(listOf(model1, model2), "error")
        model.value = "x"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class NumericValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.numeric(formValidation) {  }
        model.value = "23"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.numeric(formValidation) {  }
        model.value = "x"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class CreditCardValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.creditCard(formValidation) {  }
        model.value = "378734493671000"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.creditCard(formValidation) {  }
        model.value = "123"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}


//class OrValidationTest {
//    private lateinit var compositeValidation: CompositeValidation<Validatable>
//
//    @Before
//    fun setup() {
//        compositeValidation = CompositeValidation.create {}
//    }
//
//    @Test
//    fun `should be valid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = "x"
//        assert(model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//
//    @Test
//    fun `should be invalid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = ""
//        assert(!model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//}

//class AndValidationTest {
//    private lateinit var compositeValidation: CompositeValidation<Validatable>
//
//    @Before
//    fun setup() {
//        compositeValidation = CompositeValidation.create {}
//    }
//
//    @Test
//    fun `should be valid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = "x"
//        assert(model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//
//    @Test
//    fun `should be invalid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = ""
//        assert(!model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//}

class PrefixValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.prefix("x", formValidation) {  }
        model.value = "x"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.prefix("x", formValidation) {  }
        model.value = "y"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class SuffixValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.suffix("x", formValidation) {  }
        model.value = "ex"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.suffix("x", formValidation) {  }
        model.value = "yy"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class LongTextRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.longRange(1, 5, formValidation) {  }
        model.value = 2
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.longRange(1, 5, formValidation) {  }
        model.value = 7
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class LengthRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.lengthRange(1, 5, formValidation) {  }
        model.value = "xyz"
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.lengthRange(1, 5, formValidation) {  }
        model.value = "xyxxyxxyx"
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class FloatRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.floatRange(1f, 5f, formValidation) {  }
        model.value = 1f
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.floatRange(1f, 5f, formValidation) {  }
        model.value = 7f
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class DoubleRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.doubleRange(1.0, 5.0, formValidation) {  }
        model.value = 2.0
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.doubleRange(1.0, 5.0, formValidation) {  }
        model.value = 7.0
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class LongRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.longRange(1, 5, formValidation) {  }
        model.value = 1
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.longRange(1, 5, formValidation) {  }
        model.value = 7
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class IntRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.intRange(1, 5, formValidation) {  }
        model.value = 2
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.intRange(1, 5, formValidation) {  }
        model.value = 8
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class CharRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.charRange('a', 'c', formValidation) {  }
        model.value = 'b'
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.charRange('a', 'c', formValidation) {  }
        model.value = 'x'
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class ShortRangeValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.shortRange(1, 5, formValidation) {  }
        model.value = 1
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.shortRange(1, 5, formValidation) {  }
        model.value = 8
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class ByteValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.byteRange(1, 5, formValidation) {  }
        model.value = 1
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.byteRange(1, 5, formValidation) {  }
        model.value = 7
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}

class BooleanValidationTest {
    private lateinit var formValidation: FormValidation<Validatable>

    @Before
    fun setup() {
        formValidation = FormValidation.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = Validation.boolean(true, formValidation) {  }
        model.value = true
        assert(model.isValid)
        assert(!formValidation.isEmpty())
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = Validation.boolean(true, formValidation) {  }
        model.value = false
        assert(!model.isValid)
        assert(!formValidation.isEmpty())
    }
}