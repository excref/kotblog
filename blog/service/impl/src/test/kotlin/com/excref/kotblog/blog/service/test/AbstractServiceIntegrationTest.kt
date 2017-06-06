package com.excref.kotblog.blog.service.test

import com.excref.kotblog.blog.service.test.helper.ServiceIntegrationTestHelper
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:09 PM
 */
@RunWith(org.springframework.test.context.junit4.SpringRunner::class)
@SpringBootTest
@ContextConfiguration("classpath:applicationContext-service.xml")
@Ignore
abstract class AbstractServiceIntegrationTest : AbstractTransactionalJUnit4SpringContextTests() {

    //region Dependencies
    @Autowired
    protected lateinit var helper: ServiceIntegrationTestHelper
    //endregion
}