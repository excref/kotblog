package com.excref.kotblog.blog.service.blog

import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:21 PM
 */
class BlogServiceIntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var blogService: BlogService
    //endregion

    //region Test methods
    @Test
    fun testCreate() {
        // given
        val user = helper.persistUser()
        val name = "biacode"
        // when
        val result = blogService.create(name, user.uuid)
        // then
        assertThat(result).isNotNull().extracting("name", "user").containsOnly(name, user)
    }

    @Test
    fun testGetByUuid() {
        // given
        val blog = helper.persistBlog()
        // when
        val result = blogService.getByUuid(blog.uuid)
        // then
        assertThat(result).isNotNull().isEqualTo(blog)
    }

    @Test
    fun testExistsForName() {
        // given
        val name = "biacode"
        helper.persistBlog(name = name)
        // when
        val existsForName = blogService.existsForName(name)
        // then
        assertThat(existsForName).isTrue()
    }
    //endregion

}