package org.tieto.textsplitter.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.cli.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class responsible for options and configuration
 */
public class Configuration {
    final static Logger log = LogManager.getLogger(Configuration.class);

    @Getter
    @Setter
    String format;

    /**
     * Read parameters from std args
     */
    public Configuration readParameters(String[] args) throws ParseException {
        /// process parameters
        Configuration config = new Configuration();
        Options options = new Options();
        options.addOption("f", "format", true, "Provides outputformat.");
        CommandLineParser parser = (CommandLineParser) new DefaultParser();

        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption("format")) {
                format = commandLine.getOptionValue("format");
            }
        } catch (org.apache.commons.cli.ParseException e) {
            log.error("Unable to parse command line.", e);
            new HelpFormatter().printHelp("Help", options);
            throw(e);
        }
        ///
        return config;
    }
}
