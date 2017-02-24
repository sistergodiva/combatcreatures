package se.snrn.combatcreatures.userinterface;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GameLog {

    public static ArrayList<String> logs = new ArrayList<>();
    private static DateTimeFormatter dtf;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss ");

    public GameLog() {
        addMessage("1");
        addMessage("1");
        addMessage("1");
        addMessage("1");
        dtf = DateTimeFormatter.ofPattern("HH:mm:SS");
    }

    public static List<String> getLastMessages(int numberOfMessages) {
        return logs.subList((logs.size() - numberOfMessages), logs.size());

    }

    public static void addMessage(String message) {
        String timeStamp = simpleDateFormat.format(Calendar.getInstance().getTime());
        message = timeStamp + message;
        logs.add(message);
        while (logs.size() > 4) {
            logs.remove(0);
        }
    }
}
