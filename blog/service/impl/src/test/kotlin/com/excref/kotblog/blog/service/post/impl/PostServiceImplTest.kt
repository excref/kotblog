package com.excref.kotblog.blog.service.post.impl

import com.excref.kotblog.blog.persistence.post.PostRepository
import com.excref.kotblog.blog.service.blog.BlogService
import com.excref.kotblog.blog.service.category.CategoryService
import com.excref.kotblog.blog.service.post.PostService
import com.excref.kotblog.blog.service.post.domain.Post
import com.excref.kotblog.blog.service.post.exception.PostNotFoundForUuidException
import com.excref.kotblog.blog.service.tag.TagService
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.easymock.EasyMock.isA
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Test
import java.util.*

/**
 * @author Rafayel Mirzoyan
 * @since 6/11/17 3:39 PM
 */
class PostServiceImplTest : AbstractServiceImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val postService: PostService = PostServiceImpl()

    @Mock
    private lateinit var postRepository: PostRepository

    @Mock
    private lateinit var blogService: BlogService

    @Mock
    private lateinit var tagService: TagService

    @Mock
    private lateinit var categoryService: CategoryService
    //endregion

    //region initial
    @Test
    fun testPostService() {
        assertThat(postService).isNotNull()
    }

    @Test
    fun testPostRepository() {
        assertThat(postRepository).isNotNull()
    }
    //endregion

    //region getByUuid
    /**
     *  when when post does not exists
     */
    @Test
    fun testGetByUuid() {
        resetAll()
        // test data
        val uuid = UUID.randomUUID().toString()
        // expectations
        expect(postRepository.findByUuid(uuid)).andReturn(null)
        replayAll()
        // test scenario
        try {
            postService.getByUuid(uuid)
        } catch (ex: PostNotFoundForUuidException) {
            assertThat(ex).isNotNull().extracting("uuid").containsOnly(uuid)
        }
        verifyAll()
    }

    @Test
    fun getByUuid2() {
        resetAll()
        // test data
        val post = helper.buildPost()
        val uuid = post.uuid
        // expectations
        expect(postRepository.findByUuid(uuid)).andReturn(post)
        replayAll()
        // test scenario
        val result = postService.getByUuid(uuid)
        assertThat(result).isNotNull().isEqualTo(post)
        verifyAll()
    }
    //endregion

    //region create
    @Test
    fun create() {
        resetAll()
        // test data
        val post: Post = helper.buildPost()
        val tagUuids = post.tags.map { it -> it.uuid }.toList()
        val categoryUuids = post.categories.map { it -> it.uuid }.toList()
        //expectations
        expect(blogService.getByUuid(post.blog.uuid)).andReturn(helper.buildBlog())
        expect(tagService.getByUuids(tagUuids)).andReturn(listOf(helper.buildTag()))
        expect(categoryService.getByUuids(categoryUuids)).andReturn(listOf(helper.buildCategory()))
        expect(postRepository.save(isA(Post::class.java))).andReturn(post)
        replayAll()
        // test scenario
        val result = postService.create(post.title, post.content, post.blog.uuid, tagUuids, categoryUuids)
        assertThat(result).isNotNull().isEqualTo(post)
        verifyAll()
    }
    //endregion

}
