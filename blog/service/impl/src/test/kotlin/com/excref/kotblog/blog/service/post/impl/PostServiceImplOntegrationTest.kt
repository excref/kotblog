package com.excref.kotblog.blog.service.post.impl

import com.excref.kotblog.blog.service.post.PostService
import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Rafayel Mirzoyan
 * @since 6/11/17 5:04 AM
 */
class PostServiceImplOntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var postService: PostService
    //endregion

    //region Test methods
    @Test
    fun createAndGetyUuid() {
        // given
        // when
        val post = helper.persistPost()
        // then
        Assertions.assertThat(post).isNotNull()
        Assertions.assertThat(postService.getByUuid(post.uuid)).isNotNull().isEqualTo(post)
    }

}