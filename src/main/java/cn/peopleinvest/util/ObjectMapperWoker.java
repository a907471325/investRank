package cn.peopleinvest.util;


import org.codehaus.jackson.map.ObjectMapper;

public class ObjectMapperWoker {

    private ObjectMapperWoker() {

    }

    private static class objectMapperFactory{
        private static final ObjectMapper mapper = new ObjectMapper();
    }

    public static ObjectMapper getInstance(){
        return objectMapperFactory.mapper;
    }
}
