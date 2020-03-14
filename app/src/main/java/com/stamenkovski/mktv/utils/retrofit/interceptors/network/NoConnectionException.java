package com.stamenkovski.mktv.utils.retrofit.interceptors.network;



import java.io.IOException;

/**
 * Created by martin on 2/8/18.
 */

public class NoConnectionException extends IOException {


  @Override
  public String getMessage() {
    return "No connectivity exception";
  }
}