package com.simpo.tracker.web.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simpo.tracker.common.DateTools;
import com.simpo.tracker.common.SystemTools;
import com.simpo.tracker.common.SystemUtil;
import com.simpo.tracker.web.annotation.SystemControllerLog;
import com.simpo.tracker.web.product.entity.TkglInfo;
import com.simpo.tracker.web.product.service.TkglService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/tkgl")
public class TkglController {
    private String bakPath = SystemTools.getInstance().getProperty("system.properties", "bakPath");

    @Autowired
    private TkglService tkglService;

    @RequestMapping("/list.do")
    @SystemControllerLog(description = "列表")
    public void list(TkglInfo info, HttpServletRequest request, HttpServletResponse response) {
        String xcid = request.getParameter("xcid");
        String filetype = request.getParameter("filetype");

        info.setXcid(Long.valueOf(xcid));
        info.setFiletype(filetype);

        String output = "";
        List<TkglInfo> list = tkglService.list(null, null, false, info);
        JSONArray json = JSONArray.fromObject(list);

        int count =tkglService.count(info);

        output = "{\"totalRows\":" + (list == null ? 0 : list.size()) + ",\"rows\":" + json.toString() + "}";
        SystemUtil.writeJson(output, response);
    }

    @RequestMapping("/delete.do")
    @SystemControllerLog(description = "删除")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        TkglInfo info = tkglService.findInfoById(Long.valueOf(id));
        int row = tkglService.delete(Long.valueOf(id));
        if (row == 1) {

            if ("1".equals(info.getFiletype())) {
                deleteFile(request.getSession().getServletContext().getRealPath("/awt/" + info.getXcid() + "/") + info.getTkpic());
                deleteFile(bakPath + "/awt/" + info.getXcid() + "/" + info.getTkpic());//删除缩略图备份
            } else {
                deleteFile(request.getSession().getServletContext().getRealPath("/awt/" + info.getXcid() + "/") + info.getTkpic());
                deleteFile(bakPath + "/awt/" + info.getXcid() + "/" + info.getTkpic());//删除备份
            }

        }

        SystemUtil.writeHTML(row + "", response);
    }

    @RequestMapping(value = "/uploadPic.do")
    @SystemControllerLog(description = "上传相册图片")
    public void saveFile(HttpServletRequest request, HttpServletResponse response) {
        String xcid = request.getParameter("xcid");
        String ftype = request.getParameter("ftype");
        String result = "{\"result\":\"0\",\"message\":\"\"}";

        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象

        if (files != null && files.size() > 0) {
            TkglInfo info = null;
            String pic_new = "";
            int s = 0;
            for (MultipartFile myfile : files.values()) {
                try {
                    // 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    // 此处也可以使用Spring提供的MultipartFile.transferTo(File
                    // dest)方法实现文件的上传

                    pic_new = xcid + "_" + DateTools.getDateString(new Date(), "yyyyMMddHHmmss") + "_" + myfile.getOriginalFilename();

                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(request.getSession().getServletContext().getRealPath("/awt/" + xcid + "/"), pic_new));
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(bakPath + "/awt/" + xcid + "/", pic_new));//备份

                    info = new TkglInfo();
                    info.setCjsj(DateTools.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    info.setTkpic(pic_new);
                    info.setXcid(Long.valueOf(xcid));
                    info.setTkseq(myfile.getOriginalFilename());
                    info.setFiletype(ftype == null ? "0" : "".equals(ftype) ? "0" : ftype);

                    int row = tkglService.add(info);

                    if (row == 1) {
                        //success
                        s = s + 1;
                    } else {
                        deleteFile(request.getSession().getServletContext().getRealPath("/awt" + xcid + "/") + pic_new);
                        deleteFile(bakPath + "/awt/" + xcid + "/" + pic_new);//删除备份
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (s > 0) {
                result = "{\"result\":\"1\",\"message\":\"" + s + "\"}";
            } else {
                result = "{\"result\":\"0\",\"message\":\"上传失败\"}";
            }
        } else {
            result = "{\"result\":\"0\",\"message\":\"文件错误！\"}";
        }

        SystemUtil.writeJson(result, response);
    }

    private void deleteFile(String localFileName) {
        //localFileName = localFileName.replace("\\", "/");
        File localFile = new File(localFileName);
        boolean flag = false;
        if (localFile.isFile() && localFile.exists()) {
            //log.debug("localFile-Path-"+localFile.getPath()+"Ab "+localFile.getAbsolutePath());
            //log.debug("localFile-File-"+localFile+"Ab "+localFile.getAbsoluteFile());
            //log.debug("localFileName--"+localFileName);
            flag = localFile.getAbsoluteFile().delete();
        }
        //log.debug("文件" + localFileName + "是否删除成功：" + flag);
    }

}
