package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class NumericValidator(errorMessage: String) : TextValidator(errorMessage) {

    override fun validate(): Boolean {
        return if(value.isEmpty()) false else isDigitsOnly(value)
    }

    /**
     * This function is copied from [android.text.TextUtils]
     * to be able to run unit test with mockito
     */
    private fun isDigitsOnly(str: CharSequence): Boolean {
        val len = str.length
        var cp: Int
        var i = 0
        while (i < len) {
            cp = Character.codePointAt(str, i)
            if (!Character.isDigit(cp)) {
                return false
            }
            i += Character.charCount(cp)
        }
        return true
    }
}


