package com.dylibso.chicory.compiler.internal;

import com.dylibso.chicory.dwarf.DebugInfo.MappedLine;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Smap {

    public String generate(String outputFileName, List<MappedLine> mappedLines) {

        var linesByPath = new LinkedHashMap<String, ArrayList<MappedLine>>();
        for (MappedLine line : mappedLines) {
            String path = line.entry().path();
            linesByPath.computeIfAbsent(path, k -> new ArrayList<>()).add(line);
        }

        SmapGenerator g = new SmapGenerator();
        g.setOutputFileName(outputFileName);
        SmapStratum stratum = null;
        for (var entry : linesByPath.entrySet()) {

            ArrayList<MappedLine> lines = entry.getValue();

            // allocate a new stratum if the language changes
            var mappedEntry = lines.get(0).entry();
            String language = mappedEntry.language().value();
            if (stratum == null || !language.equals(stratum.getStratumName())) {
                if (stratum != null) {
                    g.addStratum(stratum, true);
                }
                stratum = new SmapStratum(language);
            }

            stratum.addFile(mappedEntry.file(), mappedEntry.path());
            for (MappedLine line : lines) {
                stratum.addLineData(line.entry().line(), mappedEntry.path(), 1, line.line(), 1);
            }
        }
        g.addStratum(stratum, true);

        return g.toString();
    }
}
