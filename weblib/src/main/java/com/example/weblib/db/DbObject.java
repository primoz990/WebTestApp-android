package com.example.weblib.db;

public abstract class DbObject {

    protected static final String TYPE_TEXT = " TEXT";
    protected static final String TYPE_INTEGER = " INTEGER";
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final String APOSTROPHE = "'";

    public abstract String[] getColumns();

    public abstract String[] getValues();

    public abstract String[] getTypes();

    public String getSqlTableName() {
        return super.getClass().getSimpleName();
    }

    protected String toSqlString(String string) {
        return (string == null) ? APOSTROPHE + APOSTROPHE : APOSTROPHE + string + APOSTROPHE;
    }

    protected String generateSqlInsert(String[] columns, String[] values) {
        if (columns.length == values.length && columns.length > 0) {
            String strColumns = "", strValues = "";
            for (int i = 0; i < columns.length; i++) {
                strColumns += columns[i];
                strValues += values[i];
                if (i < columns.length - 1) {
                    strColumns += COMMA + SPACE;
                    strValues += COMMA + SPACE;
                }

            }
            return "INSERT INTO" + SPACE +
                    this.getSqlTableName() +
                    SPACE + "(" + strColumns + ")" + SPACE +
                    "VALUES" + SPACE + "(" + strValues + ")";
        }
        return null;
    }

    public String getSqlInsert() {
        return generateSqlInsert(getColumns(), getValues());
    }

    public String getSqlDeleteTable() {
        return "DROP TABLE IF EXISTS" + SPACE + getSqlTableName();
    }

    public String getSqlDeleteEntries() {
        return "DELETE FROM" + SPACE + getSqlTableName();
    }

    public String getSqlSelectAll() {
        return "SELECT * FROM " + getSqlTableName();
    }

    public String getSqlCreateTable() {
        String[] columns = getColumns();
        String[] types = getTypes();
        if (columns.length == types.length && columns.length > 0) {
            String create = "CREATE TABLE" + SPACE + getSqlTableName() + SPACE + "(";
            for (int i = 0; i < columns.length; i++) {
                create += columns[i] + SPACE + types[i];
                if (i < columns.length - 1) {
                    create += COMMA + SPACE;
                }
            }
            create += ")";
            return create;
        }
        return null;
    }

}
