package wrappers;


import static com.codeborne.selenide.Selenide.$x;

public class ProseMirror {
    String label;
    String caseLocator = "//label[text()='%s']/following-sibling::div//div[contains(@class, 'ProseMirror')]//p";
    String suiteLocator = "//label[text()='%s']/ancestor::div[2]//p[@class]";

    public ProseMirror(String label) {
        this.label = label;
    }

    public void writeCase(String text) {
        $x((String.format(caseLocator, label))).click();
        $x((String.format(caseLocator, label))).clear();
        $x((String.format(caseLocator, label))).setValue(text);
    }

    public void writeSuite(String text) {
        $x((String.format(suiteLocator, label))).click();
        $x((String.format(suiteLocator, label))).clear();
        $x((String.format(suiteLocator, label))).setValue(text);
    }
}
