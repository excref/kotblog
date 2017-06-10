package com.excref.kotblog.blog.service.blog.impl

import com.excref.kotblog.blog.persistence.blog.BlogRepository
import com.excref.kotblog.blog.service.blog.BlogService
import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.blog.exception.BlogAlreadyExistsForNameException
import com.excref.kotblog.blog.service.blog.exception.BlogNotFoundForUuidException
import com.excref.kotblog.blog.service.user.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:21 PM
 */
@Service
class BlogServiceImpl : BlogService {

    //region Dependencies
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var blogRepository: BlogRepository
    //endregion

    //region Public methods
    override fun create(name: String, userUuid: String): Blog {
        logger.debug("Checking if blog already exists for name - $name")
        assertBlogNotExistsForName(name)
        logger.debug("Getting user with uuid - $userUuid")
        val user = userService.getByUuid(userUuid)
        logger.debug("Saving blog")
        return blogRepository.save(Blog(name, user))
    }

    override fun getByUuid(uuid: String): Blog {
        logger.debug("Getting blog with uuid - $uuid")
        val blog = blogRepository.findByUuid(uuid)
        assertBlogNotNullForUuid(blog, uuid)
        logger.debug("Successfully got blog with uuid - $uuid")
        return blog as Blog
    }

    override fun existsForName(name: String): Boolean {
        logger.debug("Checking if blog exists for name - $name")
        return blogRepository.findByName(name) != null
    }
    //endregion

    //region Utility methods
    private fun assertBlogNotNullForUuid(blog: Blog?, uuid: String) {
        if (blog == null) {
            logger.error("Can not find blog with uuid - $uuid")
            throw BlogNotFoundForUuidException(uuid, "Can not find blog with uuid - $uuid")
        }
    }

    private fun assertBlogNotExistsForName(name: String) {
        if (existsForName(name)) {
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