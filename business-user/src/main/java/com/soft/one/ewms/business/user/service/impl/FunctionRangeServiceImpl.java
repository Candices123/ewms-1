package com.soft.one.ewms.business.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.soft.one.ewms.domain.mappers.user.FunctionRangeMapper;
import com.soft.one.ewms.business.user.service.FunctionRangeService;
@Service
public class FunctionRangeServiceImpl implements FunctionRangeService{

    @Resource
    private FunctionRangeMapper functionRangeMapper;

}
