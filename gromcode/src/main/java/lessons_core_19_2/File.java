package lessons_core_19_2;

public class File {

	private long id;
	private String name;
	private String format;
	private long size;
	public File(long id, String name, String format, long size) {
		this.id = id;
		this.name = name;
		this.format = format;
		this.size = size;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFormat() {
		return format;
	}
	
	public long getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return "File [id=" + id + ", name=" + name + ", format=" + format + ", size=" + size + "]";
	}
	

}
