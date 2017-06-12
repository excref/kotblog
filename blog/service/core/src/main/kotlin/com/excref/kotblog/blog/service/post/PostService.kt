package com.excref.kotblog.blog.service.post

import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.post.domain.Post
import com.excref.kotblog.blog.service.tag.domain.Tag
import com.excref.kotblog.blog.service.user.domain.User

/**
 * Created by Android Studio.
 * User: Gurgen Arustamyan
 * Date: 6/8/17
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
interface PostService {

    /**
     * Creates a new post
     *
     * @param title The post title
     * @param content The post content
     * @param tags The post tags
     * @param userId The post creator id
     * @return Persistent post
     * @throws com.excref.kotblog.blog.service.post.exception.PostWithEmptyContentException If the post content is empty
     */
    fun create(title: String, content: String, categories: List<Category>, tags: List<Tag>, userId: User): Post

    /**
     * Gets post by given uuid
     * @param   uuid The post uuid
     * @return  The post
     * @throws  com.excref.kotblog.blog.service.post.exception.PostNotExistsForUuidException If post not exists
     */
    fun getByUuid(uuid: String): Post?
}