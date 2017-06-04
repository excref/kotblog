package com.excref.kotblog.service.test

import com.excref.kotblog.service.test.helper.ServiceIntegrationTestHelper
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:09 PM
 */
@RunWith(SpringRunner::class)
@SpringBootTest
abstract class AbstractServiceIntegrationTest : AbstractTransactionalJUnit4SpringContextTests() {

    //region Dependencies
    @Autowired
    protected lateinit var helper: ServiceIntegrationTestHelper
    //endregion
}