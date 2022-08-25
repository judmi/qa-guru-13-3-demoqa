package pages.components;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {
    public void checkResult (String key, String value) {
        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(value));
    }

    public void checkResultArr (String key, String[] value) {
        String result = value [0];

        for (int i = 1; i < value.length; i++) {
            result += ", " +value[i];
        }

        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(result));
    }
}
