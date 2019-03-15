package com.athome.gmall.upanddownload.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
public class UpAndDownload {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "htmlUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object htmlUpload(@RequestParam(value = "t_pic", required = false) MultipartFile pic
            , HttpServletRequest request) {

        String fileName = "";
        String newName = "";
        if (!pic.isEmpty()) {
            {

                if (pic.getSize() <= 1048576) {
                    System.out.println(pic.getSize());
                    String path = request.getSession().getServletContext().getRealPath("static/img");
                    fileName = pic.getOriginalFilename();
//                    newName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(fileName);
                    newName = UUID.randomUUID().toString() ;
                    //System.out.println("重命名的文件名：" + newName);
                    File file = new File(path, newName);
                    System.out.println("项目中态资源文件夹中img的路径" + path);
                    try {
                        pic.transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return "上传成功";
                }
            }
        }
        return "上传失败";
    }

    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void Download(HttpServletResponse res) {
        String fileName = "FLAMING MOUNTAIN.JPG";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\123\\Pictures\\"
//                    + fileName)));
            bis = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\42194\\Desktop\\a.txt")));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

//    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> testResponseEntity(String realName) throws IOException {
//        realName = "aa";
//        byte[] body = null;
//        String path = "C:\\Users\\42194\\Desktop\\a.txt";
//        File f = new File(path);
//        InputStream in = new FileInputStream(f);
//        body = new byte[in.available()];
//        in.read(body);
//        in.close();
//        HttpHeaders headers = new HttpHeaders();
//        //响应头的名字和响应头的值
//        headers.add("Content-Disposition", "attachment;filename="+new String(realName.getBytes("UTF-8"),"iso-8859-1"));//解决文名称中文乱码
//        HttpStatus statusCode = HttpStatus.OK;
//        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
//        return response;
//    }

}
