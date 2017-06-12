package com.excref.kotblog.blog.service.post.impl

import com.excref.kotblog.blog.persistence.post.PostRepository
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.post.PostService
import com.excref.kotblog.blog.service.post.domain.Post
import com.excref.kotblog.blog.service.post.exception.PostNotExistsForUuidException
import com.excref.kotblog.blog.service.tag.domain.Tag
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import com.excref.kotblog.blog.service.user.domain.User
import com.excref.kotblog.blog.service.user.domain.UserRole
import org.assertj.core.api.Assertions
import org.easymock.EasyMock
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Test
import java.util.*

/**
 * Created by Android Studio.
 * User: Gurgen Arustamyan
 * Date: 6/9/17
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
class PostServiceImplTest : AbstractServiceImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val postService: PostService = PostServiceImpl()

    @Mock
    private lateinit var postRepository: PostRepository
    //endregion

    //region initial
    @Test
    fun testPostService() {
        Assertions.assertThat(postService).isNotNull()
    }

    @Test
    fun testPostRepository() {
        Assertions.assertThat(postRepository).isNotNull()
    }
    //endregion

    //region create


    @Test
    fun create() {
        resetAll()
        // test data
        val title = "Title"
        val content = "Content"
        val categories = arrayListOf(Category("Category"))
        val tags = arrayListOf(Tag("Tag"))
        val user = User("email", "password", UserRole.SYS_ADMIN)
        //expectations
        EasyMock.expect(postRepository.save(EasyMock.isA(Post::class.java))).andAnswer({ EasyMock.getCurrentArguments()[0] as Post? })
        replayAll()
        // test scenario
        val post = postService.create(title, content, categories, tags, user)
        Assertions.assertThat(post).isNotNull()
                .extracting("title", "content", "user")
                .containsOnly(title, content, user)
        verifyAll()
    }
    //endregion

    //region getByUuid
    /**
     * When does not exists for uuid
     */
    @Test
    fun getByUuid1() {
        resetAll()
        // test data
        val uuid = UUID.randomUUID().toString()
        // expectations
        EasyMock.expect(postRepository.findByUuid(uuid)).andReturn(null)
        replayAll()
        // test scenario
        try {
            postService.getByUuid(uuid)
        } catch (ex: PostNotExistsForUuidException) {
            Assertions.assertThat(ex).isNotNull().extracting("uuid").containsOnly(uuid)
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
        EasyMock.expect(postRepository.findByUuid(uuid)).andReturn(post)
        replayAll()
        // test scenario
        val result = postService.getByUuid(uuid)
        Assertions.assertThat(result).isNotNull().isEqualTo(post)
        verifyAll()
    }
    //endregion
}