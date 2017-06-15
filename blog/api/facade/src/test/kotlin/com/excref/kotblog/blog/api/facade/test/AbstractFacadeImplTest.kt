package com.excref.kotblog.blog.api.facade.test

import com.excref.kotblog.blog.service.test.helper.FacadeImplTestHelper
import org.easymock.EasyMockRunner
import org.easymock.EasyMockSupport
import org.junit.runner.RunWith

/**
 * @author Arthur Asatryan
 * @since 6/15/17 12:23 AM
 */
@RunWith(EasyMockRunner::class)
abstract class AbstractFacadeImplTest constructor(protected val helper: FacadeImplTestHelper = FacadeImplTestHelper()) : EasyMockSupport()