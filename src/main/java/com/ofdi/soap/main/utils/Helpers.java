/* OCS - Oracle Consulting Services - Middleware
 *
 * Data de criação 03/12/2018
 * Autor: lucas.dos@oracle.com
 *
 * Projeto: GPA OFDI
 *
 *
 */

package com.ofdi.soap.main.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Helpers {

    @Value("${incentivecompensation.mapping}")
    private String mapping;

    @Value("${incentivecompensation.param1}")
    private String param1;

    @Value("${incentivecompensation.param2}")
    private String param2;

    @Value("${incentivecompensation.param3}")
    private String param3;

    @Value("${utils.encodinginput}")
    private String encodingInput;

    @Value("${utils.encodingoutput}")
    private String encodingOutput;

    @Value("${utils.encoding}")
    private String encoding;

    Logger logger = LoggerFactory.getLogger(Helpers.class);

    public  byte[] getFileContent (String path){
        byte [] fileContent = null;
        try{
            fileContent = Files.readAllBytes(new File(path).toPath());
        }catch (Exception e){
            logger.error("Erro na conversao do arquivo " + path + " em byte[]...");
            logger.info("Aplicação se encerrando....");
            System.exit(0);
        }
        return fileContent;
    }

    public  byte[] getFileContentCsv (String path){
        byte [] fileContent = null;
        File file = new File(path);

        if(Boolean.parseBoolean(encoding)){
            try{

                String content = FileUtils.readFileToString(file, encodingInput);
                FileUtils.write(file, content, encodingOutput);

                fileContent = Files.readAllBytes(new File(path).toPath());

            }catch (Exception e){
                logger.error("Erro na conversao do arquivo " + path + " em byte[]...");
                logger.info("Aplicação se encerrando....");
                System.exit(0);
            }
        }else {
            try{
                fileContent = Files.readAllBytes(new File(path).toPath());
            }catch (Exception e){
                logger.error("Erro na conversao do arquivo " + path + " em byte[]...");
                logger.info("Aplicação se encerrando....");
                System.exit(0);
            }
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

    public List<String> getIncentiveParams(){
        List<String> params = new ArrayList<>();
        params.add(mapping);
        params.add(toDateNow());
        params.add(toDateNow());
        params.add(param1);
        params.add(param2);
        params.add(param3);
        return params;
    }

    public List<String> getImportDataParams(String file, String documentId, String type){
        List<String> params = new ArrayList<>();
        params.add(file);
        params.add(documentId);
        params.add(type);
        return params;
    }
}
