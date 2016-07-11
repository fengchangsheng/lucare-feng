package com.fcs.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Lucare.Feng on 2016/5/31.
 * 自动生成实体类工具
 */
public class GenerateEntityUtil {

    private String packageOutPath = "cn.pxzs.train.share.test";//指定实体生成所在包的路径
    private static Connection con = null;
    private String[] tablenames = null;//表名
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    //数据库连接
    private static final String URL = "jdbc:mysql://localhost:3306/wechatplat";
    private static final String NAME = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    GenerateEntityUtil() {
        tablenames = new String[]{"user","role"};
        for (String tableName : tablenames) {
            generate(tableName);
        }
    }

    private void generate(String tablename){
        //查要生成实体类的表
        String sql = "select * from " + tablename;
        PreparedStatement pStemt = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, NAME, PASS);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();   //统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            String content = parse(colnames, colTypes, colSizes,tablename);
            File directory = new File("D:/devData/common/train-share");
            //System.out.println("绝对路径："+directory.getAbsolutePath());
            //System.out.println("相对路径："+directory.getCanonicalPath());
            //String path = this.getClass().getResource("").getPath();
            String outputPath = directory.getAbsolutePath() + "/src/main/java/" + this.packageOutPath.replace(".", "/") + "/" + initcap(tablename) + ".java";
            FileWriter fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String parse(String[] colnames, String[] colTypes, int[] colSizes, String tablename) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n");
        sb.append("\r\n");
        //判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        //注释部分
        sb.append("\r\n/**\r\n");
        sb.append(" * " + tablename + " 实体类\r\n");
        sb.append(" */ \r\n");
        //实体部分
        sb.append("public class " + initcap(tablename) + "{\r\n");
        processAllAttrs(sb);//属性
        processAllMethod(sb);//get set方法
        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\r\n\t@FCMapping(column = \""+colnames[i]+"\" , comment = \"\")\n");
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\n\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +
                    colnames[i] + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\n\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }

    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    public static void main(String[] args) {
        new GenerateEntityUtil();
    }
}
