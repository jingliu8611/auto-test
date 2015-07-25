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

            excel.getFlaggedMethods("Flag");

            int totalNoOfElementsFlaggedToRun = excel.flaggedMethod.size();

            Element rootElementSuite = document.createElement("suite");

            for (int elementCounter = 1; elementCounter <= totalNoOfElementsFlaggedToRun; elementCounter++) {
                Element rootElementParameter = document.createElement("parameter");

                String[] flagElement = excel.flaggedMethod.get(elementCounter).toString().split(";");

                rootElementParameter.setAttribute("name", flagElement[0]);
                rootElementParameter.setAttribute("value", flagElement[1]);
                rootElementSuite.appendChild(rootElementParameter);

            }

            Element rootElementTest = document.createElement("test");
            Element rootElementClass = document.createElement("classes");
            Element childElementClass = document.createElement("class");

            childElementClass.setAttribute("name", "com.istuary.tests.SmokeTest");

            Element rootElementGroups = document.createElement("methods");

            rootElementSuite.setAttribute("name", "SeleniumJavaFramework");
            rootElementTest.setAttribute("name", "testing");

            rootElementSuite.appendChild(rootElementTest);
            rootElementTest.appendChild(rootElementClass);
            rootElementClass.appendChild(childElementClass);
            childElementClass.appendChild(rootElementGroups);
            document.appendChild(rootElementSuite);

            for (int elementCounter = 1; elementCounter <= totalNoOfElementsFlaggedToRun; elementCounter++) {

                String element = "include";
                Element em = document.createElement(element);
                String[] flagElement = excel.flaggedMethod.get(elementCounter).toString().split(";");
                em.setAttribute("name", flagElement[0]);
                rootElementGroups.appendChild(em);
            }

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
