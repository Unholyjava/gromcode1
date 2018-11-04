package lessons_core_19_2;

import java.util.Arrays;

public class Storage {

	private long id;
	private File[] files;
	private String[] formatsSupported;
	private String storageCountry;
	private long storageSize;
	public Storage(long id, File[] files, String[] formatsSupported, String storageCountry, long storageSize) {
		this.id = id;
		this.files = files;
		this.formatsSupported = formatsSupported;
		this.storageCountry = storageCountry;
		this.storageSize = storageSize;
	}
	
	public long getId() {
		return id;
	}
	
	public File[] getFiles() {
		return files;
	}
	
	void setFiles(File[] files) {
		this.files = files;
	}
	
	public String[] getFormatsSupported() {
		return formatsSupported;
	}
	
	public String getStringFormatsSupported() {
		StringBuilder sb = new StringBuilder();
		for (String format : getFormatsSupported()) {
			sb.append(format + ", ");
		}
		return sb.toString().substring(0, sb.toString().length() - 2);
	}
	
	public String getStorageCountry() {
		return storageCountry;
	}
	
	public long getStorageSize() {
		return storageSize;
	}
	
	@Override
	public String toString() {
		return "Storage [id=" + id + ", files=" + Arrays.toString(files) + ", formatsSupported="
				+ Arrays.toString(formatsSupported) + ", storageCountry=" + storageCountry + ", storageSize="
				+ storageSize + "]";
	}
	

}
