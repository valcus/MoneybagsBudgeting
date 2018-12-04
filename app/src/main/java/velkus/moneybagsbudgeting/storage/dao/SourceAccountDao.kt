package velkus.moneybagsbudgeting.storage.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import velkus.moneybagsbudgeting.storage.models.SourceAccountWithData

@Dao
interface SourceAccountDao {

    @get:Query("SELECT * FROM sourceAccount")
    val allAccountsWithData: LiveData<List<SourceAccountWithData>>
}