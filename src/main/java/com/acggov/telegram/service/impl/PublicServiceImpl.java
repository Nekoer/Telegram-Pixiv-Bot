package com.acggov.telegram.service.impl;

import com.acggov.telegram.constant.AppConstant;
import com.acggov.telegram.service.PublicService;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.io.*;
import java.net.URL;

/**
 * Created: Nekoer
 * Desc:
 * Date: 2020/7/26 0:06
 */
@Service
public class PublicServiceImpl extends TelegramWebhookBot implements PublicService {

    @Value("${bot.api-key}")
    private String botToken;

    @Value("${bot.username}")
    private String userName;

    @Value("${acggov.apikey}")
    private String apiKey;

    @Override
    public void sendAmazingPic(Update update) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
        HttpGet httpGet = new HttpGet();
        JSONObject jsonObject = null;
        Object picData = null;
        String uri = null;
        try{

//          https://api.acg-gov.com/public/setu

            httpGet = new HttpGet("https://api.acg-gov.com/public/setu");
            httpGet.addHeader("token",apiKey);
            //发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            InputStream in = response.getEntity().getContent();
            byte[] buffer = new byte[1];
            int len = 0;
            String data = "";
            while ((len = in.read(buffer)) > 0) {
                infoStream.write(buffer, 0, len);
            }

            infoStream.close();
            httpClient.close();

            jsonObject = JSONObject.parseObject(infoStream.toString("UTF-8"));
            picData = jsonObject.get("data");
            jsonObject = JSONObject.parseObject(String.valueOf(picData));
            String pageCount = jsonObject.getString("pageCount");
            String title = jsonObject.getString("title");
            JSONArray originals = JSONArray.parseArray(jsonObject.getString("originals"));

            if (pageCount.matches(AppConstant.ISNUMBER)){
                int num = (int) (Math.random()*(Integer.parseInt(pageCount) - 1)+0);
                jsonObject = JSONObject.parseObject(String.valueOf(originals.get(num)));
                uri = jsonObject.getString("url");
            }
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(update.getMessage().getChat().getId());
            sendPhoto.setPhoto(title,getPicBytes(uri));
            execute(sendPhoto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public InputStream getPicBytes(String uri) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
        HttpGet httpGet = new HttpGet();
        try{

            httpGet = new HttpGet(uri);
            httpGet.addHeader("referer","https://www.acg-gov.com");
            //发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 403){
                return null;
            }
            InputStream in = response.getEntity().getContent();
            byte[] buffer = new byte[1];
            int len = 0;
            String data = "";
            while ((len = in.read(buffer)) > 0) {
                infoStream.write(buffer, 0, len);
            }

            infoStream.close();
            httpClient.close();

            return new ByteArrayInputStream(infoStream.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @SneakyThrows
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        if ("/setu@Hcyacg_bot".contains(update.getMessage().getText())){
            sendAmazingPic(update);
        }

        return null;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return userName;
    }
}
