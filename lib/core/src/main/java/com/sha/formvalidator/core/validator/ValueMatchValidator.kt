package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A simple validator that validates the field only if the value is the same as another one.
 *
 */
class ValueMatchValidator(errorMessage: String = DefaultErrors.valueMatchError, vararg strings: String) : TextValidator(errorMessage) {
    private val tvs: List<String> = listOf(*strings)

    override fun isValid(value: String): Boolean = tvs.all { it == value }
}