package kunong.android.library.database;

import java.util.ArrayList;

public class DBTableProperty {
	private String mTableName;
	private ArrayList<DBField> mPrimaryKeyList;
	private ArrayList<DBField> mFieldList;
	private String[] mPrimaryKeysName;
	private String[] mFieldsName;

	public ArrayList<DBField> getPrimaryKeyList() {
		return mPrimaryKeyList;
	}

	public void setPrimaryKeyList(ArrayList<DBField> primaryKeyList) {
		mPrimaryKeyList = primaryKeyList;
	}

	public ArrayList<DBField> getFieldList() {
		return mFieldList;
	}

	public void setFieldList(ArrayList<DBField> fieldList) {
		mFieldList = fieldList;
	}

	public String[] getPrimaryKeysName() {
		return mPrimaryKeysName;
	}

	public void setPrimaryKeysName(String[] primaryKeysName) {
		mPrimaryKeysName = primaryKeysName;
	}

	public String[] getFieldsName() {
		return mFieldsName;
	}

	public void setFieldsName(String[] fieldsName) {
		mFieldsName = fieldsName;
	}

	public String getTableName() {
		return mTableName;
	}

	public void setTableName(String tableName) {
		mTableName = tableName;
	}

}
