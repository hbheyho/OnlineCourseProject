package com.course.generator.util;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: HB
 * @Description: FreeMarker 生成器
 * @CreateDate: 16:05 2020/9/30
 */

public class FreeMarkerUtil {

    // 模板路径
    static final String templatePath = "generator\\src\\main\\java\\com\\course\\generator\\ftl";;

    private static Template template = null;
    /**
     * @Author: HB
     * @Description: 初始化FreeMarker 配置
     * @Date: 16:05 2020/9/30
     * @Params: name
     * @Returns:
    */
    public static void initConfig(String templateName) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        // 设置模板存放路径
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        // 指定模板文件
        template = configuration.getTemplate(templateName);
    }

    /**
     * @Author: HB
     * @Description: 生成模板文件
     * @Date: 16:16 2020/9/30
     * @Params: fileName - 最后要生成文件
     * @Returns:
    */
    public static void generator(Map<String, Object> dataModel, String fileName) throws IOException, TemplateException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        template.process(dataModel, bufferedWriter);
        bufferedWriter.flush();
        fileWriter.close();
    }
}
