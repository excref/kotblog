package com.excref.kotblog.blog.service.post

import com.excref.kotblog.blog.service.post.domain.Post

/**
 * @author Rafayel Mirzoyan
 * @since 6/10/17 6:58 PM
 */
interface PostService {
    /**
     * Creates a new post with the given ...
     * @param   name The post name
     * @param   title The post title
     * @param   content The post content
     * @param   blogUuid The post blog uuid
     * @param   tagUuids The post tags uuids
     * @param   categoryUuids The post categories uuids
     * @return  Post if created successfully
     */
    fun create(name: String,
               title: String,
               content: String,
               blogUuid: String,
               tagUuids: List<String>,
               categoryUuids: List<String>
    ): Post

    /**
     * Gets post by uuid
     *
     * @param   uuid The post's uuid
     * @return  Blog
     * @throws  com.excref.kotblog.blog.service.post.exception.PostNotExistsForUuidException When post not found
     */
    fun getByUuid(uuid: String): Post
}