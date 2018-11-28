package com.ofdi.soap.main.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Helpers {

    Logger logger = LoggerFactory.getLogger(Helpers.class);

    public  byte[] getFileContent (String path){
        byte [] fileContent = null;
        try{
            fileContent = Files.readAllBytes(new File(path).toPath());
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileContent;
    }

    public  String toDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public  String toTimesTamp(){
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmSS");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public  boolean verifyFolderCsv(String path, String csvChefe, String csvVendedor){
        File file = new File(path);
        int result = 0;

        if(file.isDirectory()){
            String [] listFiles = file.list();
            for(int i = 0; i < file.list().length; i++){
                if(listFiles[i].equals(csvChefe) || listFiles[i].equals(csvVendedor)){
                    result++;
                }
            }
        }else {
            logger.error(path + " Diretório não existente!");
            logger.info("Aplicação se encerrando....");
            System.exit(0);
        }

        if (result == 2){
            return true;
        }else{
            return false;
        }
    }
}
