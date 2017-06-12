package com.excref.kotblog.blog.service.post.impl

import com.excref.kotblog.blog.persistence.post.PostRepository
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.post.PostService
import com.excref.kotblog.blog.service.post.domain.Post
import com.excref.kotblog.blog.service.post.exception.PostNotExistsForUuidException
import com.excref.kotblog.blog.service.tag.domain.Tag
import com.excref.kotblog.blog.service.user.domain.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Android Studio.
 * User: Gurgen Arustamyan
 * Date: 6/8/17
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
class PostServiceImpl : PostService {

    //region Dependencies
    @Autowired
    private lateinit var postRepository: PostRepository
    //endregion

    //region Public methods
    @Transactional
    override fun create(title: String, content: String, categories: List<Category>, tags: List<Tag>, userId: User): Post {
        PostServiceImpl.logger.debug("Creating post with title - $title and content - $content")
        return postRepository.save(Post(title, content, categories, tags, userId))
    }

    @Transactional(readOnly = true)
    override fun getByUuid(uuid: String): Post? {
        logger.debug("Getting post with uuid - $uuid")
        val post = postRepository.findByUuid(uuid)
        assertPostNotNullForUuid(post, uuid)
        logger.debug("Successfully got category - $post")
        return post
    }

    //region Utility methods
    fun assertPostNotNullForUuid(post: Post?, uuid: String) {
        if (post == null) {
            PostServiceImpl.logger.error("Can not find post for uuid $uuid")
            throw PostNotExistsForUuidException(uuid, "Can not find post for uuid $uuid")
        }
    }
    //endregion

    //region Companion
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PostServiceImpl::class.java)
    }
    //endregion
}