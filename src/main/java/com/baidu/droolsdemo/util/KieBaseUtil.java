package com.baidu.droolsdemo.util;

import com.baidu.droolsdemo.pojo.Person;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @project: drools
 * @description: Kie 工具类
 * @author: luyao12
 * @create: 2019-12-26 15:09
 **/
public class KieBaseUtil {
    /**
     * 动态加载规则文件内容
     *
     * @param rule
     * @return
     */
    public static KieBase ruleKieBase(String rule) {
        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = null;
        try {
            kieHelper.addContent(rule, ResourceType.DRL);
            kieBase = kieHelper.build();
        } catch (Exception e) {
            System.out.println("转换动态规则内容错误！");
            e.printStackTrace();
        }
        return kieBase;
    }

    /**
     * 将决策表转换为 drl 规则文件内容
     *
     * @param filePath
     * @return
     */
    public static String transXlsToDrl(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        String drl = converter.compile(is, InputType.XLS);
        return drl;
    }

    /**
     * 根据文件路径获取 Person 实体列表
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static List<Person> getPersonEntityFromFile(String filePath) throws FileNotFoundException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        List<Person> res = new ArrayList<>();
        try {
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            /**
             * 读取文件内容
             */
            String line = br.readLine();
            while (line != null && !line.equals("")) {
                String[] array = line.split(",");

                Person person = new Person();
                person.setId(Integer.parseInt(array[0]));
                person.setGender(Integer.parseInt(array[1]));
                person.setAge((int) Double.parseDouble(array[2]));
                person.setXdScore((int) Double.parseDouble(array[3]));
                res.add(person);

                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (Exception e) {
                br = null;
                isr = null;
                fis = null;
            }
        }
        return res;
    }
}
