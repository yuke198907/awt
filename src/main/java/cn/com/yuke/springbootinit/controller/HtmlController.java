package cn.com.yuke.springbootinit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuke
 * @version 1.0
 * @date 2021/1/29 11:41
 */
@Controller
public class HtmlController {

    @RequestMapping("start")
    public String start(){
        return "";
    }
}
