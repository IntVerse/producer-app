//package com.intverse.producer.serdes;
//
//import org.apache.avro.Schema;
//import org.apache.avro.generic.GenericData;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.avro.io.*;
//import org.apache.avro.reflect.ReflectData;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Map;
//
//public class AvroSerializer<T extends GenericRecord> implements KafkaSerializer<T>, KafkaDeserializer<T> {
//
//    private final Schema schema;
//
//    public AvroSerializer(Class<T> clazz) {
//        this.schema = ReflectData.get().getSchema(clazz);
//    }
//
//    @Override
//    public byte[] serialize(String topic, T data) {
//        try {
//            DatumWriter<GenericRecord> writer = new GenericData.Record(schema).getDatumWriter();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
//            writer.write(data, encoder);
//            encoder.flush();
//            outputStream.close();
//            return outputStream.toByteArray();
//        } catch (IOException e) {
//            throw new RuntimeException("Error serializing Avro message", e);
//        }
//    }
//
//    @Override
//    public T deserialize(String topic, byte[] data) {
//        try {
//            DatumReader<GenericRecord> reader = new GenericData.Record(schema).getDatumReader();
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
//            BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(inputStream, null);
//            T result = (T) reader.read(null, decoder);
//            inputStream.close();
//            return result;
//        } catch (IOException e) {
//            throw new RuntimeException("Error deserializing Avro message", e);
//        }
//    }
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {}
//
//    @Override
//    public void close() {}
//}
