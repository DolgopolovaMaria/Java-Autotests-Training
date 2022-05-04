package SimpleTests.Pages.Components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(String day, String month, String year){
        String month_case = month.substring(0, 1) + month.substring(1).toLowerCase();
        $(".react-datepicker__month-select").selectOption(month_case);
        $(".react-datepicker__year-select").selectOption(year);
        if (day.length() == 1){
            day = "0" + day;
        }
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();
    }
}
