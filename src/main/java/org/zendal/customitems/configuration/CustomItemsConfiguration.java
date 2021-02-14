package org.zendal.customitems.configuration;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Interface of configuration of plugin
 */
public interface CustomItemsConfiguration {

     boolean resourcePackRequired();

     String getResourcePackUrl();


     byte[] getResourcePackHash() throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
