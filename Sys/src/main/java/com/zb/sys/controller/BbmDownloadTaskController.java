package com.zb.sys.controller;

import com.zb.base.define.MessageDefine;
import com.zb.base.message.JsonMessage;
import com.zb.sys.download.TaskDispatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzheng on 2019/12/10.
 */
@RestController
@RequestMapping("/api/downloadTask")
@Api(value = "下载任务记录 Client Restful API ", description = "下载任务记录 Client Restful API ", protocols = "application/json")
public class BbmDownloadTaskController {

    @Autowired
    TaskDispatch taskDispatch;

    /**
     * 取消
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancel",method = RequestMethod.PUT)
    @ApiOperation(value = "取消任务",notes = "取消任务")
    @ApiResponse(code = 200,message = "取消任务")
    public JsonMessage cancel(@ApiParam(value = "id") @RequestParam Long id){
        taskDispatch.cancel(id.toString());
        return MessageDefine.SUCCESS_DELETE.toJsonMessage(true);
    }

}
