package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.QueryValidator
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class QueryValidatorTest {
    @Test
    fun queryValidator_CorrectQuerySimple_ReturnsTrue() {
        assertTrue(QueryValidator.validate(VALID_QUERY))
    }

    // TODO: Решение задания 1 - пример негативного тестирования
    @Test
    fun queryValidator_InvalidQueryEmpty_ReturnsFalse() {
        assertFalse(QueryValidator.validate(EMPTY_QUERY))
    }

    // TODO: Решение задания 1 - пример негативного тестирования
    @Test
    fun queryValidator_InvalidQueryManyWords_ReturnsFalse() {
        assertFalse(QueryValidator.validate(MORE_THEN_ONE_WORD_QUERY))
    }
}