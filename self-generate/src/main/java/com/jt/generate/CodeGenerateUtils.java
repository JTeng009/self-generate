package com.jt.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Template;

/**
 * 
 * @ClassName: CodeGenerateUtils
 * @Description: 代码生成器
 * @author: jiang
 * @date: 2018年9月20日 上午10:27:11
 */
public class CodeGenerateUtils {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=true";
    private final String USER = "root";
    private final String PASSWORD = "123456";
    private final String tableName = "w_app_face_type";
    private final String ENTITY_PACKAGE_NAME = "com.jt.domain";
    private final String tableAnnotation = "头像挂件";
    private final String diskPath = System.getProperty("user.dir") + "/src/main/java/com/jt/domain/entity/";
    private final String className = "FaceType";

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate();
    }

    public void generate() throws Exception {
        try {
            Connection connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
            List<ColumnClass> columnClassList = new ArrayList<>();
            ColumnClass columnClass = null;
            while (resultSet.next()) {
                // id字段略过
//                if (resultSet.getString("COLUMN_NAME").equals("id"))
//                    continue;
                columnClass = new ColumnClass();
                // 获取字段名称
                columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
                // 获取字段类型
                columnClass.setColumnType(jdbcTypeChange(resultSet.getString("TYPE_NAME")));
                // 转换字段名称，如 sys_name 变成 SysName
                columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
                // 字段在数据库的注释
                columnClass.setColumnComment(resultSet.getString("REMARKS"));
                columnClassList.add(columnClass);
            }
            // 生成Mapper文件
            generateMapperFile(columnClassList);
//            // 生成Dao文件
//            generateDaoFile(columnClassList);
//            // 生成Repository文件
//            generateRepositoryFile(columnClassList);
//            // 生成服务层接口文件
//            generateServiceInterfaceFile(columnClassList);
//            // 生成服务实现层文件
//            generateServiceImplFile(columnClassList);
//            // 生成Controller层文件
//            generateControllerFile(columnClassList);
//            // 生成DTO文件
//            generateDTOFile(columnClassList);
            // 生成Model文件

            generateModelFile(columnClassList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    private void generateModelFile(List<ColumnClass> columnClassList) throws Exception {
        final String suffix = ".java";
        final String path = diskPath + className + suffix;
        final String templateName = "Model.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    private void generateDTOFile(List<ColumnClass> columnClassList) throws Exception {
        final String suffix = "DTO.java";
        final String path = "D://" + className + suffix;
        final String templateName = "DTO.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateControllerFile(List<ColumnClass> columnClassList) throws Exception {
        final String suffix = "Controller.java";
        final String path = diskPath + className + suffix;
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateServiceImplFile(List<ColumnClass> columnClassList) throws Exception {
        final String suffix = "ServiceImpl.java";
        final String path = diskPath + className + suffix;
        final String templateName = "ServiceImpl.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateServiceInterfaceFile(List<ColumnClass> columnClassList) throws Exception {
        final String prefix = "I";
        final String suffix = "Service.java";
        final String path = diskPath + prefix + className + suffix;
        final String templateName = "ServiceInterface.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateRepositoryFile(List<ColumnClass> columnClassList) throws Exception {
        final String suffix = "Repository.java";
        final String path = diskPath + className + suffix;
        final String templateName = "Repository.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateDaoFile(List<ColumnClass> columnClassList) throws Exception {
        final String suffix = "DAO.java";
        final String path = diskPath + className + suffix;
        final String templateName = "DAO.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    private void generateMapperFile(List<ColumnClass> columnClassList) throws Exception {
        final String suffix = "Mapper.xml";
        final String path = diskPath + className + suffix;
        final String templateName = "Mapper.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        dataMap.put("table_name", tableName);
        dataMap.put("class_name", className);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap)
            throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name", tableName);
        dataMap.put("class_name", className);
        dataMap.put("package_name", ENTITY_PACKAGE_NAME);
        dataMap.put("table_annotation", tableAnnotation);
        dataMap.put("entity_name", "com.jt.domain.entity");
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }

    /**
     * 
     * @Title: generateFileByTemplate
     * @Description: 数据库大驼峰处理
     * @param templateName
     * @param file
     * @param dataMap
     * @throws Exception
     * @return: void
     */
    public String replaceUnderLineAndUpperCase(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }

    private String jdbcTypeChange(String type) {
        switch (type) {
        case "INT":
            return "INTEGER";
        case "DATETIME":
            return "TIMESTAMP";
        default:
            return type;
        }
    }
}
