package org.putkov.tt.reader.impl;

import org.putkov.tt.exception.AuthoserviceException;
import org.putkov.tt.reader.ServiceConfigReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceConfigReaderImpl implements ServiceConfigReader{
    @Override
    public List<String> read(String path) throws AuthoserviceException {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line.trim());
            }
        } catch (IOException e) {
            throw new AuthoserviceException(e);
        }
        return lines;
    }
}
