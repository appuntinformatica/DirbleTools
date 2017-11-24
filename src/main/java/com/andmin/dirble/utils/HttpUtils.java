package com.andmin.dirble.utils;

import java.io.*;
import okhttp3.*;
import okio.*;
import org.apache.logging.log4j.*;

public class HttpUtils {
    private static final Logger log = LogManager.getLogger();    
    
    public static String get(String url) {
        String result = null;
        final OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();
        Request request = new Request.Builder()
                .url( url )
                .get()
            .build();
        try {
            Response response = httpClient.newCall(request).execute();
            if ( response.isSuccessful() ) {
                ResponseBody body = response.body();
                result = body.string();
            }
            response.close();
        } catch (Exception ex) {
           log.error(ex.getMessage(), ex);
        }
        return result;
    }
    
    
    public static boolean downloadFile(String url, String filename) {
        boolean result = false;
        final OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();
        Request request = new Request.Builder()
                .url( url )
            .build();
        File destFile = new File(filename);
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            if ( response.isSuccessful() ) {
                ResponseBody body = response.body();
                long contentLength = body.contentLength();
                BufferedSource source = body.source();

                BufferedSink sink = Okio.buffer(Okio.sink(destFile));
                Buffer sinkBuffer = sink.buffer();

                long totalBytesRead = 0;
                int bufferSize = 8 * 1024;
                for (long bytesRead; (bytesRead = source.read(sinkBuffer, bufferSize)) != -1; ) {
                    sink.emit();
                    totalBytesRead += bytesRead;
                    int progress = (int) ((totalBytesRead * 100) / contentLength);
                    log.info("url = {} -> progress = {}", url, progress);
                }
                sink.flush();
                sink.close();
                source.close();
                result = true;
            }            
        } catch (Exception ex) {
           log.error(ex.getMessage(), ex);
           if (destFile.exists())
               destFile.exists();
        }
        if (response != null)
            response.close();
        return result;
    }
}

