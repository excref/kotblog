package com.excref.kotblog.blog.service.post.impl

import com.excref.kotblog.blog.persistence.post.PostRepository
import com.excref.kotblog.blog.service.blog.BlogService
import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.category.CategoryService
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.post.PostService
import com.excref.kotblog.blog.service.post.domain.Post
import com.excref.kotblog.blog.service.post.exception.PostNotFoundForUuidException
import com.excref.kotblog.blog.service.tag.TagService
import com.excref.kotblog.blog.service.tag.domain.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Rafayel Mirzoyan
 * @since 6/10/17 7:58 PM
 */
@Service
class PostServiceImpl : PostService {

    //region Dependencies
    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var blogService: BlogService

    @Autowired
    private lateinit var tagService: TagService

    @Autowired
    private lateinit var categoryService: CategoryService
    //endregion

    //region Public methods
    @Transactional(readOnly = true)
    override fun getByUuid(uuid: String): Post {
        logger.debug("Getting post with uuid - $uuid")
        val post = postRepository.findByUuid(uuid)
        assertPostNotNullForUuid(post, uuid)
        logger.debug("Successfully got post - $post")
        return post as Post
    }

    @Transactional
    override fun create(title: String,
                        content: String,
                        blogUuid: String,
                        tagUuids: List<String>,
                        categoryUuids: List<String>
    ): Post {
        logger.debug("Creating post with title - $title")
        val blog: Blog = blogService.getByUuid(blogUuid)
        logger.debug("Got blog - $blog for uuid $blogUuid")
        val tags: List<Tag> = tagService.getByUuids(tagUuids)
        logger.debug("Got tags - $tags for uuids - $tagUuids")
        val categories: List<Category> = categoryService.getByUuids(categoryUuids)
        logger.debug("Got categories - $categories for uuids - $categoryUuids")
        return postRepository.save(Post(title, content, blog, tags, categories))
    }
    //endregion

    //region Utility methods
    fun assertPostNotNullForUuid(post: Post?, uuid: String) {
        if (post == null) {
            logger.error("Can not find post for uuid $uuid")
            throw PostNotFoundForUuidException(uuid, "Can not find post for uuid $uuid")
        }
    }
    //endregion

    //region Companion
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PostServiceImpl::class.java)
    }
    //endregion
}