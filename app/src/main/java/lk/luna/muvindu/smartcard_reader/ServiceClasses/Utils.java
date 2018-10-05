package lk.luna.muvindu.smartcard_reader.ServiceClasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public String currentTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(cal.getTime());
    }

    public String currentDate(){

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        try {
            Date todayWithZeroTime = formatter.parse(formatter.format(today));
            return  todayWithZeroTime.toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }



    }
}
