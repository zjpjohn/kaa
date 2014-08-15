/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaaproject.kaa.server.common.zk.gen;
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ZkKaaTcpStatistics extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ZkKaaTcpStatistics\",\"namespace\":\"org.kaaproject.kaa.server.common.zk.gen\",\"fields\":[{\"name\":\"ZkStatistics\",\"type\":{\"type\":\"record\",\"name\":\"BaseStatistics\",\"fields\":[{\"name\":\"processedRequestCount\",\"type\":\"int\"},{\"name\":\"registeredUsersCount\",\"type\":\"int\"},{\"name\":\"deltaCalculationCount\",\"type\":\"int\"},{\"name\":\"timeStarted\",\"type\":\"long\"}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
   private org.kaaproject.kaa.server.common.zk.gen.BaseStatistics ZkStatistics;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}.
   */
  public ZkKaaTcpStatistics() {}

  /**
   * All-args constructor.
   */
  public ZkKaaTcpStatistics(org.kaaproject.kaa.server.common.zk.gen.BaseStatistics ZkStatistics) {
    this.ZkStatistics = ZkStatistics;
  }

  @Override
public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  @Override
public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return ZkStatistics;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call.
  @Override
@SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: ZkStatistics = (org.kaaproject.kaa.server.common.zk.gen.BaseStatistics)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'ZkStatistics' field.
   */
  public org.kaaproject.kaa.server.common.zk.gen.BaseStatistics getZkStatistics() {
    return ZkStatistics;
  }

  /**
   * Sets the value of the 'ZkStatistics' field.
   * @param value the value to set.
   */
  public void setZkStatistics(org.kaaproject.kaa.server.common.zk.gen.BaseStatistics value) {
    this.ZkStatistics = value;
  }

  /** Creates a new ZkKaaTcpStatistics RecordBuilder */
  public static org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder newBuilder() {
    return new org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder();
  }

  /** Creates a new ZkKaaTcpStatistics RecordBuilder by copying an existing Builder */
  public static org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder newBuilder(org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder other) {
    return new org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder(other);
  }

  /** Creates a new ZkKaaTcpStatistics RecordBuilder by copying an existing ZkKaaTcpStatistics instance */
  public static org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder newBuilder(org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics other) {
    return new org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder(other);
  }

  /**
   * RecordBuilder for ZkKaaTcpStatistics instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ZkKaaTcpStatistics>
    implements org.apache.avro.data.RecordBuilder<ZkKaaTcpStatistics> {

    private org.kaaproject.kaa.server.common.zk.gen.BaseStatistics ZkStatistics;

    /** Creates a new Builder */
    private Builder() {
      super(org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.SCHEMA$);
    }

    /** Creates a Builder by copying an existing Builder */
    private Builder(org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.ZkStatistics)) {
        this.ZkStatistics = data().deepCopy(fields()[0].schema(), other.ZkStatistics);
        fieldSetFlags()[0] = true;
      }
    }

    /** Creates a Builder by copying an existing ZkKaaTcpStatistics instance */
    private Builder(org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics other) {
            super(org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.SCHEMA$);
      if (isValidValue(fields()[0], other.ZkStatistics)) {
        this.ZkStatistics = data().deepCopy(fields()[0].schema(), other.ZkStatistics);
        fieldSetFlags()[0] = true;
      }
    }

    /** Gets the value of the 'ZkStatistics' field */
    public org.kaaproject.kaa.server.common.zk.gen.BaseStatistics getZkStatistics() {
      return ZkStatistics;
    }

    /** Sets the value of the 'ZkStatistics' field */
    public org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder setZkStatistics(org.kaaproject.kaa.server.common.zk.gen.BaseStatistics value) {
      validate(fields()[0], value);
      this.ZkStatistics = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /** Checks whether the 'ZkStatistics' field has been set */
    public boolean hasZkStatistics() {
      return fieldSetFlags()[0];
    }

    /** Clears the value of the 'ZkStatistics' field */
    public org.kaaproject.kaa.server.common.zk.gen.ZkKaaTcpStatistics.Builder clearZkStatistics() {
      ZkStatistics = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public ZkKaaTcpStatistics build() {
      try {
        ZkKaaTcpStatistics record = new ZkKaaTcpStatistics();
        record.ZkStatistics = fieldSetFlags()[0] ? this.ZkStatistics : (org.kaaproject.kaa.server.common.zk.gen.BaseStatistics) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
