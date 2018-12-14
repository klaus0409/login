package com.microwise.login_http.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
    public static String readSteam(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        byte[] bytes = new byte[1024];
        while ((len=in.read(bytes))!=-1){
            baos.write(bytes,0,len);
        }
        in.close();
        String content = new String(baos.toByteArray());
        return content;
    }
}
