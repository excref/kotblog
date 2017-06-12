package com.excref.kotblog.blog.service.post

import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Rafayel Mirzoyan
 * @since 6/11/17 5:04 AM
 */
class PostServiceImplIntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var postService: PostService
    //endregion

    //region Test methods
    @Test
    fun createAndGetyUuid() {
        // when
        val post = helper.persistPost()
        // then
        assertThat(post).isNotNull()
        assertThat(postService.getByUuid(post.uuid)).isNotNull().isEqualTo(post)
    }
    //endregion
}