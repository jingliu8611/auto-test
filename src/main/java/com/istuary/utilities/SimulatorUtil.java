package com.istuary.utilities;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jliu on 20/08/15.
 */
public class SimulatorUtil {


    public static void main(String[] args) throws Exception {

        connectSimulator("10.0.10.185","10.0.10.58");
        sendModbusFlowData4("10.0.10.185","10.0.10.58");

    }

    public static void connectSimulator (String simulatorIP, String testServerIP) {
        try {

            URL url = new URL("http://"+simulatorIP+"/api/v1/simo/zoo/mw/connect");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"ip\":\""+testServerIP+"\",\"hostname\":\"IstuaryOS\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendModbusFlowData4 (String simulatorIP, String testServerIP) {
        try {

            URL url = new URL("http://"+simulatorIP+"/api/v1/simo/replay/flowdata");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"dest_ip\":\""+testServerIP+"\",\"log_name\":\"58_flow_modbus.log\",\"topic\":\"flowData2\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
