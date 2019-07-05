package com.github.neatlife.jframework.test.controller;

import com.github.neatlife.jframework.fundation.controller.Controller;
import com.github.neatlife.jframework.fundation.util.CsvUtil;
import com.github.neatlife.jframework.test.dto.ExcelUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("excel")
@Slf4j
public class ExcelController extends Controller {

    @GetMapping("/export1")
    public void exportCsv(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // csv文件名字，为了方便默认给个名字，当然名字可以自定义，看实际需求了
        String fileName = "csv文件.csv";
        // 解决不同浏览器出现的乱码
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);
        FileCopyUtils.copy(genCsvUserDto(), response.getOutputStream());
    }

    private byte[] genCsvUserDto() {
        // 模拟导出数据，这里数据可以是从数据库获取回来的，也可以是前端传过来再解析的
        // 这里的数据应该放在dao层获取的，就先简单放在这里，大家不必介意，只是demo演示
        List<ExcelUserDto> userDtoList = new ArrayList<>();
        userDtoList.add(new ExcelUserDto("13800138001", "圣诞老人1", "VIP1"));
        userDtoList.add(new ExcelUserDto("13800138002", "圣诞老人2", "VIP7"));
        userDtoList.add(new ExcelUserDto("13800138003", "圣诞老人3", "VIP8"));
        // 为了方便，也不写dao层
        List<LinkedHashMap<String, Object>> exportData = new ArrayList<>(userDtoList.size());
        // 行数据
        for (ExcelUserDto excelUserDto : userDtoList) {
            LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();
            rowData.put("1", excelUserDto.getUsername());
            rowData.put("2", excelUserDto.getNickname());
            rowData.put("3", excelUserDto.getLevel());
            exportData.add(rowData);
        }
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("1", "用户账号");
        header.put("2", "用户昵称");
        header.put("3", "用户等级");
        return CsvUtil.export(header, exportData);
    }
}
