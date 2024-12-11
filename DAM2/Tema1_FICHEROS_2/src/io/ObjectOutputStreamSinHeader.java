package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamSinHeader extends ObjectOutputStream{

	protected ObjectOutputStreamSinHeader() throws IOException, SecurityException {
		super();
	}

	public ObjectOutputStreamSinHeader(FileOutputStream fileOutputStream) throws IOException {
		super(fileOutputStream);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
	}

	
	
}
