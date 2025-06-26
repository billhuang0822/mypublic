package com.tsb.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "logging.aspect")
public class LogAspectProperties {
    public enum Mode {
        ENABLE_BY_DEFAULT,        
        DISABLE_BY_DEFAULT
    }

    private List<String> includePackages = new ArrayList<>();
    private List<String> excludePackages = new ArrayList<>();
    private Mode mode = Mode.ENABLE_BY_DEFAULT;

    public List<String> getIncludePackages() { return includePackages; }
    public void setIncludePackages(List<String> includePackages) { this.includePackages = includePackages; }

    public List<String> getExcludePackages() { return excludePackages; }
    public void setExcludePackages(List<String> excludePackages) { this.excludePackages = excludePackages; }

    public Mode getMode() { return mode; }
    public void setMode(Mode mode) { this.mode = mode; }
}