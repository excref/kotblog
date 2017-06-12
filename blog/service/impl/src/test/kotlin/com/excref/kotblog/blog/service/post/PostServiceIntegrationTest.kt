package com.excref.kotblog.blog.service.post

import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*


/**
 * @author Rafayel Mirzoyan
 * @since 6/11/17 5:04 AM
 */
class PostServiceIntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var postService: PostService
    //endregion

    //region Test methods
    @Test
    fun testCreate() {
        //given
        val title = UUID.randomUUID().toString()
        val content = UUID.randomUUID().toString()
        val blog = helper.persistBlog()
        val categoryAndTagUuids = (1..2).map { helper.persistCategory() to helper.persistTag() }.toList()
        val categories = categoryAndTagUuids.map { it.first }.toList()
        val categoryUuids = categoryAndTagUuids.map { it.first.uuid }.toList()
        val tags = categoryAndTagUuids.map { it.second }.toList()
        val tagUuids = categoryAndTagUuids.map { it.second.uuid }.toList()
        // when
        val result = postService.create(title, content, blog.uuid, tagUuids, categoryUuids)
        // then
        assertThat(result).isNotNull().extracting("title", "content").containsOnly(title, content)
        assertThat(blog).isEqualTo(result.blog)
        assertThat(categories).isEqualTo(result.categories)
        assertThat(tags).isEqualTo(result.tags)
    }

    @Test
    fun testGetUuid() {
        // given
        val post = helper.persistPost()
        // when
        val result = postService.getByUuid(post.uuid)
        // then
        assertThat(result).isNotNull().isEqualTo(post)
    }
    //endregion
}