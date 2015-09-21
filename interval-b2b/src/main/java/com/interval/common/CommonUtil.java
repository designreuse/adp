package com.interval.common;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;

/**
 * Created by User on 9/4/2015.
 */
public class CommonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    public static int convertToInt(final String key){
        int id = 0;
        try{
            id = Integer.parseInt(key);
        }catch (NumberFormatException exc){

        }
        return id;
    }

    public static byte[] readFileContents(final InputStream fileInputStream){
        byte[] bytes = null;
        try{
            bytes = IOUtils.toByteArray(fileInputStream);
        }catch (IOException ioe){
            LOGGER.error("error occurred while reading uploaded file content", ioe);
        }
        return bytes;
    }

    public static void writeToFile(final InputStream fileInputStream, final String location,
                                   final String fileName){
        OutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(new File(location + fileName));
            outputStream.write(IOUtils.toByteArray(fileInputStream));
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
        }catch (IOException ioe){
            LOGGER.error("error occurred while writing uploaded file content", ioe);
        }
    }

    public static String getFileName(final String id, String contentType){
        String fileName = id;
        if(contentType.equalsIgnoreCase(Constants.IMAGE_JPEG)){
            fileName = fileName + Constants.JPEG;
        }
        return fileName;
    }
}
