package org.putkov.tt.parser;

import org.putkov.tt.exception.AuthoserviceException;

import java.util.List;

public interface ServiceConfigParser {
        int[] parse(List<String> lines) throws AuthoserviceException;
    }


