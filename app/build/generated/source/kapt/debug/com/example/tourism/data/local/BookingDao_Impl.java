package com.example.tourism.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.tourism.data.model.Booking;
import com.example.tourism.data.model.BookingStatus;
import com.example.tourism.data.model.PaymentStatus;
import com.example.tourism.data.model.ServiceType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BookingDao_Impl implements BookingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Booking> __insertionAdapterOfBooking;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Booking> __deletionAdapterOfBooking;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBookingStatus;

  public BookingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBooking = new EntityInsertionAdapter<Booking>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `bookings` (`id`,`userId`,`serviceId`,`serviceType`,`bookingDate`,`travelDate`,`returnDate`,`numberOfPeople`,`totalAmount`,`status`,`paymentStatus`,`specialRequirements`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Booking entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getServiceId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getServiceId());
        }
        final String _tmp = __converters.fromServiceType(entity.getServiceType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final Long _tmp_1 = __converters.dateToTimestamp(entity.getBookingDate());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp_1);
        }
        final Long _tmp_2 = __converters.dateToTimestamp(entity.getTravelDate());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_2);
        }
        final Long _tmp_3 = __converters.dateToTimestamp(entity.getReturnDate());
        if (_tmp_3 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_3);
        }
        statement.bindLong(8, entity.getNumberOfPeople());
        statement.bindDouble(9, entity.getTotalAmount());
        final String _tmp_4 = __converters.fromBookingStatus(entity.getStatus());
        if (_tmp_4 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp_4);
        }
        final String _tmp_5 = __converters.fromPaymentStatus(entity.getPaymentStatus());
        if (_tmp_5 == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp_5);
        }
        if (entity.getSpecialRequirements() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getSpecialRequirements());
        }
      }
    };
    this.__deletionAdapterOfBooking = new EntityDeletionOrUpdateAdapter<Booking>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `bookings` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Booking entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateBookingStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE bookings SET status = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertBooking(final Booking booking, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBooking.insert(booking);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBooking(final Booking booking, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBooking.handle(booking);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBookingStatus(final String id, final BookingStatus status,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBookingStatus.acquire();
        int _argIndex = 1;
        final String _tmp = __converters.fromBookingStatus(status);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateBookingStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Booking>> getAllBookings() {
    final String _sql = "SELECT * FROM bookings ORDER BY bookingDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookings"}, new Callable<List<Booking>>() {
      @Override
      @NonNull
      public List<Booking> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceId");
          final int _cursorIndexOfServiceType = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceType");
          final int _cursorIndexOfBookingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "bookingDate");
          final int _cursorIndexOfTravelDate = CursorUtil.getColumnIndexOrThrow(_cursor, "travelDate");
          final int _cursorIndexOfReturnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "returnDate");
          final int _cursorIndexOfNumberOfPeople = CursorUtil.getColumnIndexOrThrow(_cursor, "numberOfPeople");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentStatus");
          final int _cursorIndexOfSpecialRequirements = CursorUtil.getColumnIndexOrThrow(_cursor, "specialRequirements");
          final List<Booking> _result = new ArrayList<Booking>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Booking _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpServiceId;
            if (_cursor.isNull(_cursorIndexOfServiceId)) {
              _tmpServiceId = null;
            } else {
              _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
            }
            final ServiceType _tmpServiceType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfServiceType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfServiceType);
            }
            _tmpServiceType = __converters.toServiceType(_tmp);
            final Date _tmpBookingDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfBookingDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfBookingDate);
            }
            _tmpBookingDate = __converters.fromTimestamp(_tmp_1);
            final Date _tmpTravelDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfTravelDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfTravelDate);
            }
            _tmpTravelDate = __converters.fromTimestamp(_tmp_2);
            final Date _tmpReturnDate;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfReturnDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfReturnDate);
            }
            _tmpReturnDate = __converters.fromTimestamp(_tmp_3);
            final int _tmpNumberOfPeople;
            _tmpNumberOfPeople = _cursor.getInt(_cursorIndexOfNumberOfPeople);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final BookingStatus _tmpStatus;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toBookingStatus(_tmp_4);
            final PaymentStatus _tmpPaymentStatus;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfPaymentStatus)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfPaymentStatus);
            }
            _tmpPaymentStatus = __converters.toPaymentStatus(_tmp_5);
            final String _tmpSpecialRequirements;
            if (_cursor.isNull(_cursorIndexOfSpecialRequirements)) {
              _tmpSpecialRequirements = null;
            } else {
              _tmpSpecialRequirements = _cursor.getString(_cursorIndexOfSpecialRequirements);
            }
            _item = new Booking(_tmpId,_tmpUserId,_tmpServiceId,_tmpServiceType,_tmpBookingDate,_tmpTravelDate,_tmpReturnDate,_tmpNumberOfPeople,_tmpTotalAmount,_tmpStatus,_tmpPaymentStatus,_tmpSpecialRequirements);
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
  public Flow<List<Booking>> getBookingsByUserId(final String userId) {
    final String _sql = "SELECT * FROM bookings WHERE userId = ? ORDER BY bookingDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookings"}, new Callable<List<Booking>>() {
      @Override
      @NonNull
      public List<Booking> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceId");
          final int _cursorIndexOfServiceType = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceType");
          final int _cursorIndexOfBookingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "bookingDate");
          final int _cursorIndexOfTravelDate = CursorUtil.getColumnIndexOrThrow(_cursor, "travelDate");
          final int _cursorIndexOfReturnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "returnDate");
          final int _cursorIndexOfNumberOfPeople = CursorUtil.getColumnIndexOrThrow(_cursor, "numberOfPeople");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentStatus");
          final int _cursorIndexOfSpecialRequirements = CursorUtil.getColumnIndexOrThrow(_cursor, "specialRequirements");
          final List<Booking> _result = new ArrayList<Booking>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Booking _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpServiceId;
            if (_cursor.isNull(_cursorIndexOfServiceId)) {
              _tmpServiceId = null;
            } else {
              _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
            }
            final ServiceType _tmpServiceType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfServiceType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfServiceType);
            }
            _tmpServiceType = __converters.toServiceType(_tmp);
            final Date _tmpBookingDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfBookingDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfBookingDate);
            }
            _tmpBookingDate = __converters.fromTimestamp(_tmp_1);
            final Date _tmpTravelDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfTravelDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfTravelDate);
            }
            _tmpTravelDate = __converters.fromTimestamp(_tmp_2);
            final Date _tmpReturnDate;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfReturnDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfReturnDate);
            }
            _tmpReturnDate = __converters.fromTimestamp(_tmp_3);
            final int _tmpNumberOfPeople;
            _tmpNumberOfPeople = _cursor.getInt(_cursorIndexOfNumberOfPeople);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final BookingStatus _tmpStatus;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toBookingStatus(_tmp_4);
            final PaymentStatus _tmpPaymentStatus;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfPaymentStatus)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfPaymentStatus);
            }
            _tmpPaymentStatus = __converters.toPaymentStatus(_tmp_5);
            final String _tmpSpecialRequirements;
            if (_cursor.isNull(_cursorIndexOfSpecialRequirements)) {
              _tmpSpecialRequirements = null;
            } else {
              _tmpSpecialRequirements = _cursor.getString(_cursorIndexOfSpecialRequirements);
            }
            _item = new Booking(_tmpId,_tmpUserId,_tmpServiceId,_tmpServiceType,_tmpBookingDate,_tmpTravelDate,_tmpReturnDate,_tmpNumberOfPeople,_tmpTotalAmount,_tmpStatus,_tmpPaymentStatus,_tmpSpecialRequirements);
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
