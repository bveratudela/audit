package com.cloudera.flume;

import java.util.*;
import java.util.regex.*;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.FlumeException;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.LoggerFactory;

public class SyslogInterceptor
implements Interceptor {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SyslogInterceptor.class);

    @Override
    public void initialize() {
    }

    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody());
        Map headers = event.getHeaders();
        Map tokens = parseSshSyslog(body);
        logger.info("Adding to event: " + headers.get("host") + ", " + headers.get("timestamp"));
        String newBody = new String(headers.get("host") + "|" + tokens.get("username") + "|" + tokens.get("source") + "|" + headers.get("timestamp"));
        event.setBody(newBody.getBytes());
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List interceptedEvents = new ArrayList(events.size());
        for (Event event : events) {
            Event interceptedEvent = intercept(event);
            interceptedEvents.add(interceptedEvent);
            logger.info("Adding intercepted event " + interceptedEvent);
        }
 
        return interceptedEvents;
    }

    @Override
    public void close() {
    }

    private Map<String, String> parseSshSyslog(String line) {
        Map<String, String> tokens = new HashMap<String, String>();
        try {
            Pattern pattern = Pattern.compile("(.*)\\[([^\\]]+)\\]: pam_unix\\(sshd:session\\): session opened for user (.*) by \\(uid=(\\d+)\\)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                tokens.put("source", "sshd");
                tokens.put("username", matcher.group(3));
            }
        } catch (Exception e) {
            logger.warn("SSH syslog message did not match pattern: " + line);
            tokens.put("source", "N/A");
            tokens.put("username", "N/A");
        }
        return tokens;
    }

    private Map<String, String> parseCentrifySyslog(String line) {
        Map<String, String> tokens = new HashMap<String, String>();
        try {
            Pattern pattern = Pattern.compile("(.*)\\[([^\\]]+)\\]: (.*)  [^|]*\\|[^|]*\\|[^|]*\\|[^|]*\\|[^|]*\\|[^|]*\\|[^|]*\\|(.*) pid=(\\d+) utc=(\\d+) centrifyEventID=(\\d+) status=(.*) service=(.*) tty=(.*) client=(\\d+(?:\\.\\d+){3})");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String userParts[] = matcher.group(4).split("[\\(\\)=,]+");
                tokens.put("source", "centrify");
                tokens.put("username", userParts[3]);
            }
        } catch (Exception e) {
            logger.warn("Centrify syslog message did not match pattern: " + line);
            tokens.put("source", "N/A");
            tokens.put("username", "N/A");
        }
        return tokens;
    }

    public static class Builder
    implements Interceptor.Builder {
        @Override
        public void configure(Context context) {
        }

        @Override
        public Interceptor build() {
            return new SyslogInterceptor();
        }
    }
}
