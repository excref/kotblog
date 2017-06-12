package com.excref.kotblog.blog.service.post

import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import com.excref.kotblog.blog.service.user.domain.UserRole
import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Component

/**
 * Created by Android Studio.
 * User: Gurgen Arustamyan
 * Date: 6/8/17
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
@EnableAutoConfiguration
@Component
class PostServiceIntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var postService: PostService
    //endregion

    //region Test methods
    @Test
    fun create() {
        // given
        val title = "Title"
        val content = "Content"
        val categories = arrayListOf(helper.persistCategory("Category"))
        val tags = arrayListOf(helper.persistTag("Tag"))
        val user = helper.persistUser("email", "password", UserRole.SYS_ADMIN)
        // when
        val post = postService.create(title, content, categories, tags, user)
        post.categories
        post.tags
        // then
        Assertions.assertThat(post)
                .isNotNull()
                .extracting("title", "content", "user")
                .containsOnly(title, content, user)
        Assertions.assertThat(postService.getByUuid(post.uuid)).isNotNull().isEqualTo(post)
    }

    @Test
    fun getByUuid() {
        // given
        val post = helper.persistPost()
        // when
        val result = postService.getByUuid(post.uuid)
        // then
        Assertions.assertThat(result).isNotNull().isEqualTo(post)
    }
    //endregion
}