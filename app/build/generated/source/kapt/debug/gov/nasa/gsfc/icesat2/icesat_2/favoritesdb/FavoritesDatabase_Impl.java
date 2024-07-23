package gov.nasa.gsfc.icesat2.icesat_2.favoritesdb;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FavoritesDatabase_Impl extends FavoritesDatabase {
  private volatile FavoritesDao _favoritesDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `favorites_table` (`dateObjectTime` INTEGER NOT NULL, `dateString` TEXT NOT NULL, `lat` REAL NOT NULL, `lng` REAL NOT NULL, `geocodedLocation` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1b9a6135b5cdab18ba8e8861078f4d47')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `favorites_table`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsFavoritesTable = new HashMap<String, TableInfo.Column>(6);
        _columnsFavoritesTable.put("dateObjectTime", new TableInfo.Column("dateObjectTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoritesTable.put("dateString", new TableInfo.Column("dateString", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoritesTable.put("lat", new TableInfo.Column("lat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoritesTable.put("lng", new TableInfo.Column("lng", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoritesTable.put("geocodedLocation", new TableInfo.Column("geocodedLocation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoritesTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavoritesTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavoritesTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavoritesTable = new TableInfo("favorites_table", _columnsFavoritesTable, _foreignKeysFavoritesTable, _indicesFavoritesTable);
        final TableInfo _existingFavoritesTable = TableInfo.read(db, "favorites_table");
        if (!_infoFavoritesTable.equals(_existingFavoritesTable)) {
          return new RoomOpenHelper.ValidationResult(false, "favorites_table(gov.nasa.gsfc.icesat2.icesat_2.favoritesdb.FavoritesEntry).\n"
                  + " Expected:\n" + _infoFavoritesTable + "\n"
                  + " Found:\n" + _existingFavoritesTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1b9a6135b5cdab18ba8e8861078f4d47", "ce1eedbd15df04d9b1ce659a4a495bd3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "favorites_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `favorites_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(FavoritesDao.class, FavoritesDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public FavoritesDao favoritesDao() {
    if (_favoritesDao != null) {
      return _favoritesDao;
    } else {
      synchronized(this) {
        if(_favoritesDao == null) {
          _favoritesDao = new FavoritesDao_Impl(this);
        }
        return _favoritesDao;
      }
    }
  }
}
