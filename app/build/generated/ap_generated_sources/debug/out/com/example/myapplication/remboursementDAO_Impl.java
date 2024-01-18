package com.example.myapplication;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class remboursementDAO_Impl implements remboursementDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<remboursement> __insertionAdapterOfremboursement;

  private final EntityDeletionOrUpdateAdapter<remboursement> __deletionAdapterOfremboursement;

  private final EntityDeletionOrUpdateAdapter<remboursement> __updateAdapterOfremboursement;

  public remboursementDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfremboursement = new EntityInsertionAdapter<remboursement>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `remboursement` (`id`,`nom`,`prix`,`quantite`,`description`,`etat`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, remboursement value) {
        stmt.bindLong(1, value.id);
        if (value.nom == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.nom);
        }
        stmt.bindLong(3, value.prix);
        stmt.bindLong(4, value.quantite);
        if (value.description == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.description);
        }
        if (value.etat == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.etat);
        }
      }
    };
    this.__deletionAdapterOfremboursement = new EntityDeletionOrUpdateAdapter<remboursement>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `remboursement` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, remboursement value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfremboursement = new EntityDeletionOrUpdateAdapter<remboursement>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `remboursement` SET `id` = ?,`nom` = ?,`prix` = ?,`quantite` = ?,`description` = ?,`etat` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, remboursement value) {
        stmt.bindLong(1, value.id);
        if (value.nom == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.nom);
        }
        stmt.bindLong(3, value.prix);
        stmt.bindLong(4, value.quantite);
        if (value.description == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.description);
        }
        if (value.etat == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.etat);
        }
        stmt.bindLong(7, value.id);
      }
    };
  }

  @Override
  public void insertRemboursement(final remboursement r) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfremboursement.insert(r);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteRemboursement(final remboursement r) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfremboursement.handle(r);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateRemboursementEtat(final remboursement r) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfremboursement.handle(r);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<remboursement> getAllRemboursements() {
    final String _sql = "SELECT * FROM remboursement";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
      final int _cursorIndexOfPrix = CursorUtil.getColumnIndexOrThrow(_cursor, "prix");
      final int _cursorIndexOfQuantite = CursorUtil.getColumnIndexOrThrow(_cursor, "quantite");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfEtat = CursorUtil.getColumnIndexOrThrow(_cursor, "etat");
      final List<remboursement> _result = new ArrayList<remboursement>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final remboursement _item;
        _item = new remboursement();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfNom)) {
          _item.nom = null;
        } else {
          _item.nom = _cursor.getString(_cursorIndexOfNom);
        }
        _item.prix = _cursor.getInt(_cursorIndexOfPrix);
        _item.quantite = _cursor.getInt(_cursorIndexOfQuantite);
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _item.description = null;
        } else {
          _item.description = _cursor.getString(_cursorIndexOfDescription);
        }
        if (_cursor.isNull(_cursorIndexOfEtat)) {
          _item.etat = null;
        } else {
          _item.etat = _cursor.getString(_cursorIndexOfEtat);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
