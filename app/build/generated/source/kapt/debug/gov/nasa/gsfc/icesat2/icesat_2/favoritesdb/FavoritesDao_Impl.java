package gov.nasa.gsfc.icesat2.icesat_2.favoritesdb;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FavoritesDao_Impl implements FavoritesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FavoritesEntry> __insertionAdapterOfFavoritesEntry;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllFavorites;

  public FavoritesDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavoritesEntry = new EntityInsertionAdapter<FavoritesEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `favorites_table` (`dateObjectTime`,`dateString`,`lat`,`lng`,`geocodedLocation`,`id`) VALUES (?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FavoritesEntry entity) {
        statement.bindLong(1, entity.getDateObjectTime());
        if (entity.getDateString() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDateString());
        }
        statement.bindDouble(3, entity.getLat());
        statement.bindDouble(4, entity.getLng());
        if (entity.getGeocodedLocation() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getGeocodedLocation());
        }
        statement.bindLong(6, entity.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM favorites_table WHERE dateObjectTime = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllFavorites = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM favorites_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final FavoritesEntry favoritesEntry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavoritesEntry.insert(favoritesEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final long timeKey) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, timeKey);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public void deleteAllFavorites() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllFavorites.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllFavorites.release(_stmt);
    }
  }

  @Override
  public FavoritesEntry[] contains(final long timeKey) {
    final String _sql = "SELECT * FROM favorites_table WHERE dateObjectTime = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, timeKey);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfDateObjectTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateObjectTime");
      final int _cursorIndexOfDateString = CursorUtil.getColumnIndexOrThrow(_cursor, "dateString");
      final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
      final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
      final int _cursorIndexOfGeocodedLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "geocodedLocation");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final FavoritesEntry[] _tmpResult = new FavoritesEntry[_cursor.getCount()];
      int _index = 0;
      while (_cursor.moveToNext()) {
        final FavoritesEntry _item;
        final long _tmpDateObjectTime;
        _tmpDateObjectTime = _cursor.getLong(_cursorIndexOfDateObjectTime);
        final String _tmpDateString;
        if (_cursor.isNull(_cursorIndexOfDateString)) {
          _tmpDateString = null;
        } else {
          _tmpDateString = _cursor.getString(_cursorIndexOfDateString);
        }
        final double _tmpLat;
        _tmpLat = _cursor.getDouble(_cursorIndexOfLat);
        final double _tmpLng;
        _tmpLng = _cursor.getDouble(_cursorIndexOfLng);
        final String _tmpGeocodedLocation;
        if (_cursor.isNull(_cursorIndexOfGeocodedLocation)) {
          _tmpGeocodedLocation = null;
        } else {
          _tmpGeocodedLocation = _cursor.getString(_cursorIndexOfGeocodedLocation);
        }
        _item = new FavoritesEntry(_tmpDateObjectTime,_tmpDateString,_tmpLat,_tmpLng,_tmpGeocodedLocation);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _tmpResult[_index] = _item;
        _index++;
      }
      final FavoritesEntry[] _result = _tmpResult;
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<FavoritesEntry>> getAllFavorites() {
    final String _sql = "SELECT * FROM favorites_table ORDER BY dateObjectTime";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"favorites_table"}, false, new Callable<List<FavoritesEntry>>() {
      @Override
      @Nullable
      public List<FavoritesEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDateObjectTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateObjectTime");
          final int _cursorIndexOfDateString = CursorUtil.getColumnIndexOrThrow(_cursor, "dateString");
          final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
          final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
          final int _cursorIndexOfGeocodedLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "geocodedLocation");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<FavoritesEntry> _result = new ArrayList<FavoritesEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FavoritesEntry _item;
            final long _tmpDateObjectTime;
            _tmpDateObjectTime = _cursor.getLong(_cursorIndexOfDateObjectTime);
            final String _tmpDateString;
            if (_cursor.isNull(_cursorIndexOfDateString)) {
              _tmpDateString = null;
            } else {
              _tmpDateString = _cursor.getString(_cursorIndexOfDateString);
            }
            final double _tmpLat;
            _tmpLat = _cursor.getDouble(_cursorIndexOfLat);
            final double _tmpLng;
            _tmpLng = _cursor.getDouble(_cursorIndexOfLng);
            final String _tmpGeocodedLocation;
            if (_cursor.isNull(_cursorIndexOfGeocodedLocation)) {
              _tmpGeocodedLocation = null;
            } else {
              _tmpGeocodedLocation = _cursor.getString(_cursorIndexOfGeocodedLocation);
            }
            _item = new FavoritesEntry(_tmpDateObjectTime,_tmpDateString,_tmpLat,_tmpLng,_tmpGeocodedLocation);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
