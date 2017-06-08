package com.excref.kotblog.blog.service.category

import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:42
 */
@EnableAutoConfiguration
@EnableJpaRepositories
@Component
class CategoryServiceIntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var categoryService: CategoryService
    //endregion

    //region Test methods
    @Test
    fun create() {
        // given
        val name = "Music"
        // when
        val category = categoryService.create(name)
        // then
        assertThat(category).isNotNull().extracting("name").containsOnly(name)
        assertThat(categoryService.getByUuid(category.uuid)).isNotNull().isEqualTo(category)
    }

    @Test
    fun getByUuid() {
        // given
        val category = helper.persistCategory()
        // when
        val result = categoryService.getByUuid(category.uuid)
        // then
        assertThat(result).isNotNull().isEqualTo(category)
    }

    @Test
    fun existsForName() {
        // given
        val category = helper.persistCategory()
        // when
        val result = categoryService.existsForName(category.name)
        // then
        assertThat(result).isTrue()
    }
    //endregion

}