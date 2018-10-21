package velkus.moneybagsbudgeting.storage;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseFactory {
    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }

        synchronized (AppDatabase.class) {
            if (instance != null) {
                return instance;
            } else {
                instance = Room.databaseBuilder(context, AppDatabase.class, "moneybags.db")
                        .allowMainThreadQueries()
                        .build();
                return instance;
            }
        }
    }
}
