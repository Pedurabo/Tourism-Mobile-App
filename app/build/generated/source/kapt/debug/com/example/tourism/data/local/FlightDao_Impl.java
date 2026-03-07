package com.example.tourism.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.tourism.data.model.Flight;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FlightDao_Impl implements FlightDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Flight> __insertionAdapterOfFlight;

  private final EntityDeletionOrUpdateAdapter<Flight> __deletionAdapterOfFlight;

  public FlightDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFlight = new EntityInsertionAdapter<Flight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `flights` (`id`,`airline`,`flightNumber`,`departureCity`,`arrivalCity`,`departureTime`,`arrivalTime`,`price`,`imageUrl`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Flight entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getAirline() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAirline());
        }
        if (entity.getFlightNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFlightNumber());
        }
        if (entity.getDepartureCity() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDepartureCity());
        }
        if (entity.getArrivalCity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getArrivalCity());
        }
        if (entity.getDepartureTime() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDepartureTime());
        }
        if (entity.getArrivalTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getArrivalTime());
        }
        statement.bindDouble(8, entity.getPrice());
        if (entity.getImageUrl() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImageUrl());
        }
      }
    };
    this.__deletionAdapterOfFlight = new EntityDeletionOrUpdateAdapter<Flight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `flights` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Flight entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertFlights(final List<Flight> flights,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFlight.insert(flights);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFlight(final Flight flight, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFlight.handle(flight);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Flight>> getAllFlights() {
    final String _sql = "SELECT * FROM flights";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"flights"}, new Callable<List<Flight>>() {
      @Override
      @NonNull
      public List<Flight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAirline = CursorUtil.getColumnIndexOrThrow(_cursor, "airline");
          final int _cursorIndexOfFlightNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "flightNumber");
          final int _cursorIndexOfDepartureCity = CursorUtil.getColumnIndexOrThrow(_cursor, "departureCity");
          final int _cursorIndexOfArrivalCity = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalCity");
          final int _cursorIndexOfDepartureTime = CursorUtil.getColumnIndexOrThrow(_cursor, "departureTime");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalTime");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final List<Flight> _result = new ArrayList<Flight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Flight _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpAirline;
            if (_cursor.isNull(_cursorIndexOfAirline)) {
              _tmpAirline = null;
            } else {
              _tmpAirline = _cursor.getString(_cursorIndexOfAirline);
            }
            final String _tmpFlightNumber;
            if (_cursor.isNull(_cursorIndexOfFlightNumber)) {
              _tmpFlightNumber = null;
            } else {
              _tmpFlightNumber = _cursor.getString(_cursorIndexOfFlightNumber);
            }
            final String _tmpDepartureCity;
            if (_cursor.isNull(_cursorIndexOfDepartureCity)) {
              _tmpDepartureCity = null;
            } else {
              _tmpDepartureCity = _cursor.getString(_cursorIndexOfDepartureCity);
            }
            final String _tmpArrivalCity;
            if (_cursor.isNull(_cursorIndexOfArrivalCity)) {
              _tmpArrivalCity = null;
            } else {
              _tmpArrivalCity = _cursor.getString(_cursorIndexOfArrivalCity);
            }
            final String _tmpDepartureTime;
            if (_cursor.isNull(_cursorIndexOfDepartureTime)) {
              _tmpDepartureTime = null;
            } else {
              _tmpDepartureTime = _cursor.getString(_cursorIndexOfDepartureTime);
            }
            final String _tmpArrivalTime;
            if (_cursor.isNull(_cursorIndexOfArrivalTime)) {
              _tmpArrivalTime = null;
            } else {
              _tmpArrivalTime = _cursor.getString(_cursorIndexOfArrivalTime);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            _item = new Flight(_tmpId,_tmpAirline,_tmpFlightNumber,_tmpDepartureCity,_tmpArrivalCity,_tmpDepartureTime,_tmpArrivalTime,_tmpPrice,_tmpImageUrl);
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

  @Override
  public Object getFlightById(final String flightId,
      final Continuation<? super Flight> $completion) {
    final String _sql = "SELECT * FROM flights WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (flightId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, flightId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Flight>() {
      @Override
      @Nullable
      public Flight call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAirline = CursorUtil.getColumnIndexOrThrow(_cursor, "airline");
          final int _cursorIndexOfFlightNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "flightNumber");
          final int _cursorIndexOfDepartureCity = CursorUtil.getColumnIndexOrThrow(_cursor, "departureCity");
          final int _cursorIndexOfArrivalCity = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalCity");
          final int _cursorIndexOfDepartureTime = CursorUtil.getColumnIndexOrThrow(_cursor, "departureTime");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalTime");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final Flight _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpAirline;
            if (_cursor.isNull(_cursorIndexOfAirline)) {
              _tmpAirline = null;
            } else {
              _tmpAirline = _cursor.getString(_cursorIndexOfAirline);
            }
            final String _tmpFlightNumber;
            if (_cursor.isNull(_cursorIndexOfFlightNumber)) {
              _tmpFlightNumber = null;
            } else {
              _tmpFlightNumber = _cursor.getString(_cursorIndexOfFlightNumber);
            }
            final String _tmpDepartureCity;
            if (_cursor.isNull(_cursorIndexOfDepartureCity)) {
              _tmpDepartureCity = null;
            } else {
              _tmpDepartureCity = _cursor.getString(_cursorIndexOfDepartureCity);
            }
            final String _tmpArrivalCity;
            if (_cursor.isNull(_cursorIndexOfArrivalCity)) {
              _tmpArrivalCity = null;
            } else {
              _tmpArrivalCity = _cursor.getString(_cursorIndexOfArrivalCity);
            }
            final String _tmpDepartureTime;
            if (_cursor.isNull(_cursorIndexOfDepartureTime)) {
              _tmpDepartureTime = null;
            } else {
              _tmpDepartureTime = _cursor.getString(_cursorIndexOfDepartureTime);
            }
            final String _tmpArrivalTime;
            if (_cursor.isNull(_cursorIndexOfArrivalTime)) {
              _tmpArrivalTime = null;
            } else {
              _tmpArrivalTime = _cursor.getString(_cursorIndexOfArrivalTime);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            _result = new Flight(_tmpId,_tmpAirline,_tmpFlightNumber,_tmpDepartureCity,_tmpArrivalCity,_tmpDepartureTime,_tmpArrivalTime,_tmpPrice,_tmpImageUrl);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
