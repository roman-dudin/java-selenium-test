package pages;

import framework.Browser;

abstract public class BasePage {

    protected Browser browser;

    public BasePage() {
        browser = Browser.getCurrentInstance();
    }
}
