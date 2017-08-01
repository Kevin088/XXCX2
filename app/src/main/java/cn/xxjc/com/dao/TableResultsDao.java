package cn.xxjc.com.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.xxjc.com.bean.TableResults;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TABLE_RESULTS".
*/
public class TableResultsDao extends AbstractDao<TableResults, Long> {

    public static final String TABLENAME = "TABLE_RESULTS";

    /**
     * Properties of entity TableResults.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Zhanming = new Property(1, String.class, "zhanming", false, "ZHANMING");
        public final static Property Yunxingbianhao = new Property(2, String.class, "yunxingbianhao", false, "YUNXINGBIANHAO");
        public final static Property Shiyandanwei = new Property(3, String.class, "shiyandanwei", false, "SHIYANDANWEI");
        public final static Property Shiyanxingzhi = new Property(4, String.class, "shiyanxingzhi", false, "SHIYANXINGZHI");
        public final static Property TestTime = new Property(5, java.util.Date.class, "testTime", false, "TEST_TIME");
        public final static Property State = new Property(6, int.class, "state", false, "STATE");
    }


    public TableResultsDao(DaoConfig config) {
        super(config);
    }
    
    public TableResultsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TABLE_RESULTS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ZHANMING\" TEXT," + // 1: zhanming
                "\"YUNXINGBIANHAO\" TEXT," + // 2: yunxingbianhao
                "\"SHIYANDANWEI\" TEXT," + // 3: shiyandanwei
                "\"SHIYANXINGZHI\" TEXT," + // 4: shiyanxingzhi
                "\"TEST_TIME\" INTEGER," + // 5: testTime
                "\"STATE\" INTEGER NOT NULL );"); // 6: state
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TABLE_RESULTS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TableResults entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String zhanming = entity.getZhanming();
        if (zhanming != null) {
            stmt.bindString(2, zhanming);
        }
 
        String yunxingbianhao = entity.getYunxingbianhao();
        if (yunxingbianhao != null) {
            stmt.bindString(3, yunxingbianhao);
        }
 
        String shiyandanwei = entity.getShiyandanwei();
        if (shiyandanwei != null) {
            stmt.bindString(4, shiyandanwei);
        }
 
        String shiyanxingzhi = entity.getShiyanxingzhi();
        if (shiyanxingzhi != null) {
            stmt.bindString(5, shiyanxingzhi);
        }
 
        java.util.Date testTime = entity.getTestTime();
        if (testTime != null) {
            stmt.bindLong(6, testTime.getTime());
        }
        stmt.bindLong(7, entity.getState());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TableResults entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String zhanming = entity.getZhanming();
        if (zhanming != null) {
            stmt.bindString(2, zhanming);
        }
 
        String yunxingbianhao = entity.getYunxingbianhao();
        if (yunxingbianhao != null) {
            stmt.bindString(3, yunxingbianhao);
        }
 
        String shiyandanwei = entity.getShiyandanwei();
        if (shiyandanwei != null) {
            stmt.bindString(4, shiyandanwei);
        }
 
        String shiyanxingzhi = entity.getShiyanxingzhi();
        if (shiyanxingzhi != null) {
            stmt.bindString(5, shiyanxingzhi);
        }
 
        java.util.Date testTime = entity.getTestTime();
        if (testTime != null) {
            stmt.bindLong(6, testTime.getTime());
        }
        stmt.bindLong(7, entity.getState());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TableResults readEntity(Cursor cursor, int offset) {
        TableResults entity = new TableResults( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // zhanming
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // yunxingbianhao
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // shiyandanwei
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // shiyanxingzhi
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // testTime
            cursor.getInt(offset + 6) // state
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TableResults entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setZhanming(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setYunxingbianhao(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setShiyandanwei(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setShiyanxingzhi(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTestTime(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setState(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TableResults entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TableResults entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TableResults entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}