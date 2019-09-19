package com.ajax;

import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName UploadPic
 * @Author THINK
 * @Date 2019/9/19 16:09
 */

@WebServlet("/UploadPic")
public class UploadPic extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();

        String savePath = this.getServletContext().getRealPath("/upload");
        File saveFileDir = new File(savePath);
        if (!saveFileDir.exists()) {
            saveFileDir.mkdirs();
        }

        String tmpPath = this.getServletContext().getRealPath("/upload/tem");
        File tmpFile = new File(tmpPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        String message="";
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 10);
            factory.setRepository(tmpFile);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024 * 1024 * 3);

            List items = upload.parseRequest(request);
            if (items.size()!=0) {
                FileItem item = (FileItem) items.get(0);
                String fileName = item.getName();
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                if(item.getSize() > 1024 * 1024 * 3){
                    message = message + "文件：" + fileName + "，上传文件大小超过限制大小：" + upload.getFileSizeMax();
                    jsonObject.put("success", 0);
                    jsonObject.put("message",message);
                }else{
                    String saveFileName = makeFileName(fileName);
                    InputStream is = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }

                    out.close();
                    is.close();
                    item.delete();
                    message = message + "文件：" + fileName + "上传成功";

                    String url= "/Blog/upload/"+saveFileName;
                    jsonObject.put("success", 1);
                    jsonObject.put("message",message);
                    jsonObject.put("url", url);
                }

            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            message = message + "上传文件大小超过限制";
            jsonObject.put("success", 0);
            jsonObject.put("message", message);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().print(jsonObject);
    }


    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doGet(request,response);
    }

    private String makeFileName(String fileName) {
        return UUID.randomUUID().toString().replaceAll("-","") + "_" + fileName;
    }
}
