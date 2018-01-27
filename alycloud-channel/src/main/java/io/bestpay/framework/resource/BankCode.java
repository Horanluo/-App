/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */

/**
 * @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */

package io.bestpay.framework.resource;

import org.apache.avro.specific.SpecificData;

import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("all")
/** 银行代码 */
@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(name="BankCode", namespace="io.bestpay.framework.resource")
@org.apache.avro.specific.AvroGenerated
public class BankCode extends io.bestpay.framework.base.SpecificRecordBase implements io.bestpay.framework.base.SpecificRecord {
  private static final long serialVersionUID = 5858828302317927919L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BankCode\",\"namespace\":\"io.bestpay.framework.resource\",\"doc\":\"银行代码\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"主键\"},{\"name\":\"bank_code\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"代码\",\"default\":\"\"},{\"name\":\"bank_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"名称\",\"default\":\"\"},{\"name\":\"description\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"备注\",\"default\":\"\"},{\"name\":\"bank_code_type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"类型\",\"default\":\"\"}],\"aliases\":[\"BankCode\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** 主键 */
  @javax.xml.bind.annotation.XmlElement(name="id")
  private java.lang.String id;
  /** 代码 */
  @javax.xml.bind.annotation.XmlElement(name="bank_code")
  private java.lang.String bank_code;
  /** 名称 */
  @javax.xml.bind.annotation.XmlElement(name="bank_name")
  private java.lang.String bank_name;
  /** 备注 */
  @javax.xml.bind.annotation.XmlElement(name="description")
  private java.lang.String description;
  /** 类型 */
  @javax.xml.bind.annotation.XmlElement(name="bank_code_type")
  private java.lang.String bank_code_type;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public BankCode() {}

  /**
   * All-args constructor.
   * @param id 主键
   * @param bank_code 代码
   * @param bank_name 名称
   * @param description 备注
   * @param bank_code_type 类型
   */
  public BankCode(java.lang.String id, java.lang.String bank_code, java.lang.String bank_name, java.lang.String description, java.lang.String bank_code_type) {
    this.id = id;
    this.bank_code = bank_code;
    this.bank_name = bank_name;
    this.description = description;
    this.bank_code_type = bank_code_type;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  @javax.xml.bind.annotation.XmlTransient
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return bank_code;
    case 2: return bank_name;
    case 3: return description;
    case 4: return bank_code_type;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.String)value$; break;
    case 1: bank_code = (java.lang.String)value$; break;
    case 2: bank_name = (java.lang.String)value$; break;
    case 3: description = (java.lang.String)value$; break;
    case 4: bank_code_type = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return 主键
   */
  @com.fasterxml.jackson.annotation.JsonGetter("id")
  @org.codehaus.jackson.annotate.JsonProperty("id")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * 主键
   * @param value the value to set.
   */
  protected void setId(java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'bank_code' field.
   * @return 代码
   */
  @com.fasterxml.jackson.annotation.JsonGetter("bank_code")
  @org.codehaus.jackson.annotate.JsonProperty("bank_code")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getBankCode() {
    return bank_code;
  }

  /**
   * Sets the value of the 'bank_code' field.
   * 代码
   * @param value the value to set.
   */
  protected void setBankCode(java.lang.String value) {
    this.bank_code = value;
  }

  /**
   * Gets the value of the 'bank_name' field.
   * @return 名称
   */
  @com.fasterxml.jackson.annotation.JsonGetter("bank_name")
  @org.codehaus.jackson.annotate.JsonProperty("bank_name")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getBankName() {
    return bank_name;
  }

  /**
   * Sets the value of the 'bank_name' field.
   * 名称
   * @param value the value to set.
   */
  protected void setBankName(java.lang.String value) {
    this.bank_name = value;
  }

  /**
   * Gets the value of the 'description' field.
   * @return 备注
   */
  @com.fasterxml.jackson.annotation.JsonGetter("description")
  @org.codehaus.jackson.annotate.JsonProperty("description")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * Sets the value of the 'description' field.
   * 备注
   * @param value the value to set.
   */
  protected void setDescription(java.lang.String value) {
    this.description = value;
  }

  /**
   * Gets the value of the 'bank_code_type' field.
   * @return 类型
   */
  @com.fasterxml.jackson.annotation.JsonGetter("bank_code_type")
  @org.codehaus.jackson.annotate.JsonProperty("bank_code_type")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getBankCodeType() {
    return bank_code_type;
  }

  /**
   * Sets the value of the 'bank_code_type' field.
   * 类型
   * @param value the value to set.
   */
  protected void setBankCodeType(java.lang.String value) {
    this.bank_code_type = value;
  }

  /**
   * Creates a new BankCode RecordBuilder.
   * @return A new BankCode RecordBuilder
   */
  public static io.bestpay.framework.resource.BankCode.Builder newBuilder() {
    return new io.bestpay.framework.resource.BankCode.Builder();
  }

  /**
   * Creates a new BankCode RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new BankCode RecordBuilder
   */
  public static io.bestpay.framework.resource.BankCode.Builder newBuilder(io.bestpay.framework.resource.BankCode.Builder other) {
    return new io.bestpay.framework.resource.BankCode.Builder(other);
  }

  /**
   * Creates a new BankCode RecordBuilder by copying an existing BankCode instance.
   * @param other The existing instance to copy.
   * @return A new BankCode RecordBuilder
   */
  public static io.bestpay.framework.resource.BankCode.Builder newBuilder(io.bestpay.framework.resource.BankCode other) {
    return new io.bestpay.framework.resource.BankCode.Builder(other);
  }

  /**
   * RecordBuilder for BankCode instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BankCode>
    implements org.apache.avro.data.RecordBuilder<BankCode> {

    /** 主键 */
    private java.lang.String id;
    /** 代码 */
    private java.lang.String bank_code;
    /** 名称 */
    private java.lang.String bank_name;
    /** 备注 */
    private java.lang.String description;
    /** 类型 */
    private java.lang.String bank_code_type;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.bestpay.framework.resource.BankCode.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bank_code)) {
        this.bank_code = data().deepCopy(fields()[1].schema(), other.bank_code);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bank_name)) {
        this.bank_name = data().deepCopy(fields()[2].schema(), other.bank_name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.description)) {
        this.description = data().deepCopy(fields()[3].schema(), other.description);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.bank_code_type)) {
        this.bank_code_type = data().deepCopy(fields()[4].schema(), other.bank_code_type);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing BankCode instance
     * @param other The existing instance to copy.
     */
    private Builder(io.bestpay.framework.resource.BankCode other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bank_code)) {
        this.bank_code = data().deepCopy(fields()[1].schema(), other.bank_code);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bank_name)) {
        this.bank_name = data().deepCopy(fields()[2].schema(), other.bank_name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.description)) {
        this.description = data().deepCopy(fields()[3].schema(), other.description);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.bank_code_type)) {
        this.bank_code_type = data().deepCopy(fields()[4].schema(), other.bank_code_type);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * 主键
      * @return The value.
      */
    public java.lang.String getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * 主键
      * @param value The value of 'id'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder setId(java.lang.String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * 主键
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * 主键
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'bank_code' field.
      * 代码
      * @return The value.
      */
    public java.lang.String getBankCode() {
      return bank_code;
    }

    /**
      * Sets the value of the 'bank_code' field.
      * 代码
      * @param value The value of 'bank_code'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder setBankCode(java.lang.String value) {
      validate(fields()[1], value);
      this.bank_code = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'bank_code' field has been set.
      * 代码
      * @return True if the 'bank_code' field has been set, false otherwise.
      */
    public boolean hasBankCode() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'bank_code' field.
      * 代码
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder clearBankCode() {
      bank_code = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'bank_name' field.
      * 名称
      * @return The value.
      */
    public java.lang.String getBankName() {
      return bank_name;
    }

    /**
      * Sets the value of the 'bank_name' field.
      * 名称
      * @param value The value of 'bank_name'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder setBankName(java.lang.String value) {
      validate(fields()[2], value);
      this.bank_name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'bank_name' field has been set.
      * 名称
      * @return True if the 'bank_name' field has been set, false otherwise.
      */
    public boolean hasBankName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'bank_name' field.
      * 名称
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder clearBankName() {
      bank_name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * 备注
      * @return The value.
      */
    public java.lang.String getDescription() {
      return description;
    }

    /**
      * Sets the value of the 'description' field.
      * 备注
      * @param value The value of 'description'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder setDescription(java.lang.String value) {
      validate(fields()[3], value);
      this.description = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'description' field has been set.
      * 备注
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'description' field.
      * 备注
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder clearDescription() {
      description = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'bank_code_type' field.
      * 类型
      * @return The value.
      */
    public java.lang.String getBankCodeType() {
      return bank_code_type;
    }

    /**
      * Sets the value of the 'bank_code_type' field.
      * 类型
      * @param value The value of 'bank_code_type'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder setBankCodeType(java.lang.String value) {
      validate(fields()[4], value);
      this.bank_code_type = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'bank_code_type' field has been set.
      * 类型
      * @return True if the 'bank_code_type' field has been set, false otherwise.
      */
    public boolean hasBankCodeType() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'bank_code_type' field.
      * 类型
      * @return This builder.
      */
    public io.bestpay.framework.resource.BankCode.Builder clearBankCodeType() {
      bank_code_type = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    public BankCode build() {
      try {
        BankCode record = new BankCode();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.bank_code = fieldSetFlags()[1] ? this.bank_code : (java.lang.String) defaultValue(fields()[1]);
        record.bank_name = fieldSetFlags()[2] ? this.bank_name : (java.lang.String) defaultValue(fields()[2]);
        record.description = fieldSetFlags()[3] ? this.description : (java.lang.String) defaultValue(fields()[3]);
        record.bank_code_type = fieldSetFlags()[4] ? this.bank_code_type : (java.lang.String) defaultValue(fields()[4]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
