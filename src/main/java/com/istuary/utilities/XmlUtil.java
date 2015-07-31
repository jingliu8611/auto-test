package com.istuary.utilities;

import org.testng.TestNG;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jliu on 22/07/15.
 */
public class XmlUtil {

    public static void createXml() throws Exception {

        try {
            String xlFilePath = "resources//data//Initial.xls";
            ExcelUtil excel = new ExcelUtil(xlFilePath);
            excel.columnDictionary();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            excel.getFlaggedTests();

            int totalNoOfTestToRun = excel.flaggedTest.size();

            Element rootElementSuite = document.createElement("suite");
            rootElementSuite.setAttribute("name", "SeleniumJavaFramework");

            for (int testCnt = 1; testCnt <= totalNoOfTestToRun; testCnt++) {
                Element rootElementTest = document.createElement("test");
                String testName = excel.flaggedTest.get(testCnt).toString();
                rootElementTest.setAttribute("name", testName);
                excel.getFlaggedClasses(testName);
                Element rootElementClass = document.createElement("classes");
                int totalNoOfClassInTest = excel.flaggedClass.size();

                for (int classCnt = 1; classCnt <= totalNoOfClassInTest; classCnt++) {
                    Element childElementClass = document.createElement("class");
                    String className = excel.flaggedClass.get(classCnt).toString();
                    childElementClass.setAttribute("name", "com.istuary.tests." + className);
                    excel.getFlaggedMethods(testName,className);
                    int totalNoOfMethodInClass = excel.flaggedMethod.size();
                    Element rootElementGroups = document.createElement("methods");

                    for (int elementCounter = 1; elementCounter <= totalNoOfMethodInClass; elementCounter++) {

                        String element = "include";
                        Element em = document.createElement(element);
                        String flagElement = excel.flaggedMethod.get(elementCounter).toString();
                        em.setAttribute("name", flagElement);
                        rootElementGroups.appendChild(em);
                    }

                    rootElementClass.appendChild(childElementClass);
                    childElementClass.appendChild(rootElementGroups);

                }
                rootElementSuite.appendChild(rootElementTest);
                rootElementTest.appendChild(rootElementClass);

            }

            document.appendChild(rootElementSuite);

            FileWriter fstream = new FileWriter("resources//testng.xml");
            BufferedWriter out = new BufferedWriter(fstream);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(fstream);

            transformer.transform(source, result);

            out.close();

        } catch (DOMException e) {
            e.printStackTrace();
        }

    }

    public static void autoRunXml() {

        System.out.println("autoRunXml is called");

        List<String> files = new ArrayList<String>();

        files.add("resources//testng.xml");

        TestNG tng = new TestNG();

        tng.setTestSuites(files);

        tng.run();

        System.out.println("autoRunXml is ended");
    }

}
