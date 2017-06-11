package com.excref.kotblog.blog.service.post.impl

import com.excref.kotblog.blog.persistence.post.PostRepository
import com.excref.kotblog.blog.service.blog.BlogService
import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.category.CategoryService
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.category.exception.PostNotExistsForUuidException
import com.excref.kotblog.blog.service.post.PostService
import com.excref.kotblog.blog.service.post.domain.Post
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
        PostServiceImpl.logger.debug("Getting post with uuid - $uuid")
        val post = postRepository.findByUuid(uuid)
        assertPostNotNullForUuid(post, uuid)
        PostServiceImpl.logger.debug("Successfully got post - $post")
        return post as Post
    }

    @Transactional
    override fun create(name: String,
                        title: String,
                        content: String,
                        blogUuid: String,
                        tagUuids: List<String>,
                        categoryUuids: List<String>
    ): Post {
        PostServiceImpl.logger.debug("Creating post $name")
        val blog: Blog = blogService.getByUuid(blogUuid)
        val tags: List<Tag> = tagService.getByUuids(tagUuids)
        val categories: List<Category> = categoryUuids.map { uuid -> categoryService.getByUuid(uuid) }.toList()
        return postRepository.save(Post(name, title, content, blog, tags, categories))
    }
    //endregion

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