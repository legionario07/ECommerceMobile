package com.example.paulinho.ecommercemobile.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ImagemUtils {

    private static InputStream input;

    public static InputStream getInputFromString(final String stringUrl){
              try {
                  URL url = new URL(stringUrl);
                  HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                  input = conexao.getInputStream();
              } catch (IOException e) {
                  e.printStackTrace();
              }

        return input;
    }
}
