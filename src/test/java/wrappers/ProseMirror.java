package wrappers;

import static com.codeborne.selenide.Selenide.$x;

public class ProseMirror {
    String label;
    String locator = "//label[text()='%s']/following-sibling::div//div[contains(@class, 'ProseMirror')]//p";

    public ProseMirror(String label) {
        this.label = label;
    }

    public void write(String text) {
        $x((String.format(locator, label))).click();
        $x((String.format(locator, label))).clear();
        $x((String.format(locator, label))).setValue(text);
    }
}
