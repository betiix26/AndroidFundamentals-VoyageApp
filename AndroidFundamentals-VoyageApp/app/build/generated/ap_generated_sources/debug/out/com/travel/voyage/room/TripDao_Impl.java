package com.travel.voyage.room;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TripDao_Impl implements TripDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Trip> __insertionAdapterOfTrip;

  private final EntityDeletionOrUpdateAdapter<Trip> __updateAdapterOfTrip;

  private final SharedSQLiteStatement __preparedStmtOfSetFavorite;

  private final SharedSQLiteStatement __preparedStmtOfRemoveFavorite;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public TripDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrip = new EntityInsertionAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Trip` (`id`,`user_id`,`name`,`destination`,`type`,`price`,`start_date`,`end_date`,`rating`,`is_favorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getDestination() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDestination());
        }
        stmt.bindLong(5, value.getType());
        stmt.bindDouble(6, value.getPrice());
        if (value.getStartDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStartDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getEndDate());
        }
        stmt.bindDouble(9, value.getRating());
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(10, _tmp);
      }
    };
    this.__updateAdapterOfTrip = new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `Trip` SET `id` = ?,`user_id` = ?,`name` = ?,`destination` = ?,`type` = ?,`price` = ?,`start_date` = ?,`end_date` = ?,`rating` = ?,`is_favorite` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getDestination() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDestination());
        }
        stmt.bindLong(5, value.getType());
        stmt.bindDouble(6, value.getPrice());
        if (value.getStartDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStartDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getEndDate());
        }
        stmt.bindDouble(9, value.getRating());
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(10, _tmp);
        stmt.bindLong(11, value.getId());
      }
    };
    this.__preparedStmtOfSetFavorite = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Trip SET is_favorite = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveFavorite = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Trip SET is_favorite = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Trip WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTrip.insertAndReturnId(trip);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTrip.handle(trip);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setFavorite(final long tripId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetFavorite.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, tripId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetFavorite.release(_stmt);
    }
  }

  @Override
  public void removeFavorite(final long tripId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveFavorite.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, tripId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveFavorite.release(_stmt);
    }
  }

  @Override
  public void delete(final long tripId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, tripId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public Trip getTrip(final long tripId) {
    final String _sql = "SELECT * FROM Trip WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tripId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDestination = CursorUtil.getColumnIndexOrThrow(_cursor, "destination");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "is_favorite");
      final Trip _result;
      if(_cursor.moveToFirst()) {
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpDestination;
        _tmpDestination = _cursor.getString(_cursorIndexOfDestination);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpStartDate;
        _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        final String _tmpEndDate;
        _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        final double _tmpRating;
        _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
        _result = new Trip(_tmpUserId,_tmpName,_tmpDestination,_tmpType,_tmpPrice,_tmpStartDate,_tmpEndDate,_tmpRating);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _result.setFavorite(_tmpIsFavorite);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Trip> getUserTrips(final long userId) {
    final String _sql = "SELECT * FROM Trip WHERE user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDestination = CursorUtil.getColumnIndexOrThrow(_cursor, "destination");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "is_favorite");
      final List<Trip> _result = new ArrayList<Trip>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Trip _item;
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpDestination;
        _tmpDestination = _cursor.getString(_cursorIndexOfDestination);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpStartDate;
        _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        final String _tmpEndDate;
        _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        final double _tmpRating;
        _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
        _item = new Trip(_tmpUserId,_tmpName,_tmpDestination,_tmpType,_tmpPrice,_tmpStartDate,_tmpEndDate,_tmpRating);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _item.setFavorite(_tmpIsFavorite);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
