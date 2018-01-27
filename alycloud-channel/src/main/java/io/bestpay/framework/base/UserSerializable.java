/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.compress.utils.IOUtils;

/**
 * 用户序列化
 * <pre>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月3日 
 *
 */
public class UserSerializable implements ISerializable<User> {

	@Override
	public byte[] encode(User datum) throws IOException {
		ByteArrayOutputStream baos = null;
		DataFileWriter<User> dataFileWriter = null;
		try {
			baos = new ByteArrayOutputStream();
			DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
			Encoder encoder= EncoderFactory.get().binaryEncoder(baos,null);  
			userDatumWriter.write(datum, encoder); 
			encoder.flush();
			return baos.toByteArray();
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			IOUtils.closeQuietly(baos);
			IOUtils.closeQuietly(dataFileWriter);
		}
		
	}

	@Override
	public User decode(byte[] data) throws IOException {
		DataFileReader<User> dataFileReader = null;
		SeekableByteArrayInput sbai = null;
		try {
			DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
			Decoder decoder = DecoderFactory.get().binaryDecoder(data,null);  
			User readUser = userDatumReader.read(null, decoder);
			return readUser;
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			IOUtils.closeQuietly(sbai);
			IOUtils.closeQuietly(dataFileReader);
		}
	}


}
