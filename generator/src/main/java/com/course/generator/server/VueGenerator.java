package com.course.generator.server;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreeMarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: HB
 * @Description: Vue前端模板生成启动类
 * @CreateDate: 16:20 2020/9/30
 */

public class VueGenerator {
    private static String MODULE = "business";
    // 模板输出位置
    private static String toVuePath = "admin\\src\\views\\admin\\";
    private static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) throws Exception {

        /* v2.0 版本 - 从generatorConfig.xml得到相应变量 */
        // 只生成配置文件中的第一个table节点
        File file = new File(generatorConfigPath);
        SAXReader reader = new SAXReader();
        //读取xml文件到Document中
        Document doc = reader.read(file);
        //获取xml文件的根节点
        Element rootElement = doc.getRootElement();
        //读取context节点
        Element contextElement = rootElement.element("context");
        //定义一个Element用于遍历
        Element tableElement;
        //取第一个“table”的节点
        tableElement = contextElement.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String name = DbUtil.getTableComment(tableName);
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("表："+tableElement.attributeValue("tableName"));
        System.out.println("Domain："+tableElement.attributeValue("domainObjectName"));

        // 填充其他数据
        String module = "business";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd");
        String dateStr = simpleDateFormat.format(new Date());
        // 关于Dto的模板数据
        // 字段相关信息
        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fieldList);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("Domain", Domain);
        paramMap.put("domain", domain);
        paramMap.put("date", dateStr);
        paramMap.put("name", name);
        paramMap.put("module", module);
        paramMap.put("fieldList", fieldList);
        paramMap.put("typeSet", typeSet);

        // 开始生成
        FreeMarkerUtil.initConfig("vue.ftl");
        FreeMarkerUtil.generator(paramMap, toVuePath + domain + ".vue");



    }

    /**
     * 获取所有的Java类型，使用Set去重
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
