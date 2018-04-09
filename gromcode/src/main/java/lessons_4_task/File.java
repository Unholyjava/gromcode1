package lessons_4_task;

public class File {

	private long id;
	private String name;
	private String format;
	private long size;
	public File(long id, String name, String format, long size) {
		super();
		this.id = id;
		this.name = name;
		this.format = format;
		this.size = size;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [id=" + id + ", name=" + name + ", format=" + format + ", size=" + size + "]";
	}
	

}
