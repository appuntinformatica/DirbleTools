package com.andmin.dirble;

import com.andmin.dirble.json.*;
import java.io.*;
import java.util.*;
import com.andmin.dirble.utils.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;
import org.apache.logging.log4j.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

public class EntryPoint {
    private static final Logger log = LogManager.getLogger();
        
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
    private static String outputFolder = "";
    
    private static void downloadAllCategories(ApplicationContext ctx) {
        Path path = new File(outputFolder, "categories.json").toPath();
        if ( !path.toFile().exists() ) {
            String getAllCategoriesURL = ctx.getBean("getAllCategoriesURL", String.class);
            log.info("getAllCategoriesURL = {}", getAllCategoriesURL);
            String allCategories = HttpUtils.get(getAllCategoriesURL);
            try {
                Files.write(path, allCategories.getBytes());
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        } else {
            log.info("'{}' esiste già", path);
        }
    }
    
    private static void downloadAllCountries(ApplicationContext ctx) {
        Path path = new File(outputFolder, "countries.json").toPath();
        if ( !path.toFile().exists() ) {
            String getAllCountriesURL = ctx.getBean("getAllCountriesURL", String.class);
            log.info("getAllCountriesURL = {}", getAllCountriesURL);
            String allCountries = HttpUtils.get(getAllCountriesURL);
            try {
                Files.write(path, allCountries.getBytes());
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        } else {
            log.info("'{}' esiste già", path);
        }
    }
    
    private static void downloadStations(ApplicationContext ctx) {
        final String getStationsURL = ctx.getBean("getStationsURL", String.class);
        int page = 0;
        boolean exit = false;
        while (!exit) {
            page++;
            String url = getStationsURL + "&page=" + page;
            log.info("getStationsURL = {}", url);
            
            Path path = new File(outputFolder, "stations_" + page + ".json").toPath();
            if ( !path.toFile().exists() ) {                
                String results = HttpUtils.get(url);
                if (results != null) {
                    List<StationInput> stations = GsonUtils.stringToArray(results, StationInput[].class);
                    if (stations.size() == 0) {
                        exit = true;
                    } else {
                        try {
                            Files.write(path, results.getBytes());
                        } catch (IOException ex) {
                            log.error(ex.getMessage(), ex);
                        }
                    }                    
                } else {
                    log.warn("stations is null!");
                    exit = true;
                }
            } else {
                log.info("'{}' esiste già", path);
            }
        }
    }
    
    private static void printStations(String inputFolder) {
        int page = 0;
        boolean exit = false;
        Map<Long, List<Long>> categoryStations = new TreeMap<>();
        List<StationOutput> stationOutputs = new ArrayList<>();
        while (!exit) {
            page++;
            File file = new File(inputFolder, "stations_" + page + ".json");
            if ( file.exists() ) {
                Stream<String> streams = null;
                try {
                    streams = Files.lines(file.toPath());
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                }
                if (streams != null) {
                    log.info("{}] '{}'", page, file.getAbsolutePath());
                    StringBuilder sb = new StringBuilder();
                    streams.forEach(line -> sb.append(line));
                    streams.close();

                    List<StationInput> stationInputs = GsonUtils.stringToArray(sb.toString(), StationInput[].class);
                    for (StationInput si : stationInputs) {
                        List<StationOutput> temp = new ArrayList<StationOutput>();
                        for (StreamInfoInput sii : si.getStreams()) {
                            String url = sii.getStream().trim();
                            if ( url.startsWith("http") ) {
                                boolean alreadyInserted = false;
                                for (StationOutput t : temp) {
                                    if ( t.getStreamUrl().equals(url) ) {
                                        alreadyInserted = true;
                                    }
                                }
                                if ( !alreadyInserted ) {
                                    StationOutput e = new StationOutput();
                                    e.setId(si.getId());
                                    e.setName(si.getName());
                                    e.setCountry(si.getCountry());
                                    if ( si.getImage() != null && si.getImage().getUrl() != null )
                                        e.setImageUrl(si.getImage().getUrl());
                                    e.setStreamUrl(url);
                                    e.setBitrate(sii.getBitrate());
                                    stationOutputs.add(e);

                                    for (CategoryInput ci : si.getCategories()) {
                                        if ( categoryStations.get(ci.getId()) == null ) {
                                            categoryStations.put(ci.getId(), new ArrayList<Long>());
                                        }
                                        if ( !categoryStations.get(ci.getId()).contains(si.getId()) ) {
                                            categoryStations.get(ci.getId()).add(si.getId());
                                        }
                                    }
                                    
                                    temp.add(e);
                                } else {
                                    log.warn("{} already inserted", si.getId());
                                }
                            } else {
                                log.warn("StationId = {} has not valid url = '{}'", si.getId(), url);
                            }
                        }
                    }
                } else {
                    log.info("streams is null!");
                    exit = true;
                }
            } else {
                log.info("'{}' not exists", file.getAbsolutePath());
                exit = true;
            }
        }
        String json = GsonUtils.arrayToString(stationOutputs, true);
        try {
            Files.write(new File("stations.json").toPath(), json.getBytes());
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        json = GsonUtils.mapToString(categoryStations, false);
        try {
            Files.write(new File("category_stations.json").toPath(), json.getBytes());
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
   
    public static void main(String[] args) throws Exception {
        /*
        File file = new File("spring.xml");
        ApplicationContext ctx = file.exists() ? new FileSystemXmlApplicationContext(file.getName()) : new ClassPathXmlApplicationContext(file.getName());
        */
        String output = "output/" + sdf.format(new Date());
        Files.createDirectories(Paths.get(output));
        outputFolder = new File(output).getAbsolutePath();
        /*
        downloadAllCategories(ctx);
        downloadAllCountries(ctx);
        downloadStations(ctx);
        */
        
        printStations(new File("output/2017_09_10").getAbsolutePath());
        
        
        /*updateAllCategories(ctx);
        updateAllCountries(ctx);        
        
        updateStations(ctx);
        */
      //  printStations(ctx);   
        //printCategories(ctx);
        //printCountries(ctx);
        
        //downloadStationImages(ctx);
     
    }   
}