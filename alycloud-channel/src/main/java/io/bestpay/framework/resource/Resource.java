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
/** 资源管理 */
@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(name="Resource", namespace="io.bestpay.framework.resource")
@org.apache.avro.specific.AvroGenerated
public class Resource extends io.bestpay.framework.base.SpecificRecordBase implements io.bestpay.framework.base.SpecificRecord {
  private static final long serialVersionUID = 1012239243922761103L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Resource\",\"namespace\":\"io.bestpay.framework.resource\",\"doc\":\"资源管理\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"主键\",\"default\":\"\"},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"名称\",\"default\":\"\"},{\"name\":\"code\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"代码\",\"default\":\"\"},{\"name\":\"url\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"路径\",\"default\":\"\"},{\"name\":\"class_icon\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"图标\",\"default\":\"\"},{\"name\":\"class_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"图标\",\"default\":\"\"},{\"name\":\"parent_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"上级代码\",\"default\":\"\"},{\"name\":\"view_class\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"视图实现类\",\"default\":\"\"},{\"name\":\"resource_type\",\"type\":{\"type\":\"enum\",\"name\":\"ResourceType\",\"symbols\":[\"APPLICATION\",\"MODULE\",\"VIEW\",\"RESOURCE\"]},\"doc\":\"资源类型\",\"default\":\"APPLICATION\"},{\"name\":\"sort_no\",\"type\":\"long\",\"doc\":\"排序值\",\"default\":100},{\"name\":\"display\",\"type\":\"boolean\",\"doc\":\"是否显示\",\"default\":true}],\"aliases\":[\"Resource\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** 主键 */
  @javax.xml.bind.annotation.XmlElement(name="id")
  private java.lang.String id;
  /** 名称 */
  @javax.xml.bind.annotation.XmlElement(name="name")
  private java.lang.String name;
  /** 代码 */
  @javax.xml.bind.annotation.XmlElement(name="code")
  private java.lang.String code;
  /** 路径 */
  @javax.xml.bind.annotation.XmlElement(name="url")
  private java.lang.String url;
  /** 图标 */
  @javax.xml.bind.annotation.XmlElement(name="class_icon")
  private java.lang.String class_icon;
  /** 图标 */
  @javax.xml.bind.annotation.XmlElement(name="class_name")
  private java.lang.String class_name;
  /** 上级代码 */
  @javax.xml.bind.annotation.XmlElement(name="parent_id")
  private java.lang.String parent_id;
  /** 视图实现类 */
  @javax.xml.bind.annotation.XmlElement(name="view_class")
  private java.lang.String view_class;
  /** 资源类型 */
  @javax.xml.bind.annotation.XmlElement(name="resource_type")
  private io.bestpay.framework.resource.ResourceType resource_type;
  /** 排序值 */
  @javax.xml.bind.annotation.XmlElement(name="sort_no")
  private java.lang.Long sort_no;
  /** 是否显示 */
  @javax.xml.bind.annotation.XmlElement(name="display")
  private java.lang.Boolean display;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Resource() {}

  /**
   * All-args constructor.
   * @param id 主键
   * @param name 名称
   * @param code 代码
   * @param url 路径
   * @param class_icon 图标
   * @param class_name 图标
   * @param parent_id 上级代码
   * @param view_class 视图实现类
   * @param resource_type 资源类型
   * @param sort_no 排序值
   * @param display 是否显示
   */
  public Resource(java.lang.String id, java.lang.String name, java.lang.String code, java.lang.String url, java.lang.String class_icon, java.lang.String class_name, java.lang.String parent_id, java.lang.String view_class, io.bestpay.framework.resource.ResourceType resource_type, java.lang.Long sort_no, java.lang.Boolean display) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.url = url;
    this.class_icon = class_icon;
    this.class_name = class_name;
    this.parent_id = parent_id;
    this.view_class = view_class;
    this.resource_type = resource_type;
    this.sort_no = sort_no;
    this.display = display;
  }

  @com.fasterxml.jackson.annotation.JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  @javax.xml.bind.annotation.XmlTransient
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return name;
    case 2: return code;
    case 3: return url;
    case 4: return class_icon;
    case 5: return class_name;
    case 6: return parent_id;
    case 7: return view_class;
    case 8: return resource_type;
    case 9: return sort_no;
    case 10: return display;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.String)value$; break;
    case 1: name = (java.lang.String)value$; break;
    case 2: code = (java.lang.String)value$; break;
    case 3: url = (java.lang.String)value$; break;
    case 4: class_icon = (java.lang.String)value$; break;
    case 5: class_name = (java.lang.String)value$; break;
    case 6: parent_id = (java.lang.String)value$; break;
    case 7: view_class = (java.lang.String)value$; break;
    case 8: resource_type = (io.bestpay.framework.resource.ResourceType)value$; break;
    case 9: sort_no = (java.lang.Long)value$; break;
    case 10: display = (java.lang.Boolean)value$; break;
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
   * Gets the value of the 'name' field.
   * @return 名称
   */
  @com.fasterxml.jackson.annotation.JsonGetter("name")
  @org.codehaus.jackson.annotate.JsonProperty("name")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * 名称
   * @param value the value to set.
   */
  protected void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'code' field.
   * @return 代码
   */
  @com.fasterxml.jackson.annotation.JsonGetter("code")
  @org.codehaus.jackson.annotate.JsonProperty("code")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getCode() {
    return code;
  }

  /**
   * Sets the value of the 'code' field.
   * 代码
   * @param value the value to set.
   */
  protected void setCode(java.lang.String value) {
    this.code = value;
  }

  /**
   * Gets the value of the 'url' field.
   * @return 路径
   */
  @com.fasterxml.jackson.annotation.JsonGetter("url")
  @org.codehaus.jackson.annotate.JsonProperty("url")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getUrl() {
    return url;
  }

  /**
   * Sets the value of the 'url' field.
   * 路径
   * @param value the value to set.
   */
  protected void setUrl(java.lang.String value) {
    this.url = value;
  }

  /**
   * Gets the value of the 'class_icon' field.
   * @return 图标
   */
  @com.fasterxml.jackson.annotation.JsonGetter("class_icon")
  @org.codehaus.jackson.annotate.JsonProperty("class_icon")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getClassIcon() {
    return class_icon;
  }

  /**
   * Sets the value of the 'class_icon' field.
   * 图标
   * @param value the value to set.
   */
  protected void setClassIcon(java.lang.String value) {
    this.class_icon = value;
  }

  /**
   * Gets the value of the 'class_name' field.
   * @return 图标
   */
  @com.fasterxml.jackson.annotation.JsonGetter("class_name")
  @org.codehaus.jackson.annotate.JsonProperty("class_name")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getClassName() {
    return class_name;
  }

  /**
   * Sets the value of the 'class_name' field.
   * 图标
   * @param value the value to set.
   */
  protected void setClassName(java.lang.String value) {
    this.class_name = value;
  }

  /**
   * Gets the value of the 'parent_id' field.
   * @return 上级代码
   */
  @com.fasterxml.jackson.annotation.JsonGetter("parent_id")
  @org.codehaus.jackson.annotate.JsonProperty("parent_id")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getParentId() {
    return parent_id;
  }

  /**
   * Sets the value of the 'parent_id' field.
   * 上级代码
   * @param value the value to set.
   */
  protected void setParentId(java.lang.String value) {
    this.parent_id = value;
  }

  /**
   * Gets the value of the 'view_class' field.
   * @return 视图实现类
   */
  @com.fasterxml.jackson.annotation.JsonGetter("view_class")
  @org.codehaus.jackson.annotate.JsonProperty("view_class")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.String getViewClass() {
    return view_class;
  }

  /**
   * Sets the value of the 'view_class' field.
   * 视图实现类
   * @param value the value to set.
   */
  protected void setViewClass(java.lang.String value) {
    this.view_class = value;
  }

  /**
   * Gets the value of the 'resource_type' field.
   * @return 资源类型
   */
  @com.fasterxml.jackson.annotation.JsonGetter("resource_type")
  @org.codehaus.jackson.annotate.JsonProperty("resource_type")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public io.bestpay.framework.resource.ResourceType getResourceType() {
    return resource_type;
  }

  /**
   * Sets the value of the 'resource_type' field.
   * 资源类型
   * @param value the value to set.
   */
  protected void setResourceType(io.bestpay.framework.resource.ResourceType value) {
    this.resource_type = value;
  }

  /**
   * Gets the value of the 'sort_no' field.
   * @return 排序值
   */
  @com.fasterxml.jackson.annotation.JsonGetter("sort_no")
  @org.codehaus.jackson.annotate.JsonProperty("sort_no")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.Long getSortNo() {
    return sort_no;
  }

  /**
   * Sets the value of the 'sort_no' field.
   * 排序值
   * @param value the value to set.
   */
  protected void setSortNo(java.lang.Long value) {
    this.sort_no = value;
  }

  /**
   * Gets the value of the 'display' field.
   * @return 是否显示
   */
  @com.fasterxml.jackson.annotation.JsonGetter("display")
  @org.codehaus.jackson.annotate.JsonProperty("display")   
  @com.fasterxml.jackson.annotation.JsonInclude(Include.NON_NULL)
  @org.codehaus.jackson.map.annotate.JsonSerialize(include=Inclusion.NON_NULL)  
  @javax.xml.bind.annotation.XmlTransient
  public java.lang.Boolean getDisplay() {
    return display;
  }

  /**
   * Sets the value of the 'display' field.
   * 是否显示
   * @param value the value to set.
   */
  protected void setDisplay(java.lang.Boolean value) {
    this.display = value;
  }

  /**
   * Creates a new Resource RecordBuilder.
   * @return A new Resource RecordBuilder
   */
  public static io.bestpay.framework.resource.Resource.Builder newBuilder() {
    return new io.bestpay.framework.resource.Resource.Builder();
  }

  /**
   * Creates a new Resource RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Resource RecordBuilder
   */
  public static io.bestpay.framework.resource.Resource.Builder newBuilder(io.bestpay.framework.resource.Resource.Builder other) {
    return new io.bestpay.framework.resource.Resource.Builder(other);
  }

  /**
   * Creates a new Resource RecordBuilder by copying an existing Resource instance.
   * @param other The existing instance to copy.
   * @return A new Resource RecordBuilder
   */
  public static io.bestpay.framework.resource.Resource.Builder newBuilder(io.bestpay.framework.resource.Resource other) {
    return new io.bestpay.framework.resource.Resource.Builder(other);
  }

  /**
   * RecordBuilder for Resource instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Resource>
    implements org.apache.avro.data.RecordBuilder<Resource> {

    /** 主键 */
    private java.lang.String id;
    /** 名称 */
    private java.lang.String name;
    /** 代码 */
    private java.lang.String code;
    /** 路径 */
    private java.lang.String url;
    /** 图标 */
    private java.lang.String class_icon;
    /** 图标 */
    private java.lang.String class_name;
    /** 上级代码 */
    private java.lang.String parent_id;
    /** 视图实现类 */
    private java.lang.String view_class;
    /** 资源类型 */
    private io.bestpay.framework.resource.ResourceType resource_type;
    /** 排序值 */
    private java.lang.Long sort_no;
    /** 是否显示 */
    private java.lang.Boolean display;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.bestpay.framework.resource.Resource.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.code)) {
        this.code = data().deepCopy(fields()[2].schema(), other.code);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.url)) {
        this.url = data().deepCopy(fields()[3].schema(), other.url);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.class_icon)) {
        this.class_icon = data().deepCopy(fields()[4].schema(), other.class_icon);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.class_name)) {
        this.class_name = data().deepCopy(fields()[5].schema(), other.class_name);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.parent_id)) {
        this.parent_id = data().deepCopy(fields()[6].schema(), other.parent_id);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.view_class)) {
        this.view_class = data().deepCopy(fields()[7].schema(), other.view_class);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.resource_type)) {
        this.resource_type = data().deepCopy(fields()[8].schema(), other.resource_type);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.sort_no)) {
        this.sort_no = data().deepCopy(fields()[9].schema(), other.sort_no);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.display)) {
        this.display = data().deepCopy(fields()[10].schema(), other.display);
        fieldSetFlags()[10] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Resource instance
     * @param other The existing instance to copy.
     */
    private Builder(io.bestpay.framework.resource.Resource other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.code)) {
        this.code = data().deepCopy(fields()[2].schema(), other.code);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.url)) {
        this.url = data().deepCopy(fields()[3].schema(), other.url);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.class_icon)) {
        this.class_icon = data().deepCopy(fields()[4].schema(), other.class_icon);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.class_name)) {
        this.class_name = data().deepCopy(fields()[5].schema(), other.class_name);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.parent_id)) {
        this.parent_id = data().deepCopy(fields()[6].schema(), other.parent_id);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.view_class)) {
        this.view_class = data().deepCopy(fields()[7].schema(), other.view_class);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.resource_type)) {
        this.resource_type = data().deepCopy(fields()[8].schema(), other.resource_type);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.sort_no)) {
        this.sort_no = data().deepCopy(fields()[9].schema(), other.sort_no);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.display)) {
        this.display = data().deepCopy(fields()[10].schema(), other.display);
        fieldSetFlags()[10] = true;
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
    public io.bestpay.framework.resource.Resource.Builder setId(java.lang.String value) {
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
    public io.bestpay.framework.resource.Resource.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * 名称
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * 名称
      * @param value The value of 'name'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setName(java.lang.String value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * 名称
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'name' field.
      * 名称
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'code' field.
      * 代码
      * @return The value.
      */
    public java.lang.String getCode() {
      return code;
    }

    /**
      * Sets the value of the 'code' field.
      * 代码
      * @param value The value of 'code'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setCode(java.lang.String value) {
      validate(fields()[2], value);
      this.code = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'code' field has been set.
      * 代码
      * @return True if the 'code' field has been set, false otherwise.
      */
    public boolean hasCode() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'code' field.
      * 代码
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearCode() {
      code = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'url' field.
      * 路径
      * @return The value.
      */
    public java.lang.String getUrl() {
      return url;
    }

    /**
      * Sets the value of the 'url' field.
      * 路径
      * @param value The value of 'url'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setUrl(java.lang.String value) {
      validate(fields()[3], value);
      this.url = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'url' field has been set.
      * 路径
      * @return True if the 'url' field has been set, false otherwise.
      */
    public boolean hasUrl() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'url' field.
      * 路径
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearUrl() {
      url = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'class_icon' field.
      * 图标
      * @return The value.
      */
    public java.lang.String getClassIcon() {
      return class_icon;
    }

    /**
      * Sets the value of the 'class_icon' field.
      * 图标
      * @param value The value of 'class_icon'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setClassIcon(java.lang.String value) {
      validate(fields()[4], value);
      this.class_icon = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'class_icon' field has been set.
      * 图标
      * @return True if the 'class_icon' field has been set, false otherwise.
      */
    public boolean hasClassIcon() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'class_icon' field.
      * 图标
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearClassIcon() {
      class_icon = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'class_name' field.
      * 图标
      * @return The value.
      */
    public java.lang.String getClassName() {
      return class_name;
    }

    /**
      * Sets the value of the 'class_name' field.
      * 图标
      * @param value The value of 'class_name'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setClassName(java.lang.String value) {
      validate(fields()[5], value);
      this.class_name = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'class_name' field has been set.
      * 图标
      * @return True if the 'class_name' field has been set, false otherwise.
      */
    public boolean hasClassName() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'class_name' field.
      * 图标
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearClassName() {
      class_name = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'parent_id' field.
      * 上级代码
      * @return The value.
      */
    public java.lang.String getParentId() {
      return parent_id;
    }

    /**
      * Sets the value of the 'parent_id' field.
      * 上级代码
      * @param value The value of 'parent_id'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setParentId(java.lang.String value) {
      validate(fields()[6], value);
      this.parent_id = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'parent_id' field has been set.
      * 上级代码
      * @return True if the 'parent_id' field has been set, false otherwise.
      */
    public boolean hasParentId() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'parent_id' field.
      * 上级代码
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearParentId() {
      parent_id = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'view_class' field.
      * 视图实现类
      * @return The value.
      */
    public java.lang.String getViewClass() {
      return view_class;
    }

    /**
      * Sets the value of the 'view_class' field.
      * 视图实现类
      * @param value The value of 'view_class'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setViewClass(java.lang.String value) {
      validate(fields()[7], value);
      this.view_class = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'view_class' field has been set.
      * 视图实现类
      * @return True if the 'view_class' field has been set, false otherwise.
      */
    public boolean hasViewClass() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'view_class' field.
      * 视图实现类
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearViewClass() {
      view_class = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'resource_type' field.
      * 资源类型
      * @return The value.
      */
    public io.bestpay.framework.resource.ResourceType getResourceType() {
      return resource_type;
    }

    /**
      * Sets the value of the 'resource_type' field.
      * 资源类型
      * @param value The value of 'resource_type'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setResourceType(io.bestpay.framework.resource.ResourceType value) {
      validate(fields()[8], value);
      this.resource_type = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'resource_type' field has been set.
      * 资源类型
      * @return True if the 'resource_type' field has been set, false otherwise.
      */
    public boolean hasResourceType() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'resource_type' field.
      * 资源类型
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearResourceType() {
      resource_type = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'sort_no' field.
      * 排序值
      * @return The value.
      */
    public java.lang.Long getSortNo() {
      return sort_no;
    }

    /**
      * Sets the value of the 'sort_no' field.
      * 排序值
      * @param value The value of 'sort_no'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setSortNo(java.lang.Long value) {
      validate(fields()[9], value);
      this.sort_no = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'sort_no' field has been set.
      * 排序值
      * @return True if the 'sort_no' field has been set, false otherwise.
      */
    public boolean hasSortNo() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'sort_no' field.
      * 排序值
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearSortNo() {
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'display' field.
      * 是否显示
      * @return The value.
      */
    public java.lang.Boolean getDisplay() {
      return display;
    }

    /**
      * Sets the value of the 'display' field.
      * 是否显示
      * @param value The value of 'display'.
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder setDisplay(java.lang.Boolean value) {
      validate(fields()[10], value);
      this.display = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'display' field has been set.
      * 是否显示
      * @return True if the 'display' field has been set, false otherwise.
      */
    public boolean hasDisplay() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the 'display' field.
      * 是否显示
      * @return This builder.
      */
    public io.bestpay.framework.resource.Resource.Builder clearDisplay() {
      fieldSetFlags()[10] = false;
      return this;
    }

    @Override
    public Resource build() {
      try {
        Resource record = new Resource();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.String) defaultValue(fields()[1]);
        record.code = fieldSetFlags()[2] ? this.code : (java.lang.String) defaultValue(fields()[2]);
        record.url = fieldSetFlags()[3] ? this.url : (java.lang.String) defaultValue(fields()[3]);
        record.class_icon = fieldSetFlags()[4] ? this.class_icon : (java.lang.String) defaultValue(fields()[4]);
        record.class_name = fieldSetFlags()[5] ? this.class_name : (java.lang.String) defaultValue(fields()[5]);
        record.parent_id = fieldSetFlags()[6] ? this.parent_id : (java.lang.String) defaultValue(fields()[6]);
        record.view_class = fieldSetFlags()[7] ? this.view_class : (java.lang.String) defaultValue(fields()[7]);
        record.resource_type = fieldSetFlags()[8] ? this.resource_type : (io.bestpay.framework.resource.ResourceType) defaultValue(fields()[8]);
        record.sort_no = fieldSetFlags()[9] ? this.sort_no : (java.lang.Long) defaultValue(fields()[9]);
        record.display = fieldSetFlags()[10] ? this.display : (java.lang.Boolean) defaultValue(fields()[10]);
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
