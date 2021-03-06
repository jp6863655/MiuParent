package org.igetwell.web;

import org.igetwell.annotation.AccessLimit;
import org.igetwell.common.utils.ResponseEntity;
import org.igetwell.system.activity.service.IActivityEnrollService;
import org.igetwell.system.limit.service.IRateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController extends BaseController {

    @Autowired
    private IRateLimitService rateLimitService;

    @Autowired
    private IActivityEnrollService activityEnrollService;

    @RequestMapping("/seckill")
    @ResponseBody
    @AccessLimit(limit = 4, sec = 10)  //加上自定义注解即可
    public String seckill (){
        return   "hello world !";
    }


    @RequestMapping("/testReq")
    @ResponseBody
    @AccessLimit(limit = 4, sec = 10)  //加上自定义注解即可
    public ResponseEntity testReq (){
        return new ResponseEntity("啦啦啦啦");
    }

    @RequestMapping("/apply")
    @ResponseBody
    @AccessLimit(limit = 4, sec = 10)  //加上自定义注解即可
    public ResponseEntity apply (){
        return activityEnrollService.save();
    }


    @RequestMapping("/queueAdd")
    @ResponseBody
    public ResponseEntity add(){
        return new ResponseEntity();
    }

    /*@RequestMapping(value = "/download", method = RequestMethod.GET)
    *//*@RequiresPermissions(permissionValue="download")*//*
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
        String fileName = "-----.xlsx";
        String path = request.getSession().getServletContext().getRealPath("/") + "download/" + fileName;
        File file = new File(path);
        //处理显示中文文件名的问题
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //设置请求头内容,告诉浏览器代开下载窗口
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }*/
}
