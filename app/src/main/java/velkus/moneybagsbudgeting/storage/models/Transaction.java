package velkus.moneybagsbudgeting.storage.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

@Entity(foreignKeys = @ForeignKey(entity = Account.class,
                                    parentColumns = "id",
                                    childColumns = "accountId"))
public class Transaction {

    public Transaction() {
    }

    public Transaction(Double amount, Type type, Long accountId) {
        this.amount = amount;
        setType(type);
        this.accountId = accountId;
    }

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Date date;

    public Double amount;

    public int typeOrdinal;

    public Long accountId;

    public Type getType() {
        return Type.values()[typeOrdinal];
    }

    public void setType(Type type) {
        typeOrdinal = type.ordinal();
    }

    @Override
    public String toString() {
        String currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return currencySymbol + " " + df.format(amount);
    }

    public enum Type {
        WITHDRAW,
        DEPOSIT,
        TRANSFER
    }
}

