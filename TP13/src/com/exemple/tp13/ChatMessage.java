package com.exemple.tp13;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.Instant;
import java.util.Objects;

public class ChatMessage implements Externalizable {
	private static final long serialVersionUID = 2L;
	private static final int FORMAT_VERSION = 1;

	private String user;
	private String message;
	private Instant timestamp;
	private transient int length;

	public ChatMessage() {
	}

	public ChatMessage(String user, String message) {
		this.user = Objects.requireNonNull(user, "user ne peut pas être null");
		this.message = Objects.requireNonNull(message, "message ne peut pas être null");
		this.timestamp = Instant.now();
		this.length = this.message.length();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(FORMAT_VERSION);
		out.writeUTF(user);
		out.writeUTF(message);
		out.writeLong(timestamp.toEpochMilli());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		int version = in.readInt();
		if (version != FORMAT_VERSION) {
			throw new IOException("Version de format inconnue : " + version);
		}

		this.user = in.readUTF();
		this.message = in.readUTF();
		this.timestamp = Instant.ofEpochMilli(in.readLong());
		this.length = (this.message != null) ? this.message.length() : 0;
	}

	public String getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public int getLength() {
		return length;
	}

	@Override
	public String toString() {
		return String.format("%s [%s]: %s", timestamp, user, message);
	}
}
