/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.kaaproject.kaa.common.endpoint.gen;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public enum SyncResponseStatus {
  NO_DELTA, DELTA, RESYNC;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"SyncResponseStatus\",\"namespace\":\"org.kaaproject.kaa.common.endpoint.gen\",\"symbols\":[\"NO_DELTA\",\"DELTA\",\"RESYNC\"]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }
}
