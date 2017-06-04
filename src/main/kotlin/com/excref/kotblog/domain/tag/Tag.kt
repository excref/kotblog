package com.excref.kotblog.domain.tag

import com.excref.kotblog.domain.common.UuidAwareDomain
import javax.persistence.Entity

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:13 PM
 */
@Entity
data class Tag(
        val name: String
) : UuidAwareDomain()