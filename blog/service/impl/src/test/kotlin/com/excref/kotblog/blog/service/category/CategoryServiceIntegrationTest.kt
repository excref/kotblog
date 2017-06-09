package com.excref.kotblog.blog.service.category

import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:42
 */
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
        val user = helper.persistUser()
        // when
        val category = categoryService.create(name, user.uuid)
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
        val user = helper.persistUser()
        val category = helper.persistCategory(user = user)
        // when
        val result = categoryService.existsForNameAndUser(category.name, user.uuid)
        // then
        assertThat(result).isTrue()
    }
    //endregion

}