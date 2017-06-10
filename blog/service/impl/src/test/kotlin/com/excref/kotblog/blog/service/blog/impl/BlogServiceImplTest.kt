package com.excref.kotblog.blog.service.blog.impl

import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:21 PM
 */
class BlogServiceImplTest : AbstractServiceImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val blogService: BlogService = BlogServiceImpl()

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

    //region existsForName
    /**
     * When blog already exists for name
     */
    @Test
    fun testExistsForName1() {
        // test data
        val name = "biacode"
        // expectations
        expect(blogRepository.findByName(name)).andReturn(null)
        // test scenario
        assertThat(blogService.existsForName(name)).isTrue()
    }

    @Test
    fun testExistsForName2() {
        // test data
        val name = "biacode"
        // expectations
        val blog = Blog(name, helper.buildUser())
        expect(blogRepository.findByName(name)).andReturn(blog)
        // test scenario
        assertThat(blogService.existsForName(name)).isTrue()
    }
    //endregion

    //endregion
}

interface BlogRepository {
    fun findByName(name: String): Blog?
}

@Service
class BlogServiceImpl : BlogService {

    //region Dependencies
    @Autowired
    private lateinit var blogRepository: BlogRepository
    //endregion

    //region Public methods
    override fun existsForName(name: String): Boolean {
        assertBlogNotExistsForName(name)
        TODO()
    }
    //endregion

    //region Utility methods
    private fun assertBlogNotExistsForName(name: String) {
        if (blogRepository.findByName(name) == null) {
            logger.error("The blog already exists for name $name")
            throw BlogAlreadyExistsForNameException(name, "The blog already exists for name $name")
        }
    }
    //endregion

    //region Companion objects
    companion object {
        private val logger = LoggerFactory.getLogger(BlogServiceImpl::class.java)
    }
    //endregion

}

data class BlogAlreadyExistsForNameException(val name: String, override val message: String) : RuntimeException(message)

interface BlogService {
    fun existsForName(name: String): Boolean
}
