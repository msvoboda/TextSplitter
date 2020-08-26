package org.tieto.textsplitter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.tieto.textsplitter.config.Configuration;
import org.tieto.textsplitter.model.TextData;
import org.apache.commons.cli.*;
import org.apache.log4j.*;
import org.tieto.textsplitter.parser.impl.TextParserImpl;
import org.tieto.textsplitter.service.OutputWriter;
import org.tieto.textsplitter.service.OutputWriterFactory;
import org.tieto.textsplitter.service.impl.CsvWriterImpl;
import org.tieto.textsplitter.utils.ConstantUtils;

/**
 *  Main Application class
 */
public class TextSplitterApp {

    final static Logger log = LogManager.getLogger(TextSplitterApp.class);
    final static String AppName = "TextSplitter";
    final static Configuration config = new Configuration();
    static OutputWriter outputWriter = new CsvWriterImpl();

    public static void main(String[] args) {
        // INITIALIZATION
        InitLogger(AppName + ".log");
        try {
            config.readParameters(args);
        }
        catch(ParseException ex) {
            log.error(ex);
            // exit app because of wrong options
            System.exit(0);
        }

        try (Scanner scan = new Scanner(System.in)) {
            scan.useDelimiter(ConstantUtils.CHAR_EOF);
            // PARSER
            TextParserImpl textParser = new TextParserImpl();
            TextData model = textParser.parseTextFromScanner(scan);
            // WRITE OUTPUT
            try {
                outputWriter = OutputWriterFactory.getOutputWriter(config);
                outputWriter.writeOutput(model);
            }
            catch(Exception ex) {
                log.error(ex);
            }
        }

        log.info("End");
    }


    /**
     *  Logger set-up from code
     *  */
    private static void InitLogger(String logfile) {
        PatternLayout patternLayoutObj = new PatternLayout();
        try{
            SimpleDateFormat dt = new SimpleDateFormat("z");
            Date date = new Date();
            String currentTZ = dt.format(date);

            String conversionPattern = "%d ("+currentTZ+") - "+AppName+" [%p] - %m %n";
            patternLayoutObj.setConversionPattern(conversionPattern);

            // Create Daily Rolling Log File Appender
            DailyRollingFileAppender rollingAppenderObj = new DailyRollingFileAppender();
            rollingAppenderObj.setFile(logfile);
            rollingAppenderObj.setDatePattern("'.'yyyy-MM-dd");
            rollingAppenderObj.setLayout(patternLayoutObj);
            rollingAppenderObj.activateOptions();
            log.addAppender(rollingAppenderObj);
        }
        catch(Exception Ex){
            log.error("ERROR: InitLogger failed: " +Ex.getMessage());
            System.exit(0);
        }
        log.info("Start");
    }
}
