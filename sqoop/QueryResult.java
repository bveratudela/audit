// ORM class for table 'null'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Tue Jan 29 17:02:09 GMT 2019
// For connector: org.apache.sqoop.manager.OracleManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class QueryResult extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("USERNAME", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        USERNAME = (String)value;
      }
    });
    setters.put("'HUE'", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        _HUE_ = (String)value;
      }
    });
    setters.put("LAST_LOGIN", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        LAST_LOGIN = (java.sql.Timestamp)value;
      }
    });
  }
  public QueryResult() {
    init0();
  }
  private String USERNAME;
  public String get_USERNAME() {
    return USERNAME;
  }
  public void set_USERNAME(String USERNAME) {
    this.USERNAME = USERNAME;
  }
  public QueryResult with_USERNAME(String USERNAME) {
    this.USERNAME = USERNAME;
    return this;
  }
  private String _HUE_;
  public String get__HUE_() {
    return _HUE_;
  }
  public void set__HUE_(String _HUE_) {
    this._HUE_ = _HUE_;
  }
  public QueryResult with__HUE_(String _HUE_) {
    this._HUE_ = _HUE_;
    return this;
  }
  private java.sql.Timestamp LAST_LOGIN;
  public java.sql.Timestamp get_LAST_LOGIN() {
    return LAST_LOGIN;
  }
  public void set_LAST_LOGIN(java.sql.Timestamp LAST_LOGIN) {
    this.LAST_LOGIN = LAST_LOGIN;
  }
  public QueryResult with_LAST_LOGIN(java.sql.Timestamp LAST_LOGIN) {
    this.LAST_LOGIN = LAST_LOGIN;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryResult)) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    boolean equal = true;
    equal = equal && (this.USERNAME == null ? that.USERNAME == null : this.USERNAME.equals(that.USERNAME));
    equal = equal && (this._HUE_ == null ? that._HUE_ == null : this._HUE_.equals(that._HUE_));
    equal = equal && (this.LAST_LOGIN == null ? that.LAST_LOGIN == null : this.LAST_LOGIN.equals(that.LAST_LOGIN));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryResult)) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    boolean equal = true;
    equal = equal && (this.USERNAME == null ? that.USERNAME == null : this.USERNAME.equals(that.USERNAME));
    equal = equal && (this._HUE_ == null ? that._HUE_ == null : this._HUE_.equals(that._HUE_));
    equal = equal && (this.LAST_LOGIN == null ? that.LAST_LOGIN == null : this.LAST_LOGIN.equals(that.LAST_LOGIN));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.USERNAME = JdbcWritableBridge.readString(1, __dbResults);
    this._HUE_ = JdbcWritableBridge.readString(2, __dbResults);
    this.LAST_LOGIN = JdbcWritableBridge.readTimestamp(3, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.USERNAME = JdbcWritableBridge.readString(1, __dbResults);
    this._HUE_ = JdbcWritableBridge.readString(2, __dbResults);
    this.LAST_LOGIN = JdbcWritableBridge.readTimestamp(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(USERNAME, 1 + __off, -9, __dbStmt);
    JdbcWritableBridge.writeString(_HUE_, 2 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeTimestamp(LAST_LOGIN, 3 + __off, 93, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(USERNAME, 1 + __off, -9, __dbStmt);
    JdbcWritableBridge.writeString(_HUE_, 2 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeTimestamp(LAST_LOGIN, 3 + __off, 93, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.USERNAME = null;
    } else {
    this.USERNAME = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this._HUE_ = null;
    } else {
    this._HUE_ = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.LAST_LOGIN = null;
    } else {
    this.LAST_LOGIN = new Timestamp(__dataIn.readLong());
    this.LAST_LOGIN.setNanos(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.USERNAME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, USERNAME);
    }
    if (null == this._HUE_) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, _HUE_);
    }
    if (null == this.LAST_LOGIN) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.LAST_LOGIN.getTime());
    __dataOut.writeInt(this.LAST_LOGIN.getNanos());
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.USERNAME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, USERNAME);
    }
    if (null == this._HUE_) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, _HUE_);
    }
    if (null == this.LAST_LOGIN) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.LAST_LOGIN.getTime());
    __dataOut.writeInt(this.LAST_LOGIN.getNanos());
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 124, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(USERNAME==null?"null":USERNAME, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(_HUE_==null?"null":_HUE_, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(LAST_LOGIN==null?"null":"" + LAST_LOGIN, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(USERNAME==null?"null":USERNAME, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(_HUE_==null?"null":_HUE_, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(LAST_LOGIN==null?"null":"" + LAST_LOGIN, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 124, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.USERNAME = null; } else {
      this.USERNAME = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this._HUE_ = null; } else {
      this._HUE_ = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.LAST_LOGIN = null; } else {
      this.LAST_LOGIN = java.sql.Timestamp.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.USERNAME = null; } else {
      this.USERNAME = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this._HUE_ = null; } else {
      this._HUE_ = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.LAST_LOGIN = null; } else {
      this.LAST_LOGIN = java.sql.Timestamp.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    QueryResult o = (QueryResult) super.clone();
    o.LAST_LOGIN = (o.LAST_LOGIN != null) ? (java.sql.Timestamp) o.LAST_LOGIN.clone() : null;
    return o;
  }

  public void clone0(QueryResult o) throws CloneNotSupportedException {
    o.LAST_LOGIN = (o.LAST_LOGIN != null) ? (java.sql.Timestamp) o.LAST_LOGIN.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("USERNAME", this.USERNAME);
    __sqoop$field_map.put("'HUE'", this._HUE_);
    __sqoop$field_map.put("LAST_LOGIN", this.LAST_LOGIN);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("USERNAME", this.USERNAME);
    __sqoop$field_map.put("'HUE'", this._HUE_);
    __sqoop$field_map.put("LAST_LOGIN", this.LAST_LOGIN);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
