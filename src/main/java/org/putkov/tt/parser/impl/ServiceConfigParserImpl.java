package org.putkov.tt.parser.impl;

import org.putkov.tt.exception.AuthoserviceException;
import org.putkov.tt.parser.ServiceConfigParser;

import java.util.List;

public class ServiceConfigParserImpl implements ServiceConfigParser {
    @Override
    public int[] parse(List<String> lines) throws AuthoserviceException {
        int cars = -1, boxes = -1, parts = -1;
        for(String line : lines){
            System.out.println("Parsing line: " + line);
            if(!line.contains("=")){
                throw new AuthoserviceException("Invalid config line: " + line);
            }
            String[] tokens = line.split("=");
            if (tokens.length != 2) {
                throw new AuthoserviceException("Malformed config line: " + line);
            }
            String key = tokens[0].strip();
            String value = tokens[1].strip();
            try {
            int parsedValue = Integer.parseInt(value);
            switch (key){
                case "cars":
                    cars = parsedValue;
                    break;
                case "boxes":
                    boxes = parsedValue;
                    break;
                case "parts":
                    parts = parsedValue;
                    break;
                default: throw new AuthoserviceException("Unknown config key: " + key);
            }
        }catch (AuthoserviceException e){
                throw new AuthoserviceException("Invalid number for key " + key + ": " + value, e);
            }
        }
        if (cars < 0 || boxes < 0 || parts < 0) {
            throw new AuthoserviceException("Missing required config values (cars, boxes, parts)");
        }
        return new int[]{cars, boxes, parts};
    }
}
