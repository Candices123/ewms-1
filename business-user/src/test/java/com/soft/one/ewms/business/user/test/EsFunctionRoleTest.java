package com.soft.one.ewms.business.user.test;

import com.soft.one.ewms.business.user.service.FunctionRoleService;
import com.soft.one.ewms.commons.constants.EsConstant;
import com.soft.one.ewms.domain.pojos.user.FunctionMenu;
import com.soft.one.ewms.domain.pojos.user.FunctionRole;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class EsFunctionRoleTest {

    @Resource
    private FunctionRoleService functionRoleService;

    @Autowired
    private JestClient jestClient;

    /**
     * 添加对于的索引到Elasticsearch
     */
    @Test
    public void testSaveEs(){
        // 数据库查
        List<FunctionRole> functionRoles = functionRoleService.selectAll();
        functionRoles.forEach(functionRole -> {
            // 字段都在某一表，那数据库类也行
            Index.Builder builder = new Index.Builder(functionRole);
            builder.refresh(true);
            // 添加到INDEX_OPERATION索引
            Index index = builder.index(EsConstant.INDEX_FUNCTION_ROLE).type(EsConstant.DEFAULT_DOC).build();
            try {
                JestResult result = jestClient.execute(index);
                if (result != null){
                    System.out.println("添加索引数据成功");
                }
            }catch (IOException e) {
                e.printStackTrace();
                log.error("执行ES创建索引失败，message:{}", e.getMessage());
            }
        });
    }
}
