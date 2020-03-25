package com.sys.supervision.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static String paramCheck(String param) {
        return StringUtils.isBlank(param) ? null : param;
    }

    public static String paramCheck(Object param) {
        return param == null ? null : param.toString();
    }

    public static String emptyFilter(Object param) {
        return param == null ? "" : param.toString();
    }

    public static BigDecimal decimalFilter(String param) {
        try {
            return new BigDecimal(param);
        } catch (Exception var) {
//            var.printStackTrace();
            logger.error("{} convert to bigDecimal error", param);
        }

        return new BigDecimal(0);
    }

    public static BigDecimal check(BigDecimal decimal) {
        return decimal == null ? new BigDecimal(0) : decimal;
    }

    public static void downloadResponse(File file, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(file.getName(), "UTF-8"));
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = httpServletResponse.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void downloadResponse(HttpServletResponse httpServletResponse, Class clazz, List data) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
//        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("数据", "UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
//        EasyExcel.write(httpServletResponse.getOutputStream(), clazz).sheet("1").doWrite(data);
        httpServletResponse.getOutputStream().flush();
        httpServletResponse.getOutputStream().close();
    }

    public static Integer makeAnnual(Integer year, Integer month) {
        return month < 3 ? year - 1: year;
    }



    public static BigDecimal getSalary(BigDecimal gongfen, Double gongshi, BigDecimal baojin) {
        gongfen = check(gongfen);
        baojin = check(baojin);
        BigDecimal gs = new BigDecimal(gongshi == null ? 0 : gongshi);
        return baojin.add(gongfen.multiply(gs));
    }

    public static BigDecimal getDianGong(BigDecimal gongfen, Double gongshi) {
        if (gongfen == null || gongshi == null) return null;
        gongfen = check(gongfen);
        BigDecimal gs = new BigDecimal(gongshi == null ? 0 : gongshi);
        return gongfen.multiply(gs);
    }
}
