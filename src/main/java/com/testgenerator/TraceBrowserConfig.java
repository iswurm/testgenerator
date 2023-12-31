package com.testgenerator;

import io.github.uchagani.jp.BrowserConfig;
import io.github.uchagani.jp.PlaywrightBrowserConfig;

public class TraceBrowserConfig implements PlaywrightBrowserConfig {
    @Override
    public BrowserConfig getBrowserConfig() {
        return new BrowserConfig()
                .chromium()
                .launch()
                .enableTracing()
                .enableTracingOnlyOnFailure();
    }
}