package org.putkov.tt.reader;

import org.putkov.tt.exception.AuthoserviceException;

import java.io.IOException;
import java.util.List;

public interface ServiceConfigReader {
    List<String> read(String path) throws AuthoserviceException;
}
