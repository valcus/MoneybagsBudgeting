package velkus.moneybagsbudgeting.storage.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Account.class,
                                    parentColumns = "id",
                                    childColumns = "sourceId"))
public class Account {

    public Account(String name, Long sourceId) {
        this.name = name;
        this.sourceId = sourceId;
    }

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String name;

    public Long sourceId;
}