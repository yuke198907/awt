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
import com.simpo.tracker.common.IntegerTools;
import com.simpo.tracker.common.PagerUtils;
import com.simpo.tracker.common.SystemTools;
import com.simpo.tracker.common.SystemUtil;
import com.simpo.tracker.web.annotation.SystemControllerLog;
import com.simpo.tracker.web.product.entity.XcglInfo;
import com.simpo.tracker.web.product.service.XcglService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/xcgl")
public class XcglController {

    private String bakPath = SystemTools.getInstance().getProperty("system.properties", "bakPath");

    @Autowired
    private XcglService xcglService;

    @RequestMapping("/list.do")
    @SystemControllerLog(description = "列表")
    public void list(XcglInfo info, HttpServletRequest request, HttpServletResponse response) {
        String pageNo = request.getParameter("start");
        String pageSize = request.getParameter("length");

        //UserInfo user = (UserInfo) request.getSession().getAttribute("USERINFO");

        if (pageNo == null) {
            pageNo = PagerUtils.pageNo;
        }
        if (pageSize == null) {
            pageSize = PagerUtils.pageSize;
        }

        if (info.getXcmc() != null && !"".equals(info.getXcmc())) {
            info.setXcmc("%" + info.getXcmc() + "%");
        }

        String output = "";
        List<XcglInfo> list = xcglService.list(((IntegerTools.parseInt(pageNo) / IntegerTools.parseInt(pageSize)) + 1) + "", pageSize, true, info);
        JSONArray json = JSONArray.fromObject(list);

        int count = xcglService.count(info);

        output = "{\"recordsTotal\":" + count + ",\"rows\":" + json.toString() + "}";
        SystemUtil.writeJson(output, response);
    }

    @RequestMapping("/find.do")
    @SystemControllerLog(description = "查看")
    public String find(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        XcglInfo info = xcglService.findInfoById(Long.valueOf(id));
        request.setAttribute("info", info);
        return "/page/xcgl_form.jsp";
    }

    @RequestMapping("/delete.do")
    @SystemControllerLog(description = "删除")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        XcglInfo info = xcglService.findInfoById(Long.valueOf(id));
        int row = xcglService.delete(Long.valueOf(id));
        if (row == 1) {
            deleteFile(request.getSession().getServletContext().getRealPath("/awt/") + info.getPic());
            deleteFile(bakPath + "/awt/" + info.getPic());//删除备份

            //删除相册文件
            FileUtils.deleteDirectory(new File(request.getSession().getServletContext().getRealPath("/awt/" + id)));
            FileUtils.deleteDirectory(new File(bakPath + "/awt/" + id));
        }

        SystemUtil.writeHTML(row + "", response);
    }

    @RequestMapping("/update.do")
    @SystemControllerLog(description = "更新")
    public void update(XcglInfo info, HttpServletRequest request, HttpServletResponse response) {

        int row = 0;

        if (info.getId() > 0) {
            info.setGxsj(DateTools.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            row = xcglService.update(info);
        } else {
            info.setCjsj(DateTools.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            row = xcglService.add(info);
        }

        SystemUtil.writeHTML(row + "", response);
    }

    @RequestMapping(value = "/uploadPic.do")
    @SystemControllerLog(description = "更新相册图片")
    public void saveFile(HttpServletRequest request, HttpServletResponse response) {
        String xcid = request.getParameter("xcid");
        String pic_old = request.getParameter("pic");
        String result = "{\"result\":\"0\",\"message\":\"\"}";

        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象

        if (files != null && files.size() == 1) {
            for (MultipartFile myfile : files.values()) {
                try {
                    // 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    // 此处也可以使用Spring提供的MultipartFile.transferTo(File
                    // dest)方法实现文件的上传

                    String pic_new = xcid + "_" + DateTools.getDateString(new Date(), "yyyyMMddHHmmss") + "_" + myfile.getOriginalFilename();

                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(request.getSession().getServletContext().getRealPath("/awt"), pic_new));
                    //备份
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(bakPath + "/awt", pic_new));

                    int row = 0;

                    if (xcid != null && !"".equals(xcid) && !"0".equals(xcid)) {
                        row = xcglService.updatePic(Long.valueOf(xcid), pic_new);
                    } else {
                        row = 1;
                    }

                    if (row == 1) {
                        //删除原文件
                        if (pic_old != null && !"".equals(pic_old)) {
                            deleteFile(request.getSession().getServletContext().getRealPath("/awt/") + pic_old);
                            deleteFile(bakPath + "/awt/" + pic_old);//删除备份
                        }

                        result = "{\"result\":\"1\",\"message\":\"" + pic_new + "\"}";
                    } else {
                        //数据库保存失败，删除新删除的文件
                        deleteFile(request.getSession().getServletContext().getRealPath("/awt/") + pic_new);
                        deleteFile(bakPath + "/awt/" + pic_new);//删除备份
                        result = "{\"result\":\"0\",\"message\":\"" + pic_old + "\"}";
                    }

                } catch (IOException e) {
                    result = "{\"result\":\"0\",\"message\":\"上传失败！\"}";
                }
            }
        } else {
            result = "{\"result\":\"0\",\"message\":\"文件错误！\"}";
        }

        SystemUtil.writeJson(result, response);
    }

    private void deleteFile(String localFileName) {
        //localFileName = localFileName.replace("\\", "/");
        File localFile = new File(localFileName);
        //boolean flag = false;
        if (localFile.isFile() && localFile.exists()) {
            //log.debug("localFile-Path-"+localFile.getPath()+"Ab "+localFile.getAbsolutePath());
            //log.debug("localFile-File-"+localFile+"Ab "+localFile.getAbsoluteFile());
            //log.debug("localFileName--"+localFileName);
            localFile.getAbsoluteFile().delete();
        }
        //log.debug("文件" + localFileName + "是否删除成功：" + flag);
    }

}
