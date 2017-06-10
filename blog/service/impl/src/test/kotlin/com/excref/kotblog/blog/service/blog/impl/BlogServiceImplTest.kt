package com.excref.kotblog.blog.service.blog.impl

import com.excref.kotblog.blog.persistence.blog.BlogRepository
import com.excref.kotblog.blog.service.blog.BlogService
import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.blog.exception.BlogAlreadyExistsForNameException
import com.excref.kotblog.blog.service.blog.exception.BlogNotFoundForUuidException
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import com.excref.kotblog.blog.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Assert.fail
import org.junit.Test
import java.util.*

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:21 PM
 */
class BlogServiceImplTest : AbstractServiceImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val blogService: BlogService = BlogServiceImpl()

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var blogRepository: BlogRepository
    //endregion

    //region Test methods

    //region Initial
    @Test
    fun testBlogService() {
        assertThat(blogService).isNotNull()
    }

    @Test
    fun testBlogRepository() {
        assertThat(blogRepository).isNotNull()
    }
    //endregion

    //region create
    /**
     * When blog with name already exists
     */
    @Test
    fun testCreate1() {
        resetAll()
        // test dara
        val name = "biacode"
        val user = helper.buildUser()
        val userUuid = user.uuid
        val blog = Blog(name, user)
        // expectations
        expect(blogRepository.findByName(name)).andReturn(blog)
        replayAll()
        // test scenario
        try {
            blogService.create(name, userUuid)
            fail()
        } catch(ex: BlogAlreadyExistsForNameException) {
            assertThat(ex).isNotNull().extracting("name").containsOnly(name)
        }
        verifyAll()
    }

    @Test
    fun testCreate2() {
        resetAll()
        // test dara
        val name = "biacode"
        val user = helper.buildUser()
        val userUuid = user.uuid
        // expectations
        expect(blogRepository.findByName(name)).andReturn(null)
        expect(userService.getByUuid(userUuid)).andReturn(user)
        expect(blogRepository.save(isA(Blog::class.java))).andAnswer({ getCurrentArguments()[0] as Blog })
        replayAll()
        // test scenario
        assertThat(blogService.create(name, userUuid)).isNotNull().extracting("name", "user").containsOnly(name, user)
        verifyAll()
    }
    //endregion

    //region getByUuid
    /**
     * When not found
     */
    @Test
    fun testGetByUuid1() {
        resetAll()
        // test data
        val uuid = UUID.randomUUID().toString()
        // expectations
        expect(blogRepository.findByUuid(uuid)).andReturn(null)
        replayAll()
        // test scenario
        try {
            blogService.getByUuid(uuid)
            fail()
        } catch(ex: BlogNotFoundForUuidException) {
            assertThat(ex).isNotNull().extracting("uuid").containsOnly(uuid)
        }
        verifyAll()
    }

    @Test
    fun testGetByUuid2() {
        resetAll()
        // test data
        val blog = Blog("biacode", helper.buildUser())
        val uuid = blog.uuid
        // expectations
        expect(blogRepository.findByUuid(uuid)).andReturn(blog)
        replayAll()
        // test scenario
        assertThat(blogService.getByUuid(uuid)).isNotNull().isEqualTo(blog)
        verifyAll()
    }
    //endregion

    //region existsForName
    /**
     * When blog already exists for name
     */
    @Test
    fun testExistsForName1() {
        resetAll()
        // test data
        val name = "biacode"
        // expectations
        expect(blogRepository.findByName(name)).andReturn(null)
        replayAll()
        // test scenario
        assertThat(blogService.existsForName(name)).isFalse()
        verifyAll()
    }

    @Test
    fun testExistsForName2() {
        resetAll()
        // test data
        val name = "biacode"
        // expectations
        val blog = Blog(name, helper.buildUser())
        expect(blogRepository.findByName(name)).andReturn(blog)
        replayAll()
        // test scenario
        assertThat(blogService.existsForName(name)).isTrue()
        verifyAll()
    }
    //endregion

    //endregion
}