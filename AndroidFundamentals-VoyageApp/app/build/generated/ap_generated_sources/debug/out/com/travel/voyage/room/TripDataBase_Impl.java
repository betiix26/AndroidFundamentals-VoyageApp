package com.travel.voyage.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TripDataBase_Impl extends TripDataBase {
  private volatile TripDao _tripDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Trip` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `user_id` INTEGER NOT NULL, `name` TEXT, `destination` TEXT, `type` INTEGER NOT NULL, `price` REAL NOT NULL, `start_date` TEXT, `end_date` TEXT, `rating` REAL NOT NULL, `is_favorite` INTEGER NOT NULL)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_Trip_user_id` ON `Trip` (`user_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '02d346c98ab0eeb737c591e35eca258f')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Trip`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTrip = new HashMap<String, TableInfo.Column>(10);
        _columnsTrip.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("destination", new TableInfo.Column("destination", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("start_date", new TableInfo.Column("start_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("end_date", new TableInfo.Column("end_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("rating", new TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrip.put("is_favorite", new TableInfo.Column("is_favorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrip = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrip = new HashSet<TableInfo.Index>(1);
        _indicesTrip.add(new TableInfo.Index("index_Trip_user_id", false, Arrays.asList("user_id")));
        final TableInfo _infoTrip = new TableInfo("Trip", _columnsTrip, _foreignKeysTrip, _indicesTrip);
        final TableInfo _existingTrip = TableInfo.read(_db, "Trip");
        if (! _infoTrip.equals(_existingTrip)) {
          return new RoomOpenHelper.ValidationResult(false, "Trip(com.travel.voyage.room.Trip).\n"
                  + " Expected:\n" + _infoTrip + "\n"
                  + " Found:\n" + _existingTrip);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "02d346c98ab0eeb737c591e35eca258f", "123a674b54da3eaf80a884ef2844cb8a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Trip");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Trip`");
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
  public TripDao getTripDao() {
    if (_tripDao != null) {
      return _tripDao;
    } else {
      synchronized(this) {
        if(_tripDao == null) {
          _tripDao = new TripDao_Impl(this);
        }
        return _tripDao;
      }
    }
  }
}
