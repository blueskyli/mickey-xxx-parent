package com.xxx.web.controller;

import com.mickey.core.Resp;
import com.mickey.core.enums.JsonResult;
import com.xxx.entity.po.XxxPo;
import com.xxx.service.api.XxxService;
import com.xxx.web.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author J·K
 * @Description: TODO
 * @date 2020/3/30 4:23 下午
 */
@Api(value = "xxx测试", tags = "xxx测试")
@Slf4j
@RestController
public class XxxController extends BaseController {

    @Autowired
    private XxxService xxxService;

    @ApiOperation(value = "selectOne", notes = "selectOne")
    @ApiResponses({
        @ApiResponse(code = 200, message = "请求成功，查看data信息"),
        @ApiResponse(code = 500, message = "错误信息，具体查看msg属性"),
    })
    @GetMapping("/selectOne")
    public JsonResult<XxxPo> one(){
        XxxPo xxxPo = new XxxPo();
        xxxPo = xxxService.selectOne(xxxPo);
        return Resp.ok(xxxPo);
    }
}
