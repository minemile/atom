package ru.atom.chat;

import okhttp3.*;

import java.io.IOException;


public class ChatClient {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost";
    private static final String PORT = ":8080";

    public static Response login(String name) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Request request = new Request.Builder()
                .post(RequestBody.create(mediaType, "name=" + name))
                .url(PROTOCOL + HOST + PORT + "/chat/login")
                .build();

        return client.newCall(request).execute();
    }

    public static Response viewChat() throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(PROTOCOL + HOST + PORT + "/chat/chat")
                .addHeader("host", HOST + PORT)
                .build();

        return client.newCall(request).execute();
    }

    public static Response viewOnline() throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(PROTOCOL + HOST + PORT + "/chat/online")
                .addHeader("host", HOST + PORT)
                .build();
        return client.newCall(request).execute();
    }

    public static Response say(String name, String msg) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("msg", msg)
                .build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(PROTOCOL + HOST + PORT + "/chat/say")
                .addHeader("host", HOST + PORT)
                .build();
        return client.newCall(request).execute();
    }
}