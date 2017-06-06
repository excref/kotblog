package com.excref.kotblog.blog.service.tag.domain

import com.excref.kotblog.blog.service.common.UuidAwareDomain
import javax.persistence.Entity

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:13 PM
 */
@Entity
data class Tag(val name: String) : UuidAwareDomain()