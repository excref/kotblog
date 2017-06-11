package com.excref.kotblog.blog.service.post

import com.excref.kotblog.blog.service.post.domain.Post

/**
 * @author Rafayel Mirzoyan
 * @since 6/10/17 6:58 PM
 */
interface PostService {
    /**
     * Creates a new post with the given name
     *
     * @param   name The post name
     * @return  Persistent category
     */
    fun create(name: String,
               title: String,
               content: String,
               blogUuid: String,
               tagUuids: List<String>,
               categoryUuids: List<String>
    ): Post

    fun getByUuid(uuid: String): Post
}